package fabioran.u5d15FinalProject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue
    private UUID id;
    private String titolo;
    private String descrizione;
    private LocalDateTime data;
    private int postiTotali;
    private int postiDisponibili;
    @ManyToOne
    @JoinColumn(name = "id_Organizzatore")
    private Utente organizzatore;
    @ManyToOne
    @JoinColumn(name = "id_Location")
    private Location location;
}

