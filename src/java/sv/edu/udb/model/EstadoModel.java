package sv.edu.udb.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.entities.EstadoEntity;
import sv.edu.udb.util.JpaUtil;

public class EstadoModel {
    //Lista de Estado para el admin

    public List<EstadoEntity> listarEstadoA() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("EstadoEntity.findAll");
            List<EstadoEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //Lista de Estado para horarios de citas 
    public List<EstadoEntity> listarEstadoH() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("EstadoEntity.findByEstadoH");
            List<EstadoEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
//se lista un unico tipo de estado, es usado en la vista del cliente

    public List<EstadoEntity> listarEstadoByOne() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("EstadoEntity.findByOne");
            List<EstadoEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    //se lista el estado 'Finalizado' para la vista del medico
    public List<EstadoEntity> listarEstadoByFin() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("EstadoEntity.findByFin");
            List<EstadoEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    //Obteniendo Estado por id
    public EstadoEntity getEstadoById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            EstadoEntity es = em.find(EstadoEntity.class, codigo);
            em.close();
            return es;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //insertando Estado en la BD
    public int insertarEstado(EstadoEntity es) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(es);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //actualizar Estado
    public int actualizarEstado(EstadoEntity est) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(est);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //elimianr dias Habiles
    public int eliminarEstado(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            EstadoEntity esta = em.find(EstadoEntity.class, id);
            if (esta != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(esta);
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
