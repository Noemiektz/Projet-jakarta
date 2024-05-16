package fr.efrei.test.repository;

import fr.efrei.test.model.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BilletRepository extends JpaRepository<Billet, String> {

	// SELECT * from Student where deleted_at is null
	List<Billet> findAllByDeletedAtNull();

	// SELECT * FROM Student where uuid = ?
	Optional<Billet> findOneByUuid(String uuid);

	Billet save(Billet student);

	void deleteByUuid(String uuid);
}
