package ar.com.ada.api.pagada.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.ada.api.pagada.entities.Pais.TipoIdImpositivoEnum;

@Entity
@Table(name = "deudor")
public class Deudor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deudor_id")
    private Integer deudorId;
    @Column(name = "pais_id")
    private Integer paisId;
    @Column(name = "tipo_id_impositivo_id")
    private TipoIdImpositivoEnum tipoIdImpositivo;
    @Column(name = "id_impositivo")
    private String idImpositivo;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "deudor", cascade = CascadeType.ALL)
    private List<Servicio> serviciosASuNombre = new ArrayList<>();

    public Integer getDeudorId() {
        return deudorId;
    }

    public void setDeudorId(Integer deudorId) {
        this.deudorId = deudorId;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public TipoIdImpositivoEnum getTipoIdImpositivo() {
        return tipoIdImpositivo;
    }

    public void setTipoIdImpositivo(TipoIdImpositivoEnum tipoIdImpositivo) {
        this.tipoIdImpositivo = tipoIdImpositivo;
    }

    public String getIdImpositivo() {
        return idImpositivo;
    }

    public void setIdImpositivo(String idImpositivo) {
        this.idImpositivo = idImpositivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Servicio> getServiciosASuNombre() {
        return serviciosASuNombre;
    }

    public void setServiciosASuNombre(List<Servicio> serviciosASuNombre) {
        this.serviciosASuNombre = serviciosASuNombre;
    }

    // Relacion bidirecional para que los objetos se apunten entre si
    public void agregarServicio(Servicio servicio) {
        this.serviciosASuNombre.add(servicio);
        servicio.setDeudor(this);
    }
}