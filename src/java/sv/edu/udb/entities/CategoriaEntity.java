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
@Table(name = "categoria")
@NamedQueries({
    @NamedQuery(name = "CategoriaEntity.findAll", query = "SELECT c FROM CategoriaEntity c"),
    @NamedQuery(name = "CategoriaEntity.findById", query = "SELECT c FROM CategoriaEntity c WHERE c.id = :id"),
    @NamedQuery(name = "CategoriaEntity.findByTipo", query = "SELECT c FROM CategoriaEntity c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CategoriaEntity.findByDescripcion", query = "SELECT c FROM CategoriaEntity c WHERE c.descripcion = :descripcion")})
public class CategoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String tipo;
    private String descripcion;
    @OneToMany(mappedBy = "categoria")
    private List<ProductoEntity> productoEntityList;

    public CategoriaEntity() {
    }

    public CategoriaEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductoEntity> getProductoEntityList() {
        return productoEntityList;
    }

    public void setProductoEntityList(List<ProductoEntity> productoEntityList) {
        this.productoEntityList = productoEntityList;
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
        if (!(object instanceof CategoriaEntity)) {
            return false;
        }
        CategoriaEntity other = (CategoriaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.CategoriaEntity[ id=" + id + " ]";
    }
    
}
