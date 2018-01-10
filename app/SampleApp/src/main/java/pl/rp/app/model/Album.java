package pl.rp.app.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;

import pl.rp.app.controller.TestController;

@Entity
@Table(name="ER_ALBUM")
public class Album implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="artist_id")
	private Artist artist;
	@Size(min = 5)
	private String name;
	
	public Album() {
		
	}
	
	public Album(Artist artist, String name) {
		this.artist = artist;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "Album toString()");
		
		return "Album [Id=" + id + ", artist.name=" + artist.getName() + ", artist.surname=" + artist.getSurname() + ", name=" + name + "]";
	}
	
}
