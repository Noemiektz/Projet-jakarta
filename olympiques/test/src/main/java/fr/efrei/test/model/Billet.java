package fr.efrei.test.model;
import jakarta.persistence.*;


@Entity
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String uuid;

	public Billet() {}
	public Billet(int id) {
		this.id = id;
		
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}


}
