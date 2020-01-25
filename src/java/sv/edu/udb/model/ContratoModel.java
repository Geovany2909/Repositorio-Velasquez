/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.entities.ContratoEntity;
import sv.edu.udb.util.JpaUtil;

/**
 *
 * @author geova
 */
public class ContratoModel {
     //Lista de Contrato
     public List<ContratoEntity> ListarContrato() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("ContratoEntity.findAll");
            List<ContratoEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //Obteniendo citas por id
    public ContratoEntity getContratoById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            ContratoEntity contrato = em.find(ContratoEntity.class, codigo);
            em.close();
            return contrato;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }


    public int insertarContrato(ContratoEntity cont) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(cont);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //actualizar citas
    public int actualizarContrato(ContratoEntity con) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(con);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarContrato(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            ContratoEntity cont = em.find(ContratoEntity.class, id);
            if (cont != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(cont);
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
    
    //obtener contrato del medico logeado
    public List<ContratoEntity> listarContratoM(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("ContratoEntity.findByContratoMedico").setParameter("id", id);
            List<ContratoEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    

}
