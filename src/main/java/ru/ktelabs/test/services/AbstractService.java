package ru.ktelabs.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ktelabs.test.customExceptions.ResourceNotFoundException;
import ru.ktelabs.test.models.AbstractEntity;
import ru.ktelabs.test.repositories.CommonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E> {
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> index() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public E save(E newItem) {
        return repository.save(newItem);
    }

    @Override
    public E getById(Long id) {
        Optional<E> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return entity.get();
    }

    @Override
    public E getByUuid(UUID uuid) {
        Optional<E> entity = repository.findByUuid(uuid);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(Long.getLong(uuid.toString()));
        }
        return entity.get();
    }

    @Override
    public abstract E update(E old, E newEntity);

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else throw new ResourceNotFoundException(id);
    }
}
