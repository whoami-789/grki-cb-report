package com.grkicbreport.components;

import com.grkicbreport.model.Inform;
import com.grkicbreport.repository.InformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InformHelper {

    private final InformRepository informRepository;

    @Autowired
    public InformHelper(InformRepository informRepository) {
        this.informRepository = informRepository;
    }

    /**
     * Извлекает одну строку из таблицы "inform".
     *
     * @return объект Inform или null, если данных нет.
     */
    public Inform fetchSingleRow() {
        Optional<Inform> optionalInform = informRepository.findAll().stream().findFirst();
        return optionalInform.orElse(null);
    }

    /**
     * Получить значение определенного столбца из первой строки.
     *
     * @param columnName имя столбца.
     * @return значение столбца в виде строки, либо null, если столбца или строки нет.
     */
    public String getColumnValue(String columnName) {
        Inform inform = fetchSingleRow();
        if (inform == null) {
            return null;
        }

        return switch (columnName) {
            case "name" -> inform.getName();
            case "mlId" -> inform.getNumks();
            case "director" -> inform.getFioDirektor();
            case "grki_password" -> inform.getGrki_password();
            case "grki_file_path" -> inform.getGrki_password();
            case "inn" -> inform.getInn().toString();
            default -> throw new IllegalArgumentException("Неизвестное имя столбца: " + columnName);
        };
    }
}
