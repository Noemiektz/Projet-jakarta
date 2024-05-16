package fr.efrei.test.model;
import jakarta.persistence.*;


@Entity
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	public Billet() {}
	public Billet(int id) {
		this.id = id;
		
	}

	public int getId() {
		return id;
	}

	public void setId(Int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}


}
