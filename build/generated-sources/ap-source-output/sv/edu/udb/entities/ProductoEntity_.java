package sv.edu.udb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.udb.entities.CategoriaEntity;
import sv.edu.udb.entities.CitaEntity;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-24T21:15:37")
@StaticMetamodel(ProductoEntity.class)
public class ProductoEntity_ { 

    public static volatile SingularAttribute<ProductoEntity, String> descripcion;
    public static volatile SingularAttribute<ProductoEntity, Float> precio;
    public static volatile ListAttribute<ProductoEntity, CitaEntity> citaEntityList;
    public static volatile SingularAttribute<ProductoEntity, CategoriaEntity> categoria;
    public static volatile SingularAttribute<ProductoEntity, String> titulo;
    public static volatile SingularAttribute<ProductoEntity, byte[]> imagen;
    public static volatile SingularAttribute<ProductoEntity, Integer> id;

}