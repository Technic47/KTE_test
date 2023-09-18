package ru.ktelabs.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ktelabs.test.customExceptions.ResourceNotFoundException;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.repositories.CabinetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AbstractServiceTest {
    private CabinetService service;
    @MockBean
    private CabinetRepository repository;

    @BeforeEach
    void setUp() {
        service = new CabinetService(repository);
    }

    @Test
    void index() {
        List<Cabinet> list = new ArrayList<>();
        list.add(new Cabinet());

        doReturn(list)
                .when(repository)
                .findAll();

        List<Cabinet> cabList = service.index();

        verify(repository).findAll();

        assertNotNull(cabList);
        assertEquals(1, cabList.size());
    }

    @Test
    void save() {
        Cabinet cabinet = new Cabinet(100);

        doReturn(cabinet)
                .when(repository)
                .save(any(Cabinet.class));

        Cabinet saved = service.save(cabinet);

        verify(repository).save(any(Cabinet.class));

        assertNotNull(saved);
        assertEquals(100, saved.getNumber());
    }

    @Test
    void getById() {
        Long ID = 1L;
        Cabinet cabinet = new Cabinet(100);

        doReturn(Optional.of(cabinet))
                .when(repository)
                .findById(ID);

        Cabinet loaded = service.getById(ID);

        verify(repository).findById(ID);

        assertNotNull(loaded);
        assertEquals(100, loaded.getNumber());
    }

    @Test
    void getByIdWrong() {
        Long ID = 1L;

        doReturn(Optional.empty())
                .when(repository)
                .findById(ID);

        assertThrows(ResourceNotFoundException.class, () -> service.getById(ID));
    }

    @Test
    void getByUuid() {
        UUID uuid = UUID.randomUUID();
        Cabinet cabinet = new Cabinet(100);

        doReturn(Optional.of(cabinet))
                .when(repository)
                .findByUuid(uuid);

        Cabinet loaded = service.getByUuid(uuid);

        verify(repository).findByUuid(uuid);

        assertNotNull(loaded);
        assertEquals(100, loaded.getNumber());
    }

    @Test
    void getByUuidWrong() {
        UUID uuid = UUID.randomUUID();

        doReturn(Optional.empty())
                .when(repository)
                .findByUuid(uuid);

        assertThrows(ResourceNotFoundException.class, () -> service.getByUuid(uuid));
    }

    @Test
    void delete() {
        Long ID = 1L;

        doReturn(true)
                .when(repository)
                .existsById(ID);

        assertTrue(service.delete(ID));

        verify(repository).deleteById(ID);
    }

    @Test
    void deleteWrongId() {
        Long ID = 1L;

        doReturn(false)
                .when(repository)
                .existsById(ID);

        verifyNoMoreInteractions(repository);

        assertThrows(ResourceNotFoundException.class, () -> service.delete(ID));
    }
}