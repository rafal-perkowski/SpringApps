package pl.rp.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rp.app.controller.TestController;
import pl.rp.app.model.Artist;
import pl.rp.app.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Override
	public List<Artist> getAllArtists() {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "ArtistServiceImpl getAllArtists()");
		
		return artistRepository.findAll();
	}
	
	@Override
	public boolean isExist(String name, String surname) {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "ArtistServiceImpl isExist(" + name + ", " + surname + ")");
		
		return artistRepository.getByNameAndSurname(name, surname)!=null ? true : false;
	}
	
	@Override
	public void saveArtist(Artist artist) {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "ArtistServiceImpl saveArtist(" + artist + ")");
		
		artistRepository.save(artist);
	}
}