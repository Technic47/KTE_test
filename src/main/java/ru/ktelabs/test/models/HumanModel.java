package ru.ktelabs.test.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

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

    public HumanModel(Long id, String firstName, String secondName, String givenName, Calendar birthDate) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.givenName = givenName;
        this.birthDate = birthDate;
        this.created = Calendar.getInstance();
        this.age = new GregorianCalendar().get(Calendar.YEAR) - birthDate.get((Calendar.YEAR));
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
