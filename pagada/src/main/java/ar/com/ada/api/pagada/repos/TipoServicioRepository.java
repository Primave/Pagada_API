package ar.com.ada.api.pagada.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.pagada.entities.TipoServicio;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {

}