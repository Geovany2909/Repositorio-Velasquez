package sv.edu.udb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.CitaEntity;
import sv.edu.udb.entities.ContratoEntity;
import sv.edu.udb.entities.DiasHabilesEntity;
import sv.edu.udb.entities.HorarioCitaEntity;
import sv.edu.udb.entities.TipoUsuarioEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:37")
@StaticMetamodel(UsuarioEntity.class)
public class UsuarioEntity_ { 

    public static volatile SingularAttribute<UsuarioEntity, String> apellidos;
    public static volatile SingularAttribute<UsuarioEntity, Date> fechaNacimiento;
    public static volatile ListAttribute<UsuarioEntity, CitaEntity> citaEntityList;
    public static volatile SingularAttribute<UsuarioEntity, String> estadoCivil;
    public static volatile SingularAttribute<UsuarioEntity, Integer> edad;
    public static volatile SingularAttribute<UsuarioEntity, String> especialidad;
    public static volatile SingularAttribute<UsuarioEntity, String> nombres;
    public static volatile SingularAttribute<UsuarioEntity, String> alergias;
    public static volatile SingularAttribute<UsuarioEntity, String> domicilio;
    public static volatile SingularAttribute<UsuarioEntity, byte[]> foto;
    public static volatile SingularAttribute<UsuarioEntity, String> experiencia;
    public static volatile ListAttribute<UsuarioEntity, DiasHabilesEntity> diasHabilesEntityList;
    public static volatile SingularAttribute<UsuarioEntity, String> contrasenia;
    public static volatile SingularAttribute<UsuarioEntity, TipoUsuarioEntity> tipoUsuario;
    public static volatile SingularAttribute<UsuarioEntity, Integer> id;
    public static volatile ListAttribute<UsuarioEntity, ContratoEntity> contratoEntityList;
    public static volatile SingularAttribute<UsuarioEntity, Character> sexo;
    public static volatile SingularAttribute<UsuarioEntity, String> telefono;
    public static volatile ListAttribute<UsuarioEntity, HorarioCitaEntity> horarioCitaEntityList;
    public static volatile SingularAttribute<UsuarioEntity, String> email;
    public static volatile SingularAttribute<UsuarioEntity, String> dni;

}