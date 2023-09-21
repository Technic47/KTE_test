package ru.ktelabs.test.services;

import ru.ktelabs.test.models.AbstractEntity;

import java.util.List;
import java.util.UUID;

/**
 * Contracts for main common services.
 * @param <E>
 */
public interface CommonService<E extends AbstractEntity> {
    List<E> index();
    E save(E newItem);
    E getById(Long id);
    E getByUuid(UUID uuid);
    abstract E update(E old, E newEntity);
    boolean delete(Long id);
}
