package ru.ilvladik.shelterforcats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilvladik.shelterforcats.models.Cat;

@Repository
public interface CatsRepository extends JpaRepository<Cat, Integer> {}
