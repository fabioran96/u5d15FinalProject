package fabioran.u5d15FinalProject.entities;

import fabioran.u5d15FinalProject.entities.enums.StatoUtente;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente implements UserDetails {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private StatoUtente statoUtente;

    public Utente(String nome, String cognome, String email, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.statoUtente.name()));
    }
    @Override
    public String getUsername() {
        return this.email;
    }
}
