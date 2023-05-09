package ru.ilvladik.shelterforcats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ilvladik.shelterforcats.models.Picture;

@Repository
public interface PicturesRepository extends JpaRepository<Picture, Integer> {
    Picture getPicturesByCatId(int id);
}
