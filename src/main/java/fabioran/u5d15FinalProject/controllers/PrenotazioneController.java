package fabioran.u5d15FinalProject.controllers;

import fabioran.u5d15FinalProject.entities.Prenotazione;
import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/reserve/{eventId}")
    public Prenotazione prenotaEvento(@PathVariable UUID eventId, @AuthenticationPrincipal Utente utente) {
        return prenotazioneService.prenotazione(eventId, utente);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public void annullaPrenotazione(@PathVariable UUID bookingId, @AuthenticationPrincipal Utente utente) {
        prenotazioneService.annullaPrenotazione(bookingId, utente);
    }

    @GetMapping("/mybookings")
    public List<Prenotazione> visualizzaPrenotazioniUtente(@AuthenticationPrincipal Utente utente) {
        return prenotazioneService.visualizzaPrenotazioniUtente(utente);
    }
}
