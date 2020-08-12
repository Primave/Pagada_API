package ar.com.ada.api.pagada.entities;

import java.util.*;

import javax.persistence.*;

import ar.com.ada.api.pagada.entities.Pais.TipoIdImpositivoEnum;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Integer empresaId;
    @Column(name = "pais_id")
    private Integer paisId;
    @Column(name = "tipo_id_impositivo_id")
    private TipoIdImpositivoEnum tipoIdImpositivo;
    @Column(name = "id_impositivo")
    private String idImpositivo;
    private String nombre;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Servicio> serviciosQueOfrece = new ArrayList<>();

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
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

    public List<Servicio> getServiciosQueOfrece() {
        return serviciosQueOfrece;
    }

    public void setServiciosQueOfrece(List<Servicio> serviciosQueOfrece) {
        this.serviciosQueOfrece = serviciosQueOfrece;
    }

    // Relacion bidirecional para que los objetos se apunten entre si
    public void agregarServicio(Servicio servicio) {
        this.serviciosQueOfrece.add(servicio);
        servicio.setEmpresa(this);
    }

}