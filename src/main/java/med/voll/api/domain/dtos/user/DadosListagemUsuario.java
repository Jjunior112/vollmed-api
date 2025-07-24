package med.voll.api.domain.dtos.user;

import med.voll.api.domain.models.User;

public record DadosListagemUsuario(String username) {
    public DadosListagemUsuario(User user) {
        this(user.getUsername());
    }
}
