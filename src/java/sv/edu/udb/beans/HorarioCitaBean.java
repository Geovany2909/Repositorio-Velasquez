package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.udb.entities.DiasEntity;
import sv.edu.udb.entities.EstadoEntity;
import sv.edu.udb.entities.HorarioCitaEntity;
import sv.edu.udb.entities.UsuarioEntity;
import sv.edu.udb.model.HorarioCitaModel;
import sv.edu.udb.util.JsfUtil;

@ManagedBean
@RequestScoped
public class HorarioCitaBean {

    HorarioCitaModel modelo = new HorarioCitaModel();
    private HorarioCitaEntity horario;
    private DiasEntity dias;
    private UsuarioEntity ortopeda;
    private EstadoEntity estadoH;

    public HorarioCitaBean() {
        horario = new HorarioCitaEntity();
        dias = new DiasEntity();
        horario.setDia(dias);

        ortopeda = new UsuarioEntity();
        horario.setOrtopeda(ortopeda);

        estadoH = new EstadoEntity();
        horario.setEstadoH(estadoH);
    }

    //se generan los modelos
    public EstadoEntity getEstadoH() {
        return estadoH;
    }

    public void setEstadoH(EstadoEntity estadoH) {
        this.estadoH = estadoH;
    }

    public HorarioCitaEntity getHorario() {
        return horario;
    }

    public void setHorario(HorarioCitaEntity horario) {
        this.horario = horario;
    }

    public DiasEntity getDias() {
        return dias;
    }

    public void setDias(DiasEntity dias) {
        this.dias = dias;
    }

    public UsuarioEntity getOrtopeda() {
        return ortopeda;
    }

    public void setOrtopeda(UsuarioEntity ortopeda) {
        this.ortopeda = ortopeda;
    }

    public List<HorarioCitaEntity> getListaHorariosC() {
        return modelo.ListarHorarios();
    }

    public List<HorarioCitaEntity> getLHorariosDisponibles() {
        return modelo.ListarHDisponible();
    }
      public List<HorarioCitaEntity> getLHorariosOcupado() {
        return modelo.ListarHOcupado();
    }



    public String addHorariosC() {
        if (modelo.agregarHorariosCitas(horario) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "Horario de cita registrado exitosamente ");
            return "HorarioA?faces-redirect=true";
        }
    }

    public void findHorariosCById(int id) {
        this.horario = modelo.getHorariosById(id);
    }

    public String updateHorarios() {
        if (modelo.ActualizarHorarios(horario) > 0) {
            JsfUtil.setFlashMessage("exito", "Horario de cita modificado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el dias");
        }
        return "HorarioA?faces-redirect=true";
    }

    public String deleteHorariosC() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.BorrarHorarios(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Horario de cita eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el Horario con id: " + id);
        }
        return "HorarioA?faces-redirect=true";
    }

}
