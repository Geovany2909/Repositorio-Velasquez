/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author geovany
 */
@Entity
@Table(name = "estado")
@NamedQueries({
    @NamedQuery(name = "EstadoEntity.findAll", query = "SELECT e FROM EstadoEntity e WHERE e.id = 1 OR e.id = 2 OR e.id = 3 OR e.id = 4"),
    @NamedQuery(name = "EstadoEntity.findByEstadoH", query = "SELECT e FROM EstadoEntity e WHERE e.id = 5 OR e.id = 6 OR e.id = 7"),
    @NamedQuery(name = "EstadoEntity.findByOne", query = "SELECT e FROM EstadoEntity e WHERE e.id = 1"),
    @NamedQuery(name = "EstadoEntity.findByFin", query = "SELECT e FROM EstadoEntity e WHERE e.id = 3"),

    @NamedQuery(name = "EstadoEntity.findById", query = "SELECT e FROM EstadoEntity e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoEntity.findByEstado", query = "SELECT e FROM EstadoEntity e WHERE e.estado = :estado")})
public class EstadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String estado;
    @OneToMany(mappedBy = "estadoH")
    private List<HorarioCitaEntity> horarioCitaEntityList;
    @OneToMany(mappedBy = "estado")
    private List<CitaEntity> citaEntityList;

    public EstadoEntity() {
    }

    public EstadoEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<HorarioCitaEntity> getHorarioCitaEntityList() {
        return horarioCitaEntityList;
    }

    public void setHorarioCitaEntityList(List<HorarioCitaEntity> horarioCitaEntityList) {
        this.horarioCitaEntityList = horarioCitaEntityList;
    }

    public List<CitaEntity> getCitaEntityList() {
        return citaEntityList;
    }

    public void setCitaEntityList(List<CitaEntity> citaEntityList) {
        this.citaEntityList = citaEntityList;
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
        if (!(object instanceof EstadoEntity)) {
            return false;
        }
        EstadoEntity other = (EstadoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.EstadoEntity[ id=" + id + " ]";
    }

}
