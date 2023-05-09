package ru.ilvladik.shelterforcats.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ilvladik.shelterforcats.models.Manager;
import ru.ilvladik.shelterforcats.repositories.ManagersRepository;
import ru.ilvladik.shelterforcats.security.ManagerDetails;

import java.util.Optional;

@Service
@Transactional
public class ManagerDetailsService implements UserDetailsService {

    private final ManagersRepository managersRepository;

    @Autowired
    public ManagerDetailsService(ManagersRepository managersRepository) {
        this.managersRepository = managersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Manager> manager = managersRepository.findByUsername(username);
        if (manager.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден");
        return new ManagerDetails(manager.get());
    }
}
