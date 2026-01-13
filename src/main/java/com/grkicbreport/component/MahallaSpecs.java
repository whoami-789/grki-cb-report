package com.grkicbreport.component;

import com.grkicbreport.model.Mahalla;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MahallaSpecs {

    public static Specification<Mahalla> active() {
        return (root, query, cb) -> cb.isTrue(root.get("isActive"));
    }

    public static Specification<Mahalla> tokensInAnyField(List<String> tokens) {
        return (root, query, cb) -> {
            List<Predicate> andPredicates = new ArrayList<>();

            for (String t : tokens) {
                String like = "%" + t.toLowerCase() + "%";
                Predicate p = cb.or(
                        cb.like(cb.lower(root.get("name")), like),         // mahalla_name
                        cb.like(cb.lower(root.get("regionName")), like),
                        cb.like(cb.lower(root.get("districtName")), like)
                );
                andPredicates.add(p);
            }

            return cb.and(andPredicates.toArray(new Predicate[0]));
        };
    }
}