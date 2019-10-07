package com.rnd.springapi.controller;

import com.rnd.springapi.entity.Lecturers;
import com.rnd.springapi.entity.Students;
import com.rnd.springapi.exception.ResourceNotFoundExceptions;
import com.rnd.springapi.repository.LecturersRepository;
import com.rnd.springapi.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/lecturers") //endpoints
public class LecturersController {

    @Autowired
    private LecturersRepository lecturersRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @PostMapping
    public Lecturers save(@RequestBody @Valid Lecturers lecturers){
        return lecturersRepository.save(lecturers);
    }

    @PutMapping
    public Lecturers update(@RequestBody @Valid Lecturers lecturers){
        return this.lecturersRepository.findById(lecturers.getId()).map((currentLecturers) -> {
            currentLecturers.setFirstname(lecturers.getFirstname());
            currentLecturers.setLastname(lecturers.getLastname());
            currentLecturers.setSalary(lecturers.getSalary());
            return this.lecturersRepository.save(currentLecturers);
        }).orElseThrow(() -> new ResourceNotFoundExceptions("lecturers ", lecturers.getId()));
    }

    @GetMapping(value = "/by")
    public Lecturers getId(@RequestParam(value = "id")int id){
        return this.lecturersRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundExceptions("lecturers", id));
    }

    @GetMapping
    public Page<Lecturers> listLecturePage(Pageable pageable){
        return this.lecturersRepository.findAll(pageable);
    }

    @GetMapping(value = "/{lectureId}/students")
    public Set<Students> getStudents(@PathVariable(value = "lectureId")int lectureId){
        return this.lecturersRepository.findById(lectureId)
                .map((lecturer) -> {
                    return lecturer.getStudents();
                }).orElseThrow(() -> new ResourceNotFoundExceptions("lecturers", lectureId));
    }

    @PostMapping(value = "/{lectureId}/students/{studentId}")
    public Set<Students> addStudent(@PathVariable("lectureId")int lectureId,
                                    @PathVariable("studentId")int studentId){
        Students students = this.studentsRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundExceptions());

        return this.lecturersRepository.findById(lectureId)
                .map((lectures) -> {
                    lectures.getStudents().add(students);
                    return lecturersRepository.save(lectures).getStudents();
                }).orElseThrow(() -> new ResourceNotFoundExceptions("lecturers", lectureId));
    }
}
