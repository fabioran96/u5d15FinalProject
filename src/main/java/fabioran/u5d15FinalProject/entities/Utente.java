package fabioran.u5d15FinalProject.entities;

import fabioran.u5d15FinalProject.entities.enums.StatoUtente;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "utenti")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private StatoUtente statoUtente;

}
