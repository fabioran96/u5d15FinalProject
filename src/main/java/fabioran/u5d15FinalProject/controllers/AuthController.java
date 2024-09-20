package fabioran.u5d15FinalProject.controllers;

import fabioran.u5d15FinalProject.exceptions.BadRequestException;
import fabioran.u5d15FinalProject.payloads.NuovoUtenteDTO;
import fabioran.u5d15FinalProject.payloads.NuovoUtenteRespDTO;
import fabioran.u5d15FinalProject.payloads.UtenteLoginDTO;
import fabioran.u5d15FinalProject.payloads.UtenteLoginRespDTO;
import fabioran.u5d15FinalProject.services.AuthService;
import fabioran.u5d15FinalProject.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService usersService;
    @PostMapping("/login")
    public UtenteLoginRespDTO loginN(@RequestBody UtenteLoginDTO payload) {
        return new UtenteLoginRespDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NuovoUtenteRespDTO save(@RequestBody @Validated NuovoUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new NuovoUtenteRespDTO(this.usersService.salvaUtente(body).getId());
        }
    }
}
