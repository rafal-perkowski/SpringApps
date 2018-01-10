package pl.rp.app.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import pl.rp.app.controller.TestController;

@Entity
@Table(name="ER_ARTIST")
public class Artist implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String surname;
	
	@JsonManagedReference
	@OneToMany(mappedBy="artist", cascade = CascadeType.ALL)
	private Set<Album> albums = new HashSet<Album>();
	
	public Artist() {
		
	}
	
	public Artist(String name, String surname) {
		
		this.name = name;
		this.surname = surname;
	}
	
	public void addAlbum(Album album) {
		
		albums.add(album);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "Artist toString()");
		
		return "Artist [id=" + id + ", name=" + name + ", surname=" + surname + ", albums=" + albums + "]";
	}
	
}

