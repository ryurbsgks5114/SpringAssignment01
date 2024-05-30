package com.sparta.assignment01.repo;

import com.sparta.assignment01.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByOrderByCreatedAtDesc();

}
