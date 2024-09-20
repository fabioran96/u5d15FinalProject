package fabioran.u5d15FinalProject.controllers;

import fabioran.u5d15FinalProject.entities.Location;
import fabioran.u5d15FinalProject.entities.Utente;
import fabioran.u5d15FinalProject.entities.enums.StatoUtente;
import fabioran.u5d15FinalProject.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/all")
    public List<Location> listaLocation() {
        return locationService.listaLocation();
    }

    @PostMapping("/create")
    public Location creaLocation(@RequestBody Location location, @AuthenticationPrincipal Utente utente) {
        if (utente.getStatoUtente() != StatoUtente.ORGANIZZATORE) {
            throw new RuntimeException("Autorizzazione negata");
        }
        return locationService.creaLocation(location);
    }

    @PutMapping("/update")
    public Location modificaLocation(@RequestBody Location location, @AuthenticationPrincipal Utente utente) {
        if (utente.getStatoUtente() != StatoUtente.ORGANIZZATORE) {
            throw new RuntimeException("Autorizzazione negata");
        }
        return locationService.modificaLocation(location);
    }

    @DeleteMapping("/delete/{locationId}")
    public void eliminaLocation(@PathVariable UUID locationId, @AuthenticationPrincipal Utente utente) {
        if (utente.getStatoUtente() != StatoUtente.ORGANIZZATORE) {
            throw new RuntimeException("Autorizzazione negata");
        }
        locationService.eliminaLocation(locationId);
    }
}
