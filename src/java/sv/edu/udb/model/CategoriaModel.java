package sv.edu.udb.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.entities.CategoriaEntity;
import sv.edu.udb.util.JpaUtil;

public class CategoriaModel {
    //Lista de categorias
    public List<CategoriaEntity> listarCategorias() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("CategoriaEntity.findAll");
            List<CategoriaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //Obteniendo categorias por id
    public CategoriaEntity getCategoriasById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            CategoriaEntity cate = em.find(CategoriaEntity.class, codigo);
            em.close();
            return cate;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }


    public int insertarCategorias(CategoriaEntity cate) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(cate);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //actualizar categorias
    public int actualizarCategorias(CategoriaEntity cat) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(cat);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarCategorias(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            CategoriaEntity cate = em.find(CategoriaEntity.class, id);
            if (cate != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(cate);
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
