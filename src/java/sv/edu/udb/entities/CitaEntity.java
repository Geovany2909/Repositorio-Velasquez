/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author geovany
 */
@Entity
@Table(name = "cita")
@NamedQueries({
    @NamedQuery(name = "CitaEntity.findAll", query = "SELECT c FROM CitaEntity c"),
    @NamedQuery(name = "CitaEntity.findByPaciente", query = "SELECT c FROM CitaEntity c WHERE c.idPaciente.tipoUsuario.id = 3 and c.idHorario.ortopeda.tipoUsuario.id = 2"),
    @NamedQuery(name = "CitaEntity.findByCitaMedicas", query = "SELECT c FROM CitaEntity c WHERE c.idHorario.ortopeda.id = :id"),
    @NamedQuery(name = "CitaEntity.findByCitaPac", query = "SELECT c FROM CitaEntity c WHERE c.idPaciente.id = :id"),
    @NamedQuery(name = "CitaEntity.findByCitaPacienteA", query = "SELECT c FROM CitaEntity c WHERE c.idPaciente.id = :id AND c.estado.estado = 'Aprobado'"),
    @NamedQuery(name = "CitaEntity.findById", query = "SELECT c FROM CitaEntity c WHERE c.id = :id"),
    @NamedQuery(name = "CitaEntity.findByFecha", query = "SELECT c FROM CitaEntity c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CitaEntity.findByObservaciones", query = "SELECT c FROM CitaEntity c WHERE c.observaciones = :observaciones")})
public class CitaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String observaciones;
    @JoinColumn(name = "idPaciente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UsuarioEntity idPaciente;
    @JoinColumn(name = "idHorario", referencedColumnName = "id")
    @ManyToOne
    private HorarioCitaEntity idHorario;
    @JoinColumn(name = "estado", referencedColumnName = "id")
    @ManyToOne
    private EstadoEntity estado;
    @JoinColumn(name = "producto", referencedColumnName = "id")
    @ManyToOne
    private ProductoEntity producto;

    public CitaEntity() {
    }

    public CitaEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public UsuarioEntity getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(UsuarioEntity idPaciente) {
        this.idPaciente = idPaciente;
    }

    public HorarioCitaEntity getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(HorarioCitaEntity idHorario) {
        this.idHorario = idHorario;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitaEntity)) {
            return false;
        }
        CitaEntity other = (CitaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.CitaEntity[ id=" + id + " ]";
    }

}
