package ru.mtsbank.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mtsbank.fintech.entity.AnimalType;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {
}
