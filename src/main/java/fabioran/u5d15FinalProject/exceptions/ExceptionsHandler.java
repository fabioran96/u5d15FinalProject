package fabioran.u5d15FinalProject.exceptions;

import fabioran.u5d12.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice // <-- OBBLIGATORIA
// 'Controller' specifico per la GESTIONE DELLE ECCEZIONI a livello globale
// E' un Controller tra virgolette perché qua non scriveremo degli endpoint
// Qua inseriremo uno o più metodi che gestiranno le eccezioni.
// Gestire le eccezioni in un web server vuol dire che quando si scatena una particolare eccezione
// devo inviare una risposta opportuna con tanto di status code e payload che comunichi in maniera
// corretta al client quale sia il problema
// Questa classe quindi ci serve per gestire le eccezioni in maniera CENTRALIZZATA, ovvero, non saranno
// i singoli endpoint a dover gestirle (con dei try-catch) ma tutte le eccezioni arriveranno a questa classe
// Una volta che arriveranno qua avremo dei metodi dedicati per gestirle una ad una. A livello di metodo
// avremo un'annotazione particolare @ExceptionHandler che ci consentirà di specificare quale eccezione
// debba venir gestita da tale metodo
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class) // Nelle parentesi indco quale eccezione debba venir gestita da questo metodo
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Lo status code deve essere 400
    public ErrorsResponseDTO handleBadRequest(BadRequestException ex) {
        // Questi handler mi consentono anche di accedere all'eccezione, utile per prendere il messaggio ad es
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleNotFound(NotFoundException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsResponseDTO handleGenericErrors(Exception ex) {
        ex.printStackTrace(); // Non dimentichiamoci che è ESTREMAMENTE UTILE sapere dove è stato generata l'eccezione per poterla fixare
        return new ErrorsResponseDTO("Problema lato server, giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}
