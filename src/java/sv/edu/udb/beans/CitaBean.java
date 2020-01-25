package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import sv.edu.udb.entities.CitaEntity;
import sv.edu.udb.entities.EstadoEntity;
import sv.edu.udb.entities.HorarioCitaEntity;
import sv.edu.udb.entities.ProductoEntity;
import sv.edu.udb.entities.UsuarioEntity;
import sv.edu.udb.model.CitaModel;
import sv.edu.udb.util.JpaUtil;
import sv.edu.udb.util.JsfUtil;

@ManagedBean
@RequestScoped
public class CitaBean {    
    ExternalContext externalContext;
    HttpSession session;

    CitaModel model = new CitaModel();
    private CitaEntity cita;
    private UsuarioEntity paciente;
    private HorarioCitaEntity horario;
    private EstadoEntity estado;
    private ProductoEntity producto;
    
    
    public CitaBean() {
        cita  = new CitaEntity();
        paciente = new UsuarioEntity();
        cita.setIdPaciente(paciente);
  
        horario = new HorarioCitaEntity();
        cita.setIdHorario(horario);
        
        estado = new EstadoEntity();
        cita.setEstado(estado);
        
        producto = new ProductoEntity();
        cita.setProducto(producto);
        
    }

    public CitaEntity getCita() {
        return cita;
    }

    public void setCita(CitaEntity cita) {
        this.cita = cita;
    }

    public UsuarioEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(UsuarioEntity paciente) {
        this.paciente = paciente;
    }


    public HorarioCitaEntity getHorario() {
        return horario;
    }

    public void setHorario(HorarioCitaEntity horario) {
        this.horario = horario;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
    
     public List<CitaEntity> getCitasX() {
        return model.listarCitas();
    }

    public void findCitasById(int id) {
        this.setCita(model.getCitasById(id));
    }
       public CitaEntity obtenerCitasById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            CitaEntity esp = em.find(CitaEntity.class, id);
            em.close();
            return esp;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String addCitas() {
        if (model.insertarCitas(getCita()) != 1) {
            System.out.println("No entra al metodo");
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "cita registrada exitosamente");
            return "CitasA?faces-redirect=true";
        }
    }

    public String updateCitas() {
        if (model.actualizarCitas(getCita()) > 0) {
            JsfUtil.setFlashMessage("exito", "cita modificada exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar la cita");
        }
        return "CitasA?faces-redirect=true";
    }

    public String deleteCitas() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);
        if (model.eliminarCitas(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Cita eliminada exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar la cita con código: " + codigo);
        }
        return "CitasA?faces-redirect=true";
    }

    public List<CitaEntity> getCitasPacientes() {
        return model.listCitasP();
    }
    
    //citas que tiene el medico logeado
    public List<CitaEntity> obtenerCitaMedicas(){
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(false);
        UsuarioEntity loggedIn = (UsuarioEntity) session.getAttribute("Session");
        return model.listCitasMed(loggedIn.getId());  
    }
    
    //citas que el mismo paciente ha solicitado
    public List<CitaEntity> obtenerCitaPaciente(){
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(false);
        UsuarioEntity loggedIn = (UsuarioEntity) session.getAttribute("Session");
        return model.listCitasPaciente(loggedIn.getId());  
    }
      public List<CitaEntity> obtenerCitaPacienteA(){
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(false);
        UsuarioEntity loggedIn = (UsuarioEntity) session.getAttribute("Session");
        return model.listCitasPacienteA(loggedIn.getId());  
    }

    
}
