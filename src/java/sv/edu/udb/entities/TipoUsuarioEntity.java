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
@Table(name = "TipoUsuario")
@NamedQueries({
    @NamedQuery(name = "TipoUsuarioEntity.findAll", query = "SELECT t FROM TipoUsuarioEntity t"),
    @NamedQuery(name = "TipoUsuarioEntity.findByOne", query = "SELECT t FROM TipoUsuarioEntity t WHERE t.id = 3"),
    @NamedQuery(name = "TipoUsuarioEntity.findById", query = "SELECT t FROM TipoUsuarioEntity t WHERE t.id = :id"),
    @NamedQuery(name = "TipoUsuarioEntity.findByTipoUsuario", query = "SELECT t FROM TipoUsuarioEntity t WHERE t.tipoUsuario = :tipoUsuario")})
public class TipoUsuarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String tipoUsuario;
    @OneToMany(mappedBy = "tipoUsuario")
    private List<UsuarioEntity> usuarioEntityList;

    public TipoUsuarioEntity() {
    }

    public TipoUsuarioEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<UsuarioEntity> getUsuarioEntityList() {
        return usuarioEntityList;
    }

    public void setUsuarioEntityList(List<UsuarioEntity> usuarioEntityList) {
        this.usuarioEntityList = usuarioEntityList;
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
        if (!(object instanceof TipoUsuarioEntity)) {
            return false;
        }
        TipoUsuarioEntity other = (TipoUsuarioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.TipoUsuarioEntity[ id=" + id + " ]";
    }
    
}
