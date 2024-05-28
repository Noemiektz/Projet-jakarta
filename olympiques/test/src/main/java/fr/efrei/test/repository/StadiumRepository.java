package fr.efrei.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.efrei.test.model.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}
