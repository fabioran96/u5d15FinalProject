package fabioran.u5d15FinalProject.services;

import fabioran.u5d15FinalProject.entities.Evento;
import fabioran.u5d15FinalProject.entities.Prenotazione;
import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.entities.enums.StatoPrenotazione;
import fabioran.u5d15FinalProject.exceptions.UnauthorizedException;
import fabioran.u5d15FinalProject.repositories.EventoRepository;
import fabioran.u5d15FinalProject.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Prenotazione prenotazione(UUID id_Evento, Utente utente) {
        Evento evento = eventoRepository.findById(id_Evento)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));
        if (evento.getPostiDisponibili() <= 0) {
            throw new UnauthorizedException("Non ci sono posti disponibili");
        }
        evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
        eventoRepository.save(evento);

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        prenotazione.setDataPrenotazione(LocalDateTime.now());
        prenotazione.setStatoPrenotazione(StatoPrenotazione.ATTIVA);
        return prenotazioneRepository.save(prenotazione);
    }

    public void annullaPrenotazione(UUID bookingId, Utente utente) {
        Prenotazione prenotazione = prenotazioneRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
        if (!prenotazione.getUtente().getId().equals(utente.getId())) {
            throw new UnauthorizedException("Non autorizzato ad annullare questa prenotazione");
        }
        prenotazione.setStatoPrenotazione(StatoPrenotazione.ANNULLATA);
        prenotazioneRepository.save(prenotazione);

        Evento evento = prenotazione.getEvento();
        evento.setPostiDisponibili(evento.getPostiDisponibili() + 1);
        eventoRepository.save(evento);
    }

    public List<Prenotazione> visualizzaPrenotazioniUtente(Utente utente) {
        return prenotazioneRepository.findByUtenteId(utente.getId());
    }
}
