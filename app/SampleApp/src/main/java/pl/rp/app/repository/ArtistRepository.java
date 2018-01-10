package pl.rp.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.rp.app.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
	Artist getByNameAndSurname(String name, String surname);
	List <Artist> findAll();
}
