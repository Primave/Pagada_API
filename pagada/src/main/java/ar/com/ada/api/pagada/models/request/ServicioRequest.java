package ar.com.ada.api.pagada.models.request;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.ada.api.pagada.entities.Servicio.EstadoEnum;
import ar.com.ada.api.pagada.entities.Servicio.TipoComprobanteEnum;

public class ServicioRequest {

    public Integer empresaId;

    public Integer deudorId;

    public Integer tipoServicioId;

    public TipoComprobanteEnum tipoComprobanteId;

    public String numero;

    public Date fechaEmision;

    public Date fechaVencimiento;

    public BigDecimal importe;

    public String moneda;

    public String codigoBarras;

    public EstadoEnum estadoId;

}
