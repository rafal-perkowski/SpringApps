package pl.rp.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.rp.app.model.Artist;
import pl.rp.app.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistRestController {
	
	@Autowired
	private ArtistService artistService;
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveArtist(@RequestBody Artist artist) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ArtistRestController saveArtist(" + artist + ")");
		
		if(!artistService.isExist(artist.getName(), artist.getSurname())) {
			artistService.saveArtist(artist);
		}
		
		TestController.traceCounter(TestController.InsertType.OUT, "ArtistRestController saveArtist(" + artist + ")");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Artist> getAllArtists() {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "ArtistRestController getAllArtists()");
		
		return artistService.getAllArtists();
	}
}
