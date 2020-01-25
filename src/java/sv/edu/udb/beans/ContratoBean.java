package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sv.edu.udb.entities.ContratoEntity;
import sv.edu.udb.entities.UsuarioEntity;
import sv.edu.udb.model.ContratoModel;
import sv.edu.udb.util.JsfUtil;

@ManagedBean
@RequestScoped
public class ContratoBean {
    ExternalContext externalContext;
    HttpSession session;
    
    ContratoModel modelo = new ContratoModel();
    private ContratoEntity contrato;
    private UsuarioEntity medico;
        
    public ContratoBean() {
        contrato = new ContratoEntity();
        medico = new UsuarioEntity();
        contrato.setMedico(medico);
    }

    public ContratoEntity getContrato() {
        return contrato;
    }


    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }

    public UsuarioEntity getMedico() {
        return medico;
    }

    public void setMedico(UsuarioEntity medico) {
        this.medico = medico;
    }
    
       public List<ContratoEntity> getListaContrato() {
        return modelo.ListarContrato();
    }

    public void findContratoById(int id) {
        this.setContrato(modelo.getContratoById(id));

    }

    public String addContrato() {
        if (modelo.insertarContrato(getContrato()) != 1) {
            System.out.println("No entra al metodo");
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "Contrato del medico registrada exitosamente");
            return "ContratoA?faces-redirect=true";
        }
    }

    public String updateContrato() {
        if (modelo.actualizarContrato(getContrato()) > 0) {
            JsfUtil.setFlashMessage("exito", "Contrato modificado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el contrato");
        }
        return "ContratoA?faces-redirect=true";
    }

    public String deleteContrato() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);
        if (modelo.eliminarContrato(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Contrato eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el contrato con el código: " + codigo);
        }
        return "ContratoA?faces-redirect=true";
    }

    //se obtiene el contrato que tiene el medico logeado
     public List<ContratoEntity> obtenerContratoM(){
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(false);
        UsuarioEntity loggedIn = (UsuarioEntity) session.getAttribute("Session");
        return modelo.listarContratoM(loggedIn.getId());  
    }
    
}
