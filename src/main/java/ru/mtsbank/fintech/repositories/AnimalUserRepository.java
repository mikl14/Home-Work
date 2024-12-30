package ru.mtsbank.fintech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mtsbank.fintech.entity.AnimalUser;

import java.util.Optional;

@Repository
public interface AnimalUserRepository extends JpaRepository<AnimalUser,Long> {
    Optional<AnimalUser> findByName(String name);
}
