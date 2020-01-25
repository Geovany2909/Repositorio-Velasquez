package sv.edu.udb.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.EstadoEntity;
import sv.edu.udb.entities.HorarioCitaEntity;
import sv.edu.udb.entities.ProductoEntity;
import sv.edu.udb.entities.UsuarioEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:36")
@StaticMetamodel(CitaEntity.class)
public class CitaEntity_ { 

    public static volatile SingularAttribute<CitaEntity, UsuarioEntity> idPaciente;
    public static volatile SingularAttribute<CitaEntity, Date> fecha;
    public static volatile SingularAttribute<CitaEntity, HorarioCitaEntity> idHorario;
    public static volatile SingularAttribute<CitaEntity, EstadoEntity> estado;
    public static volatile SingularAttribute<CitaEntity, String> observaciones;
    public static volatile SingularAttribute<CitaEntity, Integer> id;
    public static volatile SingularAttribute<CitaEntity, ProductoEntity> producto;

}