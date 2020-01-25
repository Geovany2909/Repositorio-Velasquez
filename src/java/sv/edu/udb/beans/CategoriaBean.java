package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import sv.edu.udb.entities.CategoriaEntity;
import sv.edu.udb.model.CategoriaModel;
import sv.edu.udb.util.JpaUtil;
import sv.edu.udb.util.JsfUtil;

@ManagedBean
@RequestScoped
public class CategoriaBean {
    CategoriaModel modelo = new CategoriaModel();
    private CategoriaEntity categoria;
    
    public CategoriaBean() {
        categoria = new CategoriaEntity();
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
       public List<CategoriaEntity> getCategoryX() {
        return modelo.listarCategorias();
    }
    
     public String addCategory() {
        if (modelo.insertarCategorias(categoria) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Categoria registrada exitosamente ");
            //Forzando la redirección en el cliente
            return "CategoriasA?faces-redirect=true";
        }
    }

    public void findCategoryById(int id) {
        this.categoria = modelo.getCategoriasById(id);
    }

    public CategoriaEntity obtenerCategoriasById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            CategoriaEntity ca = em.find(CategoriaEntity.class, id);
            em.close();
            return ca;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String updateCategorias() {
        if (modelo.actualizarCategorias(categoria) > 0) {
            JsfUtil.setFlashMessage("exito", "Categoria modificada exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar la categoria");
        }
        return "CategoriasA?faces-redirect=true";
    }

     public String deleteCategorias() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.eliminarCategorias(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Categorias eliminada exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar la categoria con id: " + id);
        }
        return "CategoriasA?faces-redirect=true";
    }
}
