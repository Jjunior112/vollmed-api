package med.voll.api.infra.security;

import med.voll.api.domain.dtos.user.DadosRegistro;
import med.voll.api.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
public class InitialUserCreator {

    @Value("${api.security.user}")
    private String username;
    @Value("${api.security.password}")
    private String password;
    @Autowired
    private UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void createInitialUser() {
        if (!userService.existUser(username)) {
            DadosRegistro registro = new DadosRegistro(username, password);
            userService.createUser(registro);
            System.out.println("Usuário inicial criado: " + username);
        }

    }
}