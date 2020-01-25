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
@Table(name = "contrato")
@NamedQueries({
    @NamedQuery(name = "ContratoEntity.findAll", query = "SELECT c FROM ContratoEntity c"),
    @NamedQuery(name = "ContratoEntity.findByContratoMedico", query = "SELECT c FROM ContratoEntity c WHERE c.medico.id = :id"),
    @NamedQuery(name = "ContratoEntity.findById", query = "SELECT c FROM ContratoEntity c WHERE c.id = :id"),
    @NamedQuery(name = "ContratoEntity.findByFechaInicio", query = "SELECT c FROM ContratoEntity c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ContratoEntity.findByFechaFin", query = "SELECT c FROM ContratoEntity c WHERE c.fechaFin = :fechaFin")})
public class ContratoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "medico", referencedColumnName = "id")
    @ManyToOne
    private UsuarioEntity medico;

    public ContratoEntity() {
    }

    public ContratoEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public UsuarioEntity getMedico() {
        return medico;
    }

    public void setMedico(UsuarioEntity medico) {
        this.medico = medico;
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
        if (!(object instanceof ContratoEntity)) {
            return false;
        }
        ContratoEntity other = (ContratoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.ContratoEntity[ id=" + id + " ]";
    }
    
}
