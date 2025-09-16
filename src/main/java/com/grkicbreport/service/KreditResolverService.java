package com.grkicbreport.service;

import com.grkicbreport.model.Dok;
import com.grkicbreport.model.Kredit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KreditResolverService {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(KreditResolverService.class);

    /**
     * Массовая загрузка всех кредитов по ls/lscor для заданных счетов.
     * Работает намного быстрее, чем дергать по одному через byls_kred.
     */
    public Map<String, Kredit> loadAllKredits(Set<String> accounts) {
        if (accounts == null || accounts.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Kredit> result = new HashMap<>();

        // Поля, по которым ищем
        List<String> fields = List.of(
                "lskred",
                "lsprosr_kred",
                "lssud_kred",
                "lsproc",
                "lsprocvne",
                "ls_spiskred",
                "lsprosr_proc",
                "lsrezerv"
        );

        for (String field : fields) {
            String sql = "SELECT * FROM kredit WHERE " + field + " IN :accounts";
            Query query = entityManager.createNativeQuery(sql, Kredit.class);
            query.setParameter("accounts", accounts);

            List<Kredit> kredits = query.getResultList();
            for (Kredit k : kredits) {
                try {
                    // забираем все возможные счета
                    for (String candidate : List.of(
                            k.getLskred(),
                            k.getLsprosrKred(),
                            k.getLssudKred(),
                            k.getLsproc(),
                            k.getLsprocvne(),
                            k.getLs_spiskred(),
                            k.getLsprosrProc(),
                            k.getLsrezerv()
                    )) {
                        if (candidate != null && !candidate.isBlank()) {
                            result.put(candidate, k);
                        }
                    }
                } catch (Exception e) {
                    logger.error("Ошибка при обработке кредита id=" + k.getNumdog(), e);
                }
            }
        }

        return result;
    }

    /**
     * Поиск кредита по одному счету (медленный вариант).
     * Используется только если не нашли в предзагрузке.
     */
    public Optional<Kredit> byls_kred(String lskred) {
        if (lskred == null || lskred.isBlank()) {
            return Optional.empty();
        }

        List<String> fields = List.of(
                "lskred",
                "lsprosr_kred",
                "lssud_kred",
                "lsproc",
                "lsprocvne",
                "ls_spiskred",
                "lsprosr_proc",
                "lsrezerv"
        );

        for (String field : fields) {
            String sql = "SELECT * FROM kredit WHERE " + field + " = :lskred";
            Query query = entityManager.createNativeQuery(sql, Kredit.class);
            query.setParameter("lskred", lskred);

            try {
                Kredit kredit = (Kredit) query.getSingleResult();
                return Optional.of(kredit);
            } catch (NoResultException e) {
                // не нашли — пробуем следующее поле
            } catch (Exception e) {
                logger.warn("Ошибка при поиске кредита по полю " + field, e);
            }
        }

        logger.warn("Кредит не найден ни по основному номеру {}", lskred);
        return Optional.empty();
    }

    /**
     * Унифицированный метод: сначала ищет в мапе, потом в БД через byls_kred.
     */
    public Optional<Kredit> resolveKredit(Dok dok, Map<String, Kredit> preloaded) {
        Kredit kredit = null;

        if (dok.getLs() != null) {
            kredit = preloaded.get(dok.getLs());
        }
        if (kredit == null && dok.getLscor() != null) {
            kredit = preloaded.get(dok.getLscor());
        }

        if (kredit == null) {
            // fallback — обращаемся к byls_kred
            kredit = byls_kred(dok.getLs())
                    .or(() -> byls_kred(dok.getLscor()))
                    .orElse(null);
        }

        return Optional.ofNullable(kredit);
    }
}

