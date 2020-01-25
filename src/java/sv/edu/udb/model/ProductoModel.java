package sv.edu.udb.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.entities.ProductoEntity;
import sv.edu.udb.util.JpaUtil;

public class ProductoModel {
    //se listan los productos
 public List<ProductoEntity> ListarProductos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("ProductoEntity.findAll");
            List<ProductoEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
      //Obteniendo Productos por id
    public ProductoEntity getProductosById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            ProductoEntity pr = em.find(ProductoEntity.class, codigo);
            em.close();
            return pr;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    //insertando en la bd productos
    public int agregarProductos(ProductoEntity pro) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(pro);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
    
    //actualizar Productos
    public int ActualizarProductos(ProductoEntity pr) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(pr);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
    
    //eliminado un prducto xD
        public int BorrarProductos(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            ProductoEntity h = em.find(ProductoEntity.class, id);
            if (h != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(h);
                tran.commit();
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }    
}
