package com.rnd.springapi.repository;

import com.rnd.springapi.entity.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturersRepository extends JpaRepository<Lecturers, Integer> {
}
