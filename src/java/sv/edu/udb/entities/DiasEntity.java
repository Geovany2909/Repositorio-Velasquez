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
@Table(name = "dias")
@NamedQueries({
    @NamedQuery(name = "DiasEntity.findAll", query = "SELECT d FROM DiasEntity d"),
    @NamedQuery(name = "DiasEntity.findById", query = "SELECT d FROM DiasEntity d WHERE d.id = :id"),
    @NamedQuery(name = "DiasEntity.findByDia", query = "SELECT d FROM DiasEntity d WHERE d.dia = :dia")})
public class DiasEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String dia;
    @OneToMany(mappedBy = "dia")
    private List<HorarioCitaEntity> horarioCitaEntityList;
    @OneToMany(mappedBy = "dia")
    private List<DiasHabilesEntity> diasHabilesEntityList;

    public DiasEntity() {
    }

    public DiasEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public List<HorarioCitaEntity> getHorarioCitaEntityList() {
        return horarioCitaEntityList;
    }

    public void setHorarioCitaEntityList(List<HorarioCitaEntity> horarioCitaEntityList) {
        this.horarioCitaEntityList = horarioCitaEntityList;
    }

    public List<DiasHabilesEntity> getDiasHabilesEntityList() {
        return diasHabilesEntityList;
    }

    public void setDiasHabilesEntityList(List<DiasHabilesEntity> diasHabilesEntityList) {
        this.diasHabilesEntityList = diasHabilesEntityList;
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
        if (!(object instanceof DiasEntity)) {
            return false;
        }
        DiasEntity other = (DiasEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.DiasEntity[ id=" + id + " ]";
    }
    
}
