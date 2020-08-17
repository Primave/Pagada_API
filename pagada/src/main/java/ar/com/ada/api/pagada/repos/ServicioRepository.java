package ar.com.ada.api.pagada.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.pagada.entities.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    @Query("Select s from Servicio s where s.empresa.empresaId = :empresaId") //JPQL
    List<Servicio> findAllEmpresaId(Integer empresaId);

    @Query("Select s from Servicio s where s.empresa.empresaId = :empresaId and s.estadoId = 0") //JPQL, en este caso, 0 es PENDIENTE
    List<Servicio> findAllPendientesEmpresaId(Integer empresaId);

    @Query("Select s from Servicio s where s.deudor.deudorId = :deudorId and s.estadoId = 0 and s.empresa.empresaId = :empresaId")
    List<Servicio> findAllPendientesPorDeudorIdyEmpresaId(Integer deudorId, Integer empresaId);

    @Query("Select s from Servicio s where s.deudor.deudorId = :deudorId and s.empresa.empresaId = :empresaId")
	List<Servicio> findAllPorDeudorIdyEmpresaId(Integer deudorId, Integer empresaId);

}