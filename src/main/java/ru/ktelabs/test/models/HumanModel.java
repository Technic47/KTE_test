package ru.ktelabs.test.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.AbstractDto;

import java.util.*;

import static ru.ktelabs.test.models.Gender.UNKNOWN;

@MappedSuperclass
public abstract class HumanModel extends AbstractEntity implements TicketHandler {
    @NotNull
    protected String firstName;
    @NotNull
    protected String secondName;
    protected String givenName;
    @NotNull
    @JsonProperty(value = "type")
    @Enumerated(EnumType.STRING)
    protected Gender gender;
    @JsonFormat(pattern = "yyyy-MM-DD")
    protected Calendar birthDate;
    @Transient
    protected int age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Calendar created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Calendar updated;

    @OneToMany
    protected Set<Ticket> tickets;

    public HumanModel() {
        super();
        this.firstName = "empty";
        this.secondName = "empty";
        this.givenName = "empty";
        this.gender = UNKNOWN;
        this.created = Calendar.getInstance();
    }

    public HumanModel(String firstName, String secondName, String givenName, Gender gender, Calendar birthDate) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.givenName = givenName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.created = Calendar.getInstance();
        this.age = new GregorianCalendar().get(Calendar.YEAR) - birthDate.get((Calendar.YEAR));
    }

    public HumanModel(Long id, UUID uuid, String firstName, String secondName, String givenName, Gender gender, Calendar birthDate, int age, Calendar created, Calendar updated, Set<Ticket> tickets) {
        super(id, uuid);
        this.firstName = firstName;
        this.secondName = secondName;
        this.givenName = givenName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
        this.created = created;
        this.updated = updated;
        this.tickets = tickets;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
        this.age = new GregorianCalendar().get(Calendar.YEAR) - birthDate.get((Calendar.YEAR));
    }

    public int getAge() {
        return age;
    }

    public Calendar getCreated() {
        return created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }


    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HumanModel)) return false;
        HumanModel that = (HumanModel) o;
        return age == that.age && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(givenName, that.givenName) && gender == that.gender && Objects.equals(birthDate, that.birthDate) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated) && Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, givenName, gender, birthDate, age, created, updated, tickets);
    }
}
