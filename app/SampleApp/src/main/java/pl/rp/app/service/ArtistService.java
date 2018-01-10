package pl.rp.app.service;

import java.util.List;
import pl.rp.app.model.Artist;

public interface ArtistService {
	List<Artist> getAllArtists();
	boolean isExist(String name, String surname);
	void saveArtist(Artist artist);
}
