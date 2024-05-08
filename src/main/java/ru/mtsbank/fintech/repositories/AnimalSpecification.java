package ru.mtsbank.fintech.repositories;

import org.springframework.data.jpa.domain.Specification;
import ru.mtsbank.fintech.entity.Animal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class AnimalSpecification implements Specification<Animal> {

    @Override
    public Predicate toPredicate(Root<Animal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
