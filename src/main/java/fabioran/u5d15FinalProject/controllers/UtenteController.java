package fabioran.u5d15FinalProject.controllers;

import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.payloads.NuovoUtenteDTO;
import fabioran.u5d15FinalProject.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAll(page, size, sortBy);
    }
    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }
    @PutMapping("/me")
    public Utente updateProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser, @RequestBody Utente body) {
        return this.utenteService.findByIdAndUpdate(currentAuthenticatedUser.getId(), body);
    }
    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        this.utenteService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }
    @GetMapping("/{userId}")
    public Utente findById(@PathVariable UUID userId) {
        return this.utenteService.findById(userId);
    }
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findByIdAndUpdate(@PathVariable UUID userId, @RequestBody Utente body) {
        return this.utenteService.findByIdAndUpdate(userId, body);
    }
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId) {
        this.utenteService.findByIdAndDelete(userId);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registraUtente(@RequestBody NuovoUtenteDTO nuovoUtenteDTO) {
        utenteService.salvaUtente(nuovoUtenteDTO);
        return ResponseEntity.ok("Utente registrato con successo");
    }
}
