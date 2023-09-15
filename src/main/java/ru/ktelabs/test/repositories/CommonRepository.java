package ru.ktelabs.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface CommonRepository<E> extends JpaRepository<E, Long> {
    Optional<E> findByUuid(UUID uuid);
}
