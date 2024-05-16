package com.sparta.assignment01.repo;

import com.sparta.assignment01.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAllByOrderByCreatedAtDesc();

}
