package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import sv.edu.udb.entities.EstadoEntity;
import sv.edu.udb.model.EstadoModel;
import sv.edu.udb.util.JpaUtil;
import sv.edu.udb.util.JsfUtil;


@ManagedBean
@RequestScoped
public class EstadoBean {
    EstadoModel modelo = new EstadoModel();
    private EstadoEntity estado; 
    
    public EstadoBean() {
        estado = new EstadoEntity();
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    //lista de los estadis para las citas en el apartado del admin
    public List<EstadoEntity> getListaEstadoA() {
        return modelo.listarEstadoA();
    }
        //lista de los estadis para las horarios de citas en el apartado del admin
    public List<EstadoEntity> getListaEstadoH() {
        return modelo.listarEstadoH();
    }
    //se lista el estado como por defecta en espera, es usuado en la vista del paciente
      public List<EstadoEntity> getListaEstadoByone() {
        return modelo.listarEstadoByOne();
    }
       //se lista el estado 'Finalizado' el cual es para que el medico le de fin a la cita
      public List<EstadoEntity> getListaEstadoByFin() {
        return modelo.listarEstadoByFin();
    }
    
    
        public String addEstado() {
        if (modelo.insertarEstado(estado) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Estado registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "EstadoA?faces-redirect=true";
        }
    }

    public void findEstadoById(int id) {
        this.estado = modelo.getEstadoById(id);
    }

    public EstadoEntity obtenerEstadoById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            EstadoEntity esp = em.find(EstadoEntity.class, id);
            em.close();
            return esp;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String updateEstado() {
        if (modelo.actualizarEstado(estado) > 0) {
            JsfUtil.setFlashMessage("exito", "Estado modificado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el Estado");
        }
        return "DiasA?faces-redirect=true";
    }

     public String deleteEstado() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.eliminarEstado(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Estado eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el Estado con id: " + id);
        }
        return "DiasA?faces-redirect=true";
    }
    
}
