package io.github.robertpaivadf.domain.model;

import io.github.robertpaivadf.domain.enums.NomeRegra;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table (name="tb_regras")
public class Regras implements Serializable, GrantedAuthority {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING) //salvar como string no banco de dados
    @Column(nullable = false, unique = true)
    private NomeRegra nomeRegra;


    @Override
    public String getAuthority() {
        return this.nomeRegra.toString();
    }
}
