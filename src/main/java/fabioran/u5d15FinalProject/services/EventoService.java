package fabioran.u5d15FinalProject.services;

import fabioran.u5d15FinalProject.entities.Evento;
import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Evento creaEvento(Evento evento, Utente organizzatore) {
        evento.setOrganizzatore(organizzatore);
        evento.setPostiDisponibili(evento.getPostiTotali());
        return eventoRepository.save(evento);
    }

    public Evento modificaEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void eliminaEvento(UUID id_Evento) {
        eventoRepository.deleteById(id_Evento);
    }

    public List<Evento> listaEventiDisponibili() {
        return eventoRepository.findAll();
    }
}
