package ru.ktelabs.test.repositories;

import org.springframework.stereotype.Repository;
import ru.ktelabs.test.models.ArchiveTicket;

import java.util.Optional;

@Repository
public interface ArchiveTicketRepository extends CommonRepository<ArchiveTicket> {
    Optional<ArchiveTicket> findByOldId(Long oldId);
}
