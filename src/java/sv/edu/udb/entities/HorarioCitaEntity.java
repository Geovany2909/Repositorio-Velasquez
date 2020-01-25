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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author geovany
 */
@Entity
@Table(name = "HorarioCita")
@NamedQueries({
    @NamedQuery(name = "HorarioCitaEntity.findAll", query = "SELECT h FROM HorarioCitaEntity h"),
    @NamedQuery(name = "HorarioCitaEntity.findDisponible", query = "SELECT h FROM HorarioCitaEntity h WHERE h.estadoH.id = 5"),
    @NamedQuery(name = "HorarioCitaEntity.findOcupado", query = "SELECT h FROM HorarioCitaEntity h WHERE h.estadoH.id = 6"),

    @NamedQuery(name = "HorarioCitaEntity.findById", query = "SELECT h FROM HorarioCitaEntity h WHERE h.id = :id"),
    @NamedQuery(name = "HorarioCitaEntity.findByHoraInicio", query = "SELECT h FROM HorarioCitaEntity h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "HorarioCitaEntity.findByHoraFinal", query = "SELECT h FROM HorarioCitaEntity h WHERE h.horaFinal = :horaFinal")})
public class HorarioCitaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String horaInicio;
    private String horaFinal;
    @JoinColumn(name = "estadoH", referencedColumnName = "id")
    @ManyToOne
    private EstadoEntity estadoH;
    @JoinColumn(name = "dia", referencedColumnName = "Id")
    @ManyToOne
    private DiasEntity dia;
    @JoinColumn(name = "ortopeda", referencedColumnName = "id")
    @ManyToOne
    private UsuarioEntity ortopeda;
    @OneToMany(mappedBy = "idHorario")
    private List<CitaEntity> citaEntityList;

    public HorarioCitaEntity() {
    }

    public HorarioCitaEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public EstadoEntity getEstadoH() {
        return estadoH;
    }

    public void setEstadoH(EstadoEntity estadoH) {
        this.estadoH = estadoH;
    }

    public DiasEntity getDia() {
        return dia;
    }

    public void setDia(DiasEntity dia) {
        this.dia = dia;
    }

    public UsuarioEntity getOrtopeda() {
        return ortopeda;
    }

    public void setOrtopeda(UsuarioEntity ortopeda) {
        this.ortopeda = ortopeda;
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
        if (!(object instanceof HorarioCitaEntity)) {
            return false;
        }
        HorarioCitaEntity other = (HorarioCitaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.HorarioCitaEntity[ id=" + id + " ]";
    }

}
