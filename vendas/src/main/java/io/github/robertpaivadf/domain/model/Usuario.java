package io.github.robertpaivadf.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="tb_usuario")
public class Usuario implements UserDetails, Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String usuario;
    @Column(nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(name = "tb_regras_usuario",
                joinColumns = @JoinColumn(name="id_usuario"),
                inverseJoinColumns = @JoinColumn(name = "id_regra"))
    private List<Regras> regras;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.regras;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
