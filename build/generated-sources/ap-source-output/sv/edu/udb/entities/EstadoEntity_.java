package sv.edu.udb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.CitaEntity;
import sv.edu.udb.entities.HorarioCitaEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:36")
@StaticMetamodel(EstadoEntity.class)
public class EstadoEntity_ { 

    public static volatile SingularAttribute<EstadoEntity, String> estado;
    public static volatile ListAttribute<EstadoEntity, CitaEntity> citaEntityList;
    public static volatile SingularAttribute<EstadoEntity, Integer> id;
    public static volatile ListAttribute<EstadoEntity, HorarioCitaEntity> horarioCitaEntityList;

}