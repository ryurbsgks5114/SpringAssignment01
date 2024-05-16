package com.sparta.assignment01.repo;

import com.sparta.assignment01.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {

}
