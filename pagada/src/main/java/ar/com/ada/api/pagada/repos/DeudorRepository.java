package ar.com.ada.api.pagada.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.pagada.entities.Deudor;

@Repository
public interface DeudorRepository extends JpaRepository<Deudor, Integer> {

}