
package sv.edu.udb.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.entities.CitaEntity;
import sv.edu.udb.util.JpaUtil;

public class CitaModel {
      //Lista de citas
    public List<CitaEntity> listarCitas() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("CitaEntity.findAll");
            List<CitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //Obteniendo citas por id
    public CitaEntity getCitasById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            CitaEntity citas = em.find(CitaEntity.class, codigo);
            em.close();
            return citas;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public CitaEntity getCitasByIdMed(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            CitaEntity citas = em.find(CitaEntity.class, codigo);
            em.close();
            return citas;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarCitas(CitaEntity citas) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(citas);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //actualizar citas
    public int actualizarCitas(CitaEntity ci) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(ci);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarCitas(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            CitaEntity cit = em.find(CitaEntity.class, id);
            if (cit != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(cit);
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

    //Lista de citas por paciente 
    public List<CitaEntity> listCitasP() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("CitaEntity.findByPaciente");
            List<CitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
//listar citas que tiene el medico
    public List<CitaEntity> listCitasMed(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("CitaEntity.findByCitaMedicas").setParameter("id", id);
            List<CitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    //listar citas que el paciente tiene
     public List<CitaEntity> listCitasPaciente(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("CitaEntity.findByCitaPac").setParameter("id", id);
            List<CitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
       //listar citas aceptadas que el paciente tiene
     public List<CitaEntity> listCitasPacienteA(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("CitaEntity.findByCitaPacienteA").setParameter("id", id);
            List<CitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
}
