package com.grkicbreport.controller;

import com.grkicbreport.component.MahallaSpecs;
import com.grkicbreport.component.TextNorm;
import com.grkicbreport.dto.MahallaSearchDTO;
import com.grkicbreport.repository.MahallaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grki/mahalla")
public class MahallaSearchController {

    private final MahallaRepository repo;

    @GetMapping("/search")
    public List<MahallaSearchDTO> search(
            @RequestParam("q") String q,
            @RequestParam(defaultValue = "20") int limit
    ) {
        String nq = TextNorm.norm(q);
        if (nq == null || nq.isBlank()) return List.of();

        List<String> tokens = Arrays.stream(nq.split("\\s+"))
                .filter(s -> !s.isBlank())
                .distinct()
                .toList();

        int safeLimit = Math.min(Math.max(limit, 1), 50);

        var spec = MahallaSpecs.active().and(MahallaSpecs.tokensInAnyField(tokens));

        var page = repo.findAll(
                spec,
                PageRequest.of(0, safeLimit, Sort.by("regionName", "districtName", "name"))
        );

        return page.getContent().stream()
                .map(m -> new MahallaSearchDTO(
                        m.getCode(),         // <-- код махалли
                        m.getName(),         // <-- название махалли
                        m.getDistrictName(), // <-- район
                        m.getRegionName()    // <-- область
                ))
                .toList();
    }
}