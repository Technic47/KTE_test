package ru.ktelabs.soap.services;

import ru.ktelabs.soap.models.AbstractEntity;

import java.util.List;
import java.util.UUID;

public interface CommonService<E extends AbstractEntity> {
    List<E> index();
    E save(E newItem);
    E getById(Long id);
    E getByUuid(UUID uuid);
    abstract E update(E old, E newEntity);
    boolean delete(Long id);
}
