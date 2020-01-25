/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.beans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.IOUtils;
import sv.edu.udb.entities.TipoUsuarioEntity;
import sv.edu.udb.entities.UsuarioEntity;
import sv.edu.udb.model.UsuarioModel;
import sv.edu.udb.util.JpaUtil;
import sv.edu.udb.util.JsfUtil;

/**
 *
 * @author geovanni
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {

    ExternalContext externalContext;
    HttpSession session;

    UsuarioModel modelo = new UsuarioModel();
    private UsuarioEntity usuario;
    private TipoUsuarioEntity tipoU;
    private Part fileUpload;

    public UsuarioBean() {
        usuario = new UsuarioEntity();
        tipoU = new TipoUsuarioEntity();
        usuario.setTipoUsuario(tipoU);
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public TipoUsuarioEntity getTipoU() {
        return tipoU;
    }

    public void setTipoU(TipoUsuarioEntity tipoU) {
        this.tipoU = tipoU;
    }

    private String email;
    private String contrasenia;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<UsuarioEntity> getListaUsuarios() {
        return modelo.ListarUsuario();
    }

    public List<UsuarioEntity> getListaPacientes() {
        return modelo.ListarPaciente();
    }

    public List<UsuarioEntity> getListamedicos() {
        return modelo.ListarMedico();
    }

    public String addUsuario() throws IOException {

        if (fileUpload != null) {
            byte[] bytes = IOUtils.readFully(fileUpload.getInputStream(), -1, true);
            usuario.setFoto(bytes);
        } else {
            BufferedImage bImage = ImageIO.read(getClass().getResource("/resources/default-u.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] bytes = bos.toByteArray();
            usuario.setFoto(bytes);
        }

        if (modelo.agregarUsuarios(usuario) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "usuario registrado exitosamente ");
            return "UsuarioA?faces-redirect=true";
        }
    }

    public void findUsuariosById(int id) {
        this.usuario = modelo.getUsuariosById(id);
    }

    public UsuarioEntity obtenerUsuariosById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            UsuarioEntity esp = em.find(UsuarioEntity.class, id);
            em.close();
            return esp;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String updateUsuarios() throws IOException {
        if (fileUpload != null) {
            byte[] bytes = IOUtils.readFully(fileUpload.getInputStream(), -1, false);
            usuario.setFoto(bytes);
        } else {
            BufferedImage bImage = ImageIO.read(getClass().getResource("/resources/default-u.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] bytes = bos.toByteArray();
            usuario.setFoto(bytes);
        }
        if (modelo.ActualizarUsuarios(usuario) > 0) {
            JsfUtil.setFlashMessage("exito", "dias de modificado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el dias");
        }
        return "UsuarioA?faces-redirect=true";
    }

    public String deleteUsuarios() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.BorrarUsuarios(id) > 0) {
            JsfUtil.setFlashMessage("exito", "usuario eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el usuario con id: " + id);
        }
        return "UsuarioA?faces-redirect=true";
    }

    public String displayImagen(byte[] data) throws IOException {

        String imageString = new String(Base64.encodeBase64(data));
        return imageString;
    }

    public void cerrarSesion() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public void autenticar() {
        usuario = modelo.encontrarUsuarioxLogin(email, contrasenia);
        if (usuario == null) {
            //JsfUtil.setErrorMessage(null, "¡Error! No se pudo iniciar sesion");
            System.out.println("¡Error! No se pudo iniciar sesion");
        }
        if (usuario.getId() == null) {
            JsfUtil.setErrorMessage(null, "Usuario y/o contraseña incorrectos");
        }
        try {
            externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.getSessionMap().put("Session", usuario);
            switch (usuario.getTipoUsuario().getId()) {
                case 1:
                    externalContext.redirect("Administrador/index.xhtml");
                    break;
                case 2:
                    externalContext.redirect("Medicos/index.xhtml");
                    break;
                case 3:
                    externalContext.redirect("Paciente/index.xhtml");
            }
        } catch (IOException e) {
            JsfUtil.setErrorMessage(null, "¡Error! No se pudo redirigir");
        }
    }

    public List<UsuarioEntity> obtenerPacienteL() {
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession) externalContext.getSession(false);
        UsuarioEntity loggedIn = (UsuarioEntity) session.getAttribute("Session");
        return modelo.listarPacienteLogeado(loggedIn.getId());
    }

}
