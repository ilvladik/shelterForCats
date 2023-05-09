package ru.ilvladik.shelterforcats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilvladik.shelterforcats.models.Manager;
import java.util.Optional;

@Repository
public interface ManagersRepository extends JpaRepository<Manager, Integer> {
    Optional<Manager> findByUsername(String username);
}
