package sv.edu.udb.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.entities.HorarioCitaEntity;
import sv.edu.udb.util.JpaUtil;

public class HorarioCitaModel {
    //Lista de Horario Citas

    public List<HorarioCitaEntity> ListarHorarios() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("HorarioCitaEntity.findAll");
            List<HorarioCitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<HorarioCitaEntity> ListarHDisponible() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("HorarioCitaEntity.findDisponible");
            List<HorarioCitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
        public List<HorarioCitaEntity> ListarHOcupado() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("HorarioCitaEntity.findOcupado");
            List<HorarioCitaEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }


    //Obteniendo Horario Cita por id

    public HorarioCitaEntity getHorariosById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            HorarioCitaEntity hora = em.find(HorarioCitaEntity.class, codigo);
            em.close();
            return hora;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int agregarHorariosCitas(HorarioCitaEntity horar) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(horar);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //actualizar Horarios Citas
    public int ActualizarHorarios(HorarioCitaEntity horari) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(horari);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //eliminado Horario Cita
    public int BorrarHorarios(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            HorarioCitaEntity h = em.find(HorarioCitaEntity.class, id);
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
