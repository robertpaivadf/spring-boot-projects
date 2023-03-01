package io.github.robertpaivadf.config;

import io.github.robertpaivadf.domain.model.Usuario;
import io.github.robertpaivadf.domain.repositories.RepUsuario;
import io.github.robertpaivadf.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepUsuario repUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repUsuario.findByUsuario(username)
                .orElseThrow(() -> new RegraNegocioException("User Not Found with username: " + username));
        return usuario;
    }
}
