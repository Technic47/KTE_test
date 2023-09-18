package ru.ktelabs.test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.dto.CabinetDTO;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Cabinet extends AbstractEntity {
    @NotNull
    @Column(unique = true)
    private int number;
    @OneToMany
    private Set<TimeSlot> timeslots;

    public Cabinet(int number) {
        super();
        this.number = number;
    }

    public Cabinet(Long id, UUID uuid, int number, Set<TimeSlot> timeslots) {
        super(id, uuid);
        this.number = number;
        this.timeslots = timeslots;
    }

    public Cabinet(CabinetDTO dto){
        super();
        this.number = dto.getNumber();
    }

    @Override
    public AbstractDto createDTO() {
        return new CabinetDTO(this);
    }

    public Cabinet() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(Set<TimeSlot> timeslots) {
        this.timeslots = timeslots;
    }
}
