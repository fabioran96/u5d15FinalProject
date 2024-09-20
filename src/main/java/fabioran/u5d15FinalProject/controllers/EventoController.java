package fabioran.u5d15FinalProject.controllers;

import fabioran.u5d15FinalProject.entities.Evento;
import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.entities.enums.StatoUtente;
import fabioran.u5d15FinalProject.exceptions.UnauthorizedException;
import fabioran.u5d15FinalProject.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/all")
    public List<Evento> EventiDisponibili() {
        return eventoService.listaEventiDisponibili();
    }

    @PostMapping("/create")
    public Evento creaEvento(@RequestBody Evento evento, @AuthenticationPrincipal Utente utente) {
        if (utente.getStatoUtente() != StatoUtente.ORGANIZZATORE) {
            throw new UnauthorizedException("Non autorizzato");
        }
        return eventoService.creaEvento(evento, utente);
    }

    @PutMapping("/update")
    public Evento modificaEvento(@RequestBody Evento evento, @AuthenticationPrincipal Utente utente) {
        if (utente.getStatoUtente() != StatoUtente.ORGANIZZATORE) {
            throw new UnauthorizedException("Non autorizzato");
        }
        return eventoService.modificaEvento(evento);
    }

    @DeleteMapping("/delete/{eventId}")
    public void eliminaEvento(@PathVariable UUID eventId, @AuthenticationPrincipal Utente utente) {
        if (utente.getStatoUtente() != StatoUtente.ORGANIZZATORE) {
            throw new UnauthorizedException("Non autorizzato");
        }
        eventoService.eliminaEvento(eventId);
    }
}
