package ar.com.ada.api.pagada.controllers;

import ar.com.ada.api.pagada.entities.Empresa;
import ar.com.ada.api.pagada.services.*;
import ar.com.ada.api.pagada.services.EmpresaService.EmpresaValidacionEnum;
import ar.com.ada.api.pagada.models.request.EmpresaRequest;
import ar.com.ada.api.pagada.models.response.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpresaController {

    /*
     * Crear/Obtener empresas GET /api/empresas POST /api/empresas : crea una
     * empresa. Se debe validar que el numero de Id Imposito sean SOLO numeros de al
     * menos 11 digitos(no usar REGEX) Y maximo hasta 20 nros y el nombre
     * obligatorio y hasta 100 chars.
     */
    // En HTTP, tienen que devolver una estructura, llamada ResponseData
    // ResponseInfo/ResponseData: tiene:
    // Codigo De error http: 200, 201, 400,401, 404, 500, 503
    // Headers: ContentType(que tipo de respuesta estoy enviando-JSON)
    // Header Content-Length: cuantos bytes mando
    // Header Cache Info:
    // Body: en esta seccion viene lo que realmente queremos devolver, en este caso
    // una lista de empresas
    // Todo lo de arriba es un response entity.
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/api/empresas")
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> empresas;
        // to do :obtener lista de empresas a atravez del service y lo guardamos en la
        // varable empresas

        empresas = empresaService.listarEmpresas();
        return ResponseEntity.ok(empresas);

    }

    @PostMapping("/api/empresas")
    public ResponseEntity<GenericResponse> crearEmpresa(@RequestBody EmpresaRequest empR) {
        GenericResponse gr = new GenericResponse();

        // to do: hacer validaciones y crear la empresa a traves del service

        Empresa emp = new Empresa();
        emp.setPaisId(empR.paisId);
        emp.setTipoIdImpositivo(empR.tipoIdImpositivo);
        emp.setIdImpositivo(empR.idImpositivo);
        emp.setNombre(empR.nombre);

        // Este metodo no es tan extensible que el de abajo
        // porque depeden de los parametros
        // empresaService.validarNombreEIdImpositivo(empR.nombre, empR.idImpositivo);

        EmpresaValidacionEnum resultadoValidacion = empresaService.validarEmpresa(emp);
        if (resultadoValidacion != EmpresaValidacionEnum.OK) {
            gr.isOk = false;
            gr.message = "No se pudo validar la empresa " + resultadoValidacion.toString();

            return ResponseEntity.badRequest().body(gr); // http 400
        }

        empresaService.crearEmpresa(emp);

        // O haciendo esto
        // Empresa empresa = empresaService.crearEmpresa(empR.paisId,
        // empR.tipoIdImpositivo, empR.idImpositivo, empR.nombre);

        if (emp.getEmpresaId() != null) {
            gr.isOk = true;
            gr.id = emp.getEmpresaId();
            gr.message = "Empresa creada con exito";
            return ResponseEntity.ok(gr);
        }

        gr.isOk = false;
        gr.message = "No se pudo crear la empresa";

        return ResponseEntity.badRequest().body(gr); // http 400

    }

}