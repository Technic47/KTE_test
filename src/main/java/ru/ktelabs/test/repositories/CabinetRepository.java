package ru.ktelabs.test.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;
import ru.ktelabs.test.models.Cabinet;

import java.util.Optional;

@Repository
public interface CabinetRepository extends CommonRepository<Cabinet> {
    Optional<Cabinet> findByNumber(@NotNull int number);
    boolean existsByNumber(int number);
}
