package fabioran.u5d15FinalProject.entities;

import fabioran.u5d15FinalProject.entities.enums.StatoPrenotazione;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime dataPrenotazione;
    @Enumerated(EnumType.STRING)
    private StatoPrenotazione statoPrenotazione;

    @ManyToOne
    @JoinColumn(name = "id_Utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_Evento")
    private Evento evento;

}
