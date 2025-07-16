package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.domain.User;
import med.voll.api.dtos.user.DadosLogin;
import med.voll.api.dtos.user.DadosTokenJwt;
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
@RequestMapping("/login")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosLogin login) {

        var authToken = new UsernamePasswordAuthenticationToken(login.login(),login.password());

        var authentication = authenticationManager.authenticate(authToken);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));

    }

}
