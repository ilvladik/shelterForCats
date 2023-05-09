package ru.ilvladik.shelterforcats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilvladik.shelterforcats.models.Application;

import java.util.List;

@Repository
public interface ApplicationsRepository extends JpaRepository<Application, Integer> {
    List<Application> findAllByOrderByCreatedAtAsc();
    Application findApplicationByPhone(String phone);
}
