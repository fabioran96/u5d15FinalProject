package fabioran.u5d15FinalProject.repositories;

import fabioran.u5d15FinalProject.entities.Prenotazione;
import fabioran.u5d15FinalProject.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    List<Prenotazione> findByUtenteId(UUID id);
}
