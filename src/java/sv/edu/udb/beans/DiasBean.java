
package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import sv.edu.udb.entities.DiasEntity;
import sv.edu.udb.model.DiasModel;
import sv.edu.udb.util.JpaUtil;
import sv.edu.udb.util.JsfUtil;

/**
 *
 * @author geovanni
 */
@ManagedBean
@RequestScoped
public class DiasBean {

    DiasModel modelo = new DiasModel();
    private DiasEntity dias;
    
    public DiasBean() {
        dias = new DiasEntity();
    }

    public DiasEntity getDias() {
        return dias;
    }
    public void setDias(DiasEntity dias) {
        this.dias = dias;
    }
    
   public List<DiasEntity> getListaDias() {
        return modelo.listarDias();
    }

    public String addDias() {
        if (modelo.insertarDias(dias) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "dia registrada exitosamente ");
            //Forzando la redirección en el cliente
            return "ContratosView?faces-redirect=true";
        }
    }

    public void findDiasById(int id) {
        this.dias = modelo.getDiaById(id);
    }

    public DiasEntity obtenerDiasById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            DiasEntity esp = em.find(DiasEntity.class, id);
            em.close();
            return esp;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String updateDias() {
        if (modelo.modificarDias(dias) > 0) {
            JsfUtil.setFlashMessage("exito", "dias de trabajo modificado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el dias");
        }
        return "ContratosView?faces-redirect=true";
    }

     public String deleteDias() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.BorrarDias(id) > 0) {
            JsfUtil.setFlashMessage("exito", "dias eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el dia con id: " + id);
        }
        return "ContratosView?faces-redirect=true";
    }

    
}
