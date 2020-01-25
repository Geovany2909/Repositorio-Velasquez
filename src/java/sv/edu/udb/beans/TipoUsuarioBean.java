/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import sv.edu.udb.entities.TipoUsuarioEntity;
import sv.edu.udb.model.TipoUsuarioModel;
import sv.edu.udb.util.JpaUtil;
import sv.edu.udb.util.JsfUtil;

/**
 *
 * @author geovanni
 */
@ManagedBean
@RequestScoped
public class TipoUsuarioBean {

    TipoUsuarioModel modelo = new TipoUsuarioModel();
    private TipoUsuarioEntity tipo;

    public TipoUsuarioBean() {
        tipo = new TipoUsuarioEntity();
    }

    /**
     * @return the tipo
     */
    public TipoUsuarioEntity getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoUsuarioEntity tipo) {
        this.tipo = tipo;
    }

    public List<TipoUsuarioEntity> getListaTipoUsuarios() {
        return modelo.ListarTipoUsuario();
    }

    public String addTipoUsuarios() {
        if (modelo.agregarTipoUsuarios(tipo) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "Ortesis registrada exitosamente ");
            return "TipoUsuarioA?faces-redirect=true";
        }
    }

    public void findUsuariosById(int id) {
        this.tipo = modelo.getTipoUsuariosById(id);
    }

    public TipoUsuarioEntity obtenerTipoUsuariosById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            TipoUsuarioEntity esp = em.find(TipoUsuarioEntity.class, id);
            em.close();
            return esp;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String updateTipoUsuarios() {
        if (modelo.ActualizarTipoUsuarios(tipo) > 0) {
            JsfUtil.setFlashMessage("exito", "dias de modificado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el dias");
        }
        return "TipoUsuarioA?faces-redirect=true";
    }

    public String deleteTipoUsuarios() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.BorrarTipoUsuarios(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Horarios eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el Horario con id: " + id);
        }
        return "TipoUsuarioA?faces-redirect=true";
    }

    public List<TipoUsuarioEntity> getListaTipoPaciente() {
        return modelo.ListarOneTipo();
    }

}
