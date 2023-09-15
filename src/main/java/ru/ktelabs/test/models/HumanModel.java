package ru.ktelabs.test.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import java.util.Calendar;
import java.util.GregorianCalendar;

@MappedSuperclass
public class HumanModel extends AbstractEntity {
    private String firstName;
    private String secondName;
    private String givenName;
    private Calendar birthDate;
    @Transient
    private int age;
    private Calendar created;
    private Calendar updated;

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

    public String getSecondName() {
        return secondName;
    }

    public String getGivenName() {
        return givenName;
    }

    public Calendar getBirthDate() {
        return birthDate;
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

    public int getAge() {
        return age;
    }
}
