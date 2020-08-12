package ar.com.ada.api.pagada.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import ar.com.ada.api.pagada.entities.Deudor;
import ar.com.ada.api.pagada.entities.Pais.TipoIdImpositivoEnum;
import ar.com.ada.api.pagada.repos.DeudorRepository;

@Service
public class DeudorService {

    @Autowired
    DeudorRepository deudorRepo;

    public List<Deudor> listarDeudores() {
        return deudorRepo.findAll();
    }

    public void guardar(Deudor deudor) {
        deudorRepo.save(deudor);
    }

    public Deudor crearDeudor(Integer paisId, TipoIdImpositivoEnum tipoIdImpositivo, String idImpositivo,
            String nombre) {

        Deudor deudor = new Deudor();
        deudor.setPaisId(paisId);
        deudor.setTipoIdImpositivo(tipoIdImpositivo);
        deudor.setIdImpositivo(idImpositivo);
        deudor.setNombre(nombre);

        guardar(deudor);

        if (deudor.getDeudorId() == null)
            return null;

        return deudor;
    }

}