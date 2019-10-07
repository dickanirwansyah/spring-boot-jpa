package com.rnd.springapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass
public abstract class Person extends ModelAudit{

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    public Person(){}

    public Person(LocalDateTime createdAt, LocalDateTime updatedAt, String firstname, String lastname) {
        super(createdAt, updatedAt);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
