package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import sv.edu.udb.entities.DiasEntity;
import sv.edu.udb.entities.DiasHabilesEntity;
import sv.edu.udb.entities.UsuarioEntity;
import sv.edu.udb.model.DiasHabilesModel;
import sv.edu.udb.util.JpaUtil;
import sv.edu.udb.util.JsfUtil;

@ManagedBean
@RequestScoped
public class DiasHabilesBean {
    ExternalContext externalContext;
    HttpSession session;
    
    DiasHabilesModel modelo = new DiasHabilesModel();
    private DiasHabilesEntity diasHa;
    private DiasEntity dia;
    private UsuarioEntity medico;
    
    public DiasHabilesBean() {
        diasHa = new DiasHabilesEntity();
        dia = new DiasEntity();
        diasHa.setDia(dia);
        
        medico = new UsuarioEntity();
        diasHa.setMedico(medico);
    }


    public DiasHabilesEntity getDiasHa() {
        return diasHa;
    }

    public void setDiasHa(DiasHabilesEntity diasHa) {
        this.diasHa = diasHa;
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
    
    public List<DiasHabilesEntity> getListDiasHabiles() {
        return modelo.listarDiasHabiles();
    }

    public String addDiasHabiles() {
        if (modelo.insertarDiasHabiles(diasHa) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "dias Habiles registrados exitosamente ");
            //Forzando la redirección en el cliente
            return "DiasHabilesA?faces-redirect=true";
        }
    }

    public void findDiasHabilesById(int id) {
        this.diasHa = modelo.getDiasHabilesById(id);
    }

    public DiasHabilesEntity obtenerDiasHabilesById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            DiasHabilesEntity esp = em.find(DiasHabilesEntity.class, id);
            em.close();
            return esp;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String updateDiasHabiles() {
        if (modelo.actualizarDiasHabiles(diasHa) > 0) {
            JsfUtil.setFlashMessage("exito", "dias Habiles modificados exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el dias");
        }
        return "DiasHabilesA?faces-redirect=true";
    }

    public String deleteDiasHabiles() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.eliminarDiasHabiles(id) > 0) {
            JsfUtil.setFlashMessage("exito", "dias habiles eliminados exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el dia habil con id: " + id);
        }
        return "DiasHabilesA?faces-redirect=true";
    }
    
    //se obtiene los dias de trabajo que tiene el medico.
    public List<DiasHabilesEntity> obtenerDiasHByMedico(){
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(false);
        UsuarioEntity loggedIn = (UsuarioEntity) session.getAttribute("Session");
        return modelo.listarDiasMedico(loggedIn.getId());  
    }
    
}
