package sv.edu.udb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.DiasHabilesEntity;
import sv.edu.udb.entities.HorarioCitaEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:37")
@StaticMetamodel(DiasEntity.class)
public class DiasEntity_ { 

    public static volatile ListAttribute<DiasEntity, DiasHabilesEntity> diasHabilesEntityList;
    public static volatile SingularAttribute<DiasEntity, Integer> id;
    public static volatile SingularAttribute<DiasEntity, String> dia;
    public static volatile ListAttribute<DiasEntity, HorarioCitaEntity> horarioCitaEntityList;

}