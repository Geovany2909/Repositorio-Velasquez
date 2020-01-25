/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.entities;

import java.io.Serializable;
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

/**
 *
 * @author geovany
 */
@Entity
@Table(name = "diasHabiles")
@NamedQueries({
    @NamedQuery(name = "DiasHabilesEntity.findAll", query = "SELECT d FROM DiasHabilesEntity d"),
    @NamedQuery(name = "DiasHabilesEntity.findByDiasMedico", query = "SELECT d FROM DiasHabilesEntity d WHERE d.medico.id = :id"),
    @NamedQuery(name = "DiasHabilesEntity.findById", query = "SELECT d FROM DiasHabilesEntity d WHERE d.id = :id"),
    @NamedQuery(name = "DiasHabilesEntity.findByHorarioEntrada", query = "SELECT d FROM DiasHabilesEntity d WHERE d.horarioEntrada = :horarioEntrada"),
    @NamedQuery(name = "DiasHabilesEntity.findByHorarioSalida", query = "SELECT d FROM DiasHabilesEntity d WHERE d.horarioSalida = :horarioSalida")})
public class DiasHabilesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String horarioEntrada;
    private String horarioSalida;
    @JoinColumn(name = "dia", referencedColumnName = "Id")
    @ManyToOne
    private DiasEntity dia;
    @JoinColumn(name = "medico", referencedColumnName = "id")
    @ManyToOne
    private UsuarioEntity medico;

    public DiasHabilesEntity() {
    }

    public DiasHabilesEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public DiasEntity getDia() {
        return dia;
    }

    public void setDia(DiasEntity dia) {
        this.dia = dia;
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
        if (!(object instanceof DiasHabilesEntity)) {
            return false;
        }
        DiasHabilesEntity other = (DiasHabilesEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.DiasHabilesEntity[ id=" + id + " ]";
    }
    
}
