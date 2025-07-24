package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.application.services.UserService;
import med.voll.api.domain.dtos.user.DadosListagemUsuario;
import med.voll.api.domain.dtos.user.dadosRegistro;
import med.voll.api.domain.models.User;
import med.voll.api.domain.dtos.user.DadosLogin;
import med.voll.api.domain.dtos.user.DadosTokenJwt;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired

    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosLogin login) {

        var authToken = new UsernamePasswordAuthenticationToken(login.login(), login.password());

        var authentication = authenticationManager.authenticate(authToken);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));

    }

    @PostMapping("/register")
    @SecurityRequirement(name = "bearer-key")

    public ResponseEntity register(@RequestBody @Valid dadosRegistro registro) {

        User response = userService.createUser(registro);

        return ResponseEntity.ok().body(new DadosListagemUsuario(response));

    }


}
