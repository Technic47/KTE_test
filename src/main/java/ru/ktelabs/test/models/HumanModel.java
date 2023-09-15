package ru.ktelabs.test.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.UUID;

@MappedSuperclass
public class HumanModel extends AbstractEntity implements TicketHandler {
    private String firstName;
    private String secondName;
    private String givenName;
    private Calendar birthDate;
    @Transient
    private int age;
    private Calendar created;
    private Calendar updated;

    @OneToMany
    private Set<Ticket> tickets;

    public HumanModel() {
        this.firstName = "empty";
        this.secondName = "empty";
        this.givenName = "empty";
        this.created = Calendar.getInstance();
    }

    public HumanModel(String firstName, String secondName, String givenName, Calendar birthDate) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.givenName = givenName;
        this.birthDate = birthDate;
        this.created = Calendar.getInstance();
        this.age = new GregorianCalendar().get(Calendar.YEAR) - birthDate.get((Calendar.YEAR));
    }

    public HumanModel(Long id, UUID uuid, String firstName, String secondName, String givenName, Calendar birthDate, int age, Calendar created, Calendar updated, Set<Ticket> tickets) {
        super(id, uuid);
        this.firstName = firstName;
        this.secondName = secondName;
        this.givenName = givenName;
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
}
