package ru.ktelabs.test.models;

import org.junit.jupiter.api.Test;
import ru.ktelabs.test.models.builders.DoctorDTOBuilder;
import ru.ktelabs.test.models.dto.DoctorDTO;

import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ktelabs.test.models.Gender.MALE;

class DoctorTest {
    @Test
    void creationTest() {
        DoctorDTO dto = new DoctorDTOBuilder().setFirstName("pavel").setSecondName("kuznetsov").setGivenName("andreevich").setGender(MALE).setBirthDate(new GregorianCalendar()).setSpeciality("dentist").createDoctorDTO();

        Doctor doctor = new Doctor(dto);
        assertThat(doctor.getUuid()).isNotNull();
        assertThat(doctor.getFirstName())
                .isNotNull()
                .isEqualTo("pavel");
        assertThat(doctor.getSecondName())
                .isNotNull()
                .isEqualTo("kuznetsov");
        assertThat(doctor.getGivenName())
                .isNotNull()
                .isEqualTo("andreevich");
        assertThat(doctor.getGender())
                .isNotNull()
                .isEqualTo(MALE);
        assertThat(doctor.getBirthDate())
                .isNotNull();
        assertThat(doctor.getSpeciality())
                .isNotNull()
                .isEqualTo("dentist");
        assertThat(doctor.getAge())
                .isNotNull();
    }
}