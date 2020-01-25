package sv.edu.udb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.DiasEntity;
import sv.edu.udb.entities.UsuarioEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:37")
@StaticMetamodel(DiasHabilesEntity.class)
public class DiasHabilesEntity_ { 

    public static volatile SingularAttribute<DiasHabilesEntity, String> horarioEntrada;
    public static volatile SingularAttribute<DiasHabilesEntity, UsuarioEntity> medico;
    public static volatile SingularAttribute<DiasHabilesEntity, Integer> id;
    public static volatile SingularAttribute<DiasHabilesEntity, String> horarioSalida;
    public static volatile SingularAttribute<DiasHabilesEntity, DiasEntity> dia;

}