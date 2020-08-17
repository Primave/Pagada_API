package ar.com.ada.api.pagada.services;

import java.util.List;
import java.util.Optional;

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

    // Evitar pasar el model DeudorRequest que referencia a algo que pasa FRONT o
    // desde afuera, hacia la capa Service
    // Si se hace, usarlo con otro nombre o con otro tipo de funcionalidad.
    // O sea este metodo evitarlo hacerlo asi:
    // public DeudorValidacionEnum validarDeudorInfo(DeudorRequest req) {
    // return validarDeudorInfo(req.paisId, req.tipoIdImpositivo, req.idImpositivo,
    // req.nombre);
    // }

    public DeudorValidacionEnum validarDeudorInfo(Integer paisId, TipoIdImpositivoEnum tipoIdImpositivo,
            String idImpositivo, String nombre) {
        // Si es nulo, error
        if (idImpositivo == null)
            return DeudorValidacionEnum.ID_IMPOSITIVO_INVALIDO;

        // ID impositivo al menos de 11 digitos y maximo 20
        if (!(idImpositivo.length() >= 11 && idImpositivo.length() <= 20))
            return DeudorValidacionEnum.ID_IMPOSITIVO_INVALIDO;

        // "Hola mundo" -> "H", "o" , "l", "a"
        // 1forma: recorrer con foreach
        // 2 forma: transformar el string a un array de chars y recorrer el array con
        // fori
        // 3 forma: recorrer el string utilzando charIndex(posicion)
        // 4 forma: stream

        // 1forma: "A3939393"
        for (char caracter : idImpositivo.toCharArray()) {
            if (!Character.isDigit(caracter))
                return DeudorValidacionEnum.ID_IMPOSITIVO_INVALIDO;

        }

        // 2 forma: usando for i
        char[] idImpositivoComoArray = idImpositivo.toCharArray();
        for (int i = 0; i < idImpositivoComoArray.length; i++) {
            if (!Character.isDigit(idImpositivoComoArray[i])) {
                return DeudorValidacionEnum.ID_IMPOSITIVO_INVALIDO;
            }
        }
        // 3 forma: un for i en el string usando char index
        for (int i = 0; i < idImpositivo.length(); i++) {
            if (!Character.isDigit(idImpositivo.charAt(i))) {
                return DeudorValidacionEnum.ID_IMPOSITIVO_INVALIDO;
            }
        }

        // 4Forma: usando stream
        if (idImpositivo.chars().filter(c -> !Character.isDigit(c)).count() > 0)
            return DeudorValidacionEnum.ID_IMPOSITIVO_INVALIDO;

        if (nombre == null)
            return DeudorValidacionEnum.NOMBRE_INVALIDO;

        if (nombre.length() > 100)
            return DeudorValidacionEnum.NOMBRE_INVALIDO;

        // Si llego hassta aqui, es que todo lo de arriba, era valido
        return DeudorValidacionEnum.OK;
    }

    public enum DeudorValidacionEnum {
        OK, // Cuando esta todo validado ok
        NOMBRE_INVALIDO, // Nombre tenga algun problema
        ID_IMPOSITIVO_INVALIDO // ID impositivo tenga un problema
    }

    public Deudor buscarDeudorPorId(Integer deudorId) {
        Optional<Deudor> oDeudor = deudorRepo.findById(deudorId);
        if (oDeudor.isPresent()) {
            return oDeudor.get();
        } else {
            return null;
        }
    }

}