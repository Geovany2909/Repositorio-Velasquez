/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author geovany
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT u FROM UsuarioEntity u"),
    @NamedQuery(name = "UsuarioEntity.findByLogin", query = "SELECT u FROM UsuarioEntity u WHERE u.email = :email AND u.contrasenia = :contrasenia"),
    @NamedQuery(name = "UsuarioEntity.findByPaciente", query = "SELECT u FROM UsuarioEntity u WHERE u.tipoUsuario.id = 3"),
    @NamedQuery(name = "UsuarioEntity.findByMedico", query = "SELECT u FROM UsuarioEntity u WHERE u.tipoUsuario.id = 2"),
    @NamedQuery(name = "UsuarioEntity.findByPacienteL", query = "SELECT u FROM UsuarioEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioEntity.findById", query = "SELECT u FROM UsuarioEntity u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioEntity.findByNombres", query = "SELECT u FROM UsuarioEntity u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "UsuarioEntity.findByApellidos", query = "SELECT u FROM UsuarioEntity u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "UsuarioEntity.findByEmail", query = "SELECT u FROM UsuarioEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UsuarioEntity.findByDni", query = "SELECT u FROM UsuarioEntity u WHERE u.dni = :dni"),
    @NamedQuery(name = "UsuarioEntity.findBySexo", query = "SELECT u FROM UsuarioEntity u WHERE u.sexo = :sexo"),
    @NamedQuery(name = "UsuarioEntity.findByFechaNacimiento", query = "SELECT u FROM UsuarioEntity u WHERE u.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "UsuarioEntity.findByEdad", query = "SELECT u FROM UsuarioEntity u WHERE u.edad = :edad"),
    @NamedQuery(name = "UsuarioEntity.findByTelefono", query = "SELECT u FROM UsuarioEntity u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "UsuarioEntity.findByEstadoCivil", query = "SELECT u FROM UsuarioEntity u WHERE u.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "UsuarioEntity.findByDomicilio", query = "SELECT u FROM UsuarioEntity u WHERE u.domicilio = :domicilio"),
    @NamedQuery(name = "UsuarioEntity.findByAlergias", query = "SELECT u FROM UsuarioEntity u WHERE u.alergias = :alergias"),
    @NamedQuery(name = "UsuarioEntity.findByEspecialidad", query = "SELECT u FROM UsuarioEntity u WHERE u.especialidad = :especialidad"),
    @NamedQuery(name = "UsuarioEntity.findByExperiencia", query = "SELECT u FROM UsuarioEntity u WHERE u.experiencia = :experiencia"),
    @NamedQuery(name = "UsuarioEntity.findByContrasenia", query = "SELECT u FROM UsuarioEntity u WHERE u.contrasenia = :contrasenia")})
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private String nombres;
    @Basic(optional = false)
    private String apellidos;
    @Basic(optional = false)
    private String email;
    private String dni;
    @Basic(optional = false)
    private Character sexo;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    private int edad;
    private String telefono;
    @Column(name = "estado_civil")
    private String estadoCivil;
    private String domicilio;
    private String alergias;
    private String especialidad;
    private String experiencia;
    @Basic(optional = false)
    private String contrasenia;
    @Lob
    private byte[] foto;
    @OneToMany(mappedBy = "ortopeda")
    private List<HorarioCitaEntity> horarioCitaEntityList;
    @OneToMany(mappedBy = "medico")
    private List<DiasHabilesEntity> diasHabilesEntityList;
    @OneToMany(mappedBy = "medico")
    private List<ContratoEntity> contratoEntityList;
    @JoinColumn(name = "tipoUsuario", referencedColumnName = "id")
    @ManyToOne
    private TipoUsuarioEntity tipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private List<CitaEntity> citaEntityList;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer id) {
        this.id = id;
    }

    public UsuarioEntity(Integer id, String nombres, String apellidos, String email, Character sexo, int edad, String contrasenia) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.sexo = sexo;
        this.edad = edad;
        this.contrasenia = contrasenia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<HorarioCitaEntity> getHorarioCitaEntityList() {
        return horarioCitaEntityList;
    }

    public void setHorarioCitaEntityList(List<HorarioCitaEntity> horarioCitaEntityList) {
        this.horarioCitaEntityList = horarioCitaEntityList;
    }

    public List<DiasHabilesEntity> getDiasHabilesEntityList() {
        return diasHabilesEntityList;
    }

    public void setDiasHabilesEntityList(List<DiasHabilesEntity> diasHabilesEntityList) {
        this.diasHabilesEntityList = diasHabilesEntityList;
    }

    public List<ContratoEntity> getContratoEntityList() {
        return contratoEntityList;
    }

    public void setContratoEntityList(List<ContratoEntity> contratoEntityList) {
        this.contratoEntityList = contratoEntityList;
    }

    public TipoUsuarioEntity getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEntity tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.entities.UsuarioEntity[ id=" + id + " ]";
    }

}
