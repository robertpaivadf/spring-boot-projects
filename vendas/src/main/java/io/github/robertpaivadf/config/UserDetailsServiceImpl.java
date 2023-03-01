package io.github.robertpaivadf.config;

import io.github.robertpaivadf.domain.model.Usuario;
import io.github.robertpaivadf.domain.repositories.RepUsuario;
import io.github.robertpaivadf.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //Esse foi usado para que o método findByUsuario também busque a coleção de regras dos usuários
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepUsuario repUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repUsuario.findByUsuario(username)
                .orElseThrow(() -> new RegraNegocioException("User Not Found with username: " + username));
        return new User(usuario.getUsuario(), usuario.getSenha(), true,true,true,true,usuario.getAuthorities());
    }
}
