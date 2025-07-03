package project.loginsystem.infrastructure.repository;

import org.springframework.stereotype.Service;
import project.loginsystem.domain.model.User;
import project.loginsystem.domain.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id);
    }
}
