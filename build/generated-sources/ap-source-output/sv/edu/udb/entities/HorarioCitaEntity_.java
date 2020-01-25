package sv.edu.udb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.CitaEntity;
import sv.edu.udb.entities.DiasEntity;
import sv.edu.udb.entities.EstadoEntity;
import sv.edu.udb.entities.UsuarioEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:37")
@StaticMetamodel(HorarioCitaEntity.class)
public class HorarioCitaEntity_ { 

    public static volatile SingularAttribute<HorarioCitaEntity, EstadoEntity> estadoH;
    public static volatile ListAttribute<HorarioCitaEntity, CitaEntity> citaEntityList;
    public static volatile SingularAttribute<HorarioCitaEntity, String> horaFinal;
    public static volatile SingularAttribute<HorarioCitaEntity, Integer> id;
    public static volatile SingularAttribute<HorarioCitaEntity, UsuarioEntity> ortopeda;
    public static volatile SingularAttribute<HorarioCitaEntity, DiasEntity> dia;
    public static volatile SingularAttribute<HorarioCitaEntity, String> horaInicio;

}