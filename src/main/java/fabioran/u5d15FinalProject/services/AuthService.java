package fabioran.u5d15FinalProject.services;

import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.exceptions.UnauthorizedException;
import fabioran.u5d15FinalProject.payloads.UtenteLoginDTO;
import fabioran.u5d15FinalProject.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;
    public String checkCredentialsAndGenerateToken(UtenteLoginDTO body) {
        Utente found = this.utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
