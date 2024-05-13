package ru.mtsbank.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.mtsbank.fintech.entity.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long>, JpaSpecificationExecutor<Animal> {
    List<Animal> findById(Integer id);

    List<Animal> findByName(String name);
}
