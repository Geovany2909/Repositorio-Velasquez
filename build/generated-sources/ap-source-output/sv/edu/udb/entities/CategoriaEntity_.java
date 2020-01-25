package sv.edu.udb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.ProductoEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:37")
@StaticMetamodel(CategoriaEntity.class)
public class CategoriaEntity_ { 

    public static volatile SingularAttribute<CategoriaEntity, String> descripcion;
    public static volatile SingularAttribute<CategoriaEntity, String> tipo;
    public static volatile ListAttribute<CategoriaEntity, ProductoEntity> productoEntityList;
    public static volatile SingularAttribute<CategoriaEntity, Integer> id;

}