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
import javax.persistence.Lob;
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
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "ProductoEntity.findAll", query = "SELECT p FROM ProductoEntity p"),
    @NamedQuery(name = "ProductoEntity.findById", query = "SELECT p FROM ProductoEntity p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoEntity.findByTitulo", query = "SELECT p FROM ProductoEntity p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "ProductoEntity.findByDescripcion", query = "SELECT p FROM ProductoEntity p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProductoEntity.findByPrecio", query = "SELECT p FROM ProductoEntity p WHERE p.precio = :precio")})
public class ProductoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String titulo;
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float precio;
    @Lob
    private byte[] imagen;
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    @ManyToOne
    private CategoriaEntity categoria;
    @OneToMany(mappedBy = "producto")
    private List<CitaEntity> citaEntityList;

    public ProductoEntity() {
    }

    public ProductoEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
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
        if (!(object instanceof ProductoEntity)) {
            return false;
        }
        ProductoEntity other = (ProductoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.ProductoEntity[ id=" + id + " ]";
    }
    
}
