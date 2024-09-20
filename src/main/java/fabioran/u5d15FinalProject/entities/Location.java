package fabioran.u5d15FinalProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "location")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Location {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String indirizzo;
    private String citta;
    private int numero_posti;
}
