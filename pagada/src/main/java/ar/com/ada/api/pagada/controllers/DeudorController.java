package ar.com.ada.api.pagada.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.pagada.entities.Deudor;
import ar.com.ada.api.pagada.models.request.DeudorRequest;
import ar.com.ada.api.pagada.models.response.GenericResponse;
import ar.com.ada.api.pagada.services.DeudorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DeudorController {

    @Autowired
    DeudorService deudorService;

    @GetMapping("/api/deudores")
    public ResponseEntity<List<Deudor>> obtenerDeudores() {
        List<Deudor> deudores = deudorService.listarDeudores();
        return ResponseEntity.ok(deudores);
    }

    @PostMapping("/api/deudores")
    public ResponseEntity<GenericResponse> crearDeudor(@RequestBody DeudorRequest dr) {
        GenericResponse gr = new GenericResponse();

        //
        Deudor deudor = deudorService.crearDeudor(dr.paisId, dr.tipoIdImpositivo, dr.idImpositivo, dr.nombre);

        if (deudor != null) {
            gr.isOk = true;
            gr.id = deudor.getDeudorId();
            gr.message = "Deudor cargado con éxito.";
            return ResponseEntity.ok(gr);
        }
        gr.isOk = false;
        gr.message = "No se pudo cargar al Deudor.";
        return ResponseEntity.badRequest().body(gr);
    }

}
