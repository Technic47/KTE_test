package ru.ktelabs.test.repositories;

import org.springframework.stereotype.Repository;
import ru.ktelabs.test.models.Doctor;

@Repository
public interface DoctorRepository extends CommonRepository<Doctor> {
}
