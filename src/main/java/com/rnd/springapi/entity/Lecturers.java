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
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "lecturers")
public class Lecturers extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long salary;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "lecturers_students",
    joinColumns = {@JoinColumn(name = "lecturers_id")},
            inverseJoinColumns = {@JoinColumn(name = "students_id")})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Students> students = new HashSet<>();

    public Lecturers(){}

    public Lecturers(LocalDateTime createdAt, LocalDateTime updatedAt, String firstname, String lastname, int id, Long salary, Set<Students> students) {
        super(createdAt, updatedAt, firstname, lastname);
        this.id = id;
        this.salary = salary;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Set<Students> getStudents() {
        return students;
    }

    public void setStudents(Set<Students> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Lecturers{" +
                "id=" + id +
                ", salary=" + salary +
                ", students=" + students +
                '}';
    }
}
