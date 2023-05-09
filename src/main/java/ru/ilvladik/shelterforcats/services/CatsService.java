package ru.ilvladik.shelterforcats.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilvladik.shelterforcats.models.Cat;
import ru.ilvladik.shelterforcats.models.Picture;
import ru.ilvladik.shelterforcats.repositories.CatsRepository;
import ru.ilvladik.shelterforcats.repositories.PicturesRepository;

import java.util.List;

@Service
@Transactional
public class CatsService {

    private final CatsRepository catsRepository;
    private final PicturesRepository picturesRepository;

    @Autowired
    public CatsService(CatsRepository catsRepository, PicturesRepository picturesRepository) {
        this.catsRepository = catsRepository;
        this.picturesRepository = picturesRepository;
    }

    public List<Cat> findAllCats() {
        return catsRepository.findAll();
    }

    public Cat findCat(int id) {
        return catsRepository.findById(id).orElseThrow();
    }

    public void save(Cat cat) {
        catsRepository.save(cat);
    }

    public void delete(int id) {
        catsRepository.deleteById(id);
    }

    public Picture getPictureByCatId(int id) {
        return picturesRepository.getPicturesByCatId(id);
    }

}
