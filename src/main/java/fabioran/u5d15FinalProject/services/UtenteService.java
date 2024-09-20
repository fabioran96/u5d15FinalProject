package fabioran.u5d15FinalProject.services;

import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.exceptions.BadRequestException;
import fabioran.u5d15FinalProject.exceptions.NotFoundException;
import fabioran.u5d15FinalProject.payloads.NuovoUtenteDTO;
import fabioran.u5d15FinalProject.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    
    public Utente salvaUtente(NuovoUtenteDTO body) {
        Utente utente = new Utente();
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        utente.setEmail(body.email());
        utente.setPassword(bcrypt.encode(body.password()));

        Utente nuovoUtente = new Utente(body.nome(),body.cognome(),body.email(),body.username(),body.password());
            return utenteRepository.save(nuovoUtente);
        }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }
    public Utente findById(UUID userId) {
        return this.utenteRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }
    public void findByIdAndDelete(UUID userId) {
        Utente found = this.findById(userId);
        this.utenteRepository.delete(found);
    }
    public Utente findByIdAndUpdate(UUID userId, Utente newUserData) {
        this.utenteRepository.findByEmail(newUserData.getEmail()).ifPresent(
                user -> {
                    throw new BadRequestException("L'email " + newUserData.getEmail() + " è già in uso!");
                }
        );
        Utente found = this.findById(userId);
        found.setNome(newUserData.getNome());
        found.setCognome(newUserData.getCognome());
        found.setEmail(newUserData.getEmail());
        found.setPassword(newUserData.getPassword());
        return this.utenteRepository.save(found);
    }
    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }
}
