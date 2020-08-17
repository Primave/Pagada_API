package ar.com.ada.api.pagada.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ar.com.ada.api.pagada.entities.TipoServicio;
import ar.com.ada.api.pagada.models.response.GenericResponse;
import ar.com.ada.api.pagada.services.ServicioService;

@RestController
public class TipoServicioController {

    @Autowired
    ServicioService servicioService;

    @GetMapping("/api/tipos-servicios")
    public ResponseEntity<List<TipoServicio>> listarTipoServicios() {
        List<TipoServicio> tipoServicios = servicioService.listarTipoServicios();
        return ResponseEntity.ok(tipoServicios);
    }

    @PostMapping("/api/tipos-servicios")
    public ResponseEntity<GenericResponse> crearTipoServicio(@RequestBody TipoServicio tipo) {
        GenericResponse genericResponse = new GenericResponse();
        // to do hacer que alguien cree el tipo de servicio
        boolean resultadoCreacion = servicioService.crearTipoServicio(tipo);
        if (resultadoCreacion) {
            genericResponse.isOk = true;
            genericResponse.message = "el servicio fue creado con exito";
            genericResponse.id = tipo.getTipoServicioId();
            return ResponseEntity.ok(genericResponse);
        } else {
            genericResponse.isOk = false;
            genericResponse.message = "el servicio ya existe";
            genericResponse.id = tipo.getTipoServicioId();
            return ResponseEntity.badRequest().body(genericResponse);
        }
    }

} // ya podemos llorar? u.u
