package ar.com.ada.api.pagada.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.ada.api.pagada.entities.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    // en este caso estamos forzando a que no se use el Optional
    // lo que hay que cambiar es el tipo de parametro a int en vez de Integer
    // por defecto el de Integer devuelve un Optional<Empresa>
    Empresa findById(int id);
}