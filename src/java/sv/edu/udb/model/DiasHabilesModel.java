package sv.edu.udb.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.entities.DiasHabilesEntity;
import sv.edu.udb.util.JpaUtil;

public class DiasHabilesModel {
     //Lista de Dias Habiles
    public List<DiasHabilesEntity> listarDiasHabiles() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("DiasHabilesEntity.findAll");
            List<DiasHabilesEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //Obteniendo Dias habiles por id
    public DiasHabilesEntity getDiasHabilesById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            DiasHabilesEntity citas = em.find(DiasHabilesEntity.class, codigo);
            em.close();
            return citas;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    //insertando Dias Habiles en la BD

    public int insertarDiasHabiles(DiasHabilesEntity dias) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(dias);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //actualizar Dias Habiles
    public int actualizarDiasHabiles(DiasHabilesEntity diash) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(diash);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //elimianr dias Habiles
    public int eliminarDiasHabiles(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            DiasHabilesEntity dias = em.find(DiasHabilesEntity.class, id);
            if (dias != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(dias);
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
    
    //listar dias Habiles por medicos.
    public List<DiasHabilesEntity> listarDiasMedico(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("DiasHabilesEntity.findByDiasMedico").setParameter("id", id);
            List<DiasHabilesEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
}
