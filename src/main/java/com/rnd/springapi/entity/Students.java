package com.rnd.springapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rnd.springapi.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "students")
public class Students extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float gpa;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST},
    mappedBy = "students")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Lecturers> lecturers = new HashSet<>();

    public Students(){}

    public Students(LocalDateTime createdAt, LocalDateTime updatedAt, String firstname, String lastname, int id, float gpa, Set<Lecturers> lecturers) {
        super(createdAt, updatedAt, firstname, lastname);
        this.id = id;
        this.gpa = gpa;
        this.lecturers = lecturers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Set<Lecturers> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturers> lecturers) {
        this.lecturers = lecturers;
    }
}
