package fabioran.u5d15FinalProject.services;

import fabioran.u5d15FinalProject.entities.Location;
import fabioran.u5d15FinalProject.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location creaLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location modificaLocation(Location location) {
        return locationRepository.save(location);
    }

    public void eliminaLocation(UUID locationId) {
        locationRepository.deleteById(locationId);
    }

    public List<Location> listaLocation() {
        return locationRepository.findAll();
    }

    public Location trovaPerId(UUID id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trovata"));
    }

}
