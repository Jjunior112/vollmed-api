package med.voll.api.application.services;

import med.voll.api.domain.dtos.user.DadosRegistro;
import med.voll.api.domain.models.User;
import med.voll.api.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public User createUser(DadosRegistro registro) {

        User user = new User(registro);

        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return repository.save(user);

    }

    public boolean existUser(String login) {
        var user = repository.findByLogin(login);

        if (user == null) {
            return false;
        }

        return true;

    }

}
