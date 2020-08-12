package ar.com.ada.api.pagada.entities;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

//El servico mes a mes
@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicio_id")
    private Integer servicioId;

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id") // Esta es la FK
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "deudor_id", referencedColumnName = "deudor_id")
    private Deudor deudor;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_id", referencedColumnName = "tipo_servicio_id")
    private TipoServicio tipoServicio;
    @Column(name = "tipo_comprobante_id")
    private TipoComprobanteEnum tipoComprobante;
    @Column(name = "fecha_emision")
    private Date fechaEmision;
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
    private BigDecimal importe;
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Column(name = "estado_id")
    private EstadoEnum estadoId;

    // Owner de la relacion
    @OneToOne(mappedBy = "servicio", cascade = CascadeType.ALL)
    private Pago pago;

    public enum TipoComprobanteEnum {
        FACTURA, CONTRATO, IMPUESTO
    }

    public enum EstadoEnum {
        PENDIENTE, PAGADO, ANULADO
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Deudor getDeudor() {
        return deudor;
    }

    public void setDeudor(Deudor deudor) {
        this.deudor = deudor;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    // Relacion bidireccion
    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
        this.tipoServicio.getServiciosEmitidos().add(this);
    }

    public TipoComprobanteEnum getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public EstadoEnum getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(EstadoEnum estadoId) {
        this.estadoId = estadoId;
    }

    public Pago getPago() {
        return pago;
    }

    // Relacion bidireccional
    public void setPago(Pago pago) {
        this.pago = pago; // le pongo el pago al servicio
        pago.setServicio(this); // Le pongo el servicio al pago
    }

}