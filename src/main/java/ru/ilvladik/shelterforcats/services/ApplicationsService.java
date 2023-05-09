package ru.ilvladik.shelterforcats.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilvladik.shelterforcats.models.Application;
import ru.ilvladik.shelterforcats.repositories.ApplicationsRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApplicationsService {

    private final ApplicationsRepository applicationsRepository;

    @Autowired
    public ApplicationsService(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

    public List<Application> getAllApplications() {
        return applicationsRepository.findAllByOrderByCreatedAtAsc();
    }

    public Application getApplicationByPhone(String phone) {
        return applicationsRepository.findApplicationByPhone(phone);
    }

    public void save(Application application) {
        application.setCreatedAt(new Date());
        applicationsRepository.save(application);
    }

    public void delete(int id) {
        applicationsRepository.deleteById(id);
    }
}
