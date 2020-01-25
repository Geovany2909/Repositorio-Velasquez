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
import sv.edu.udb.entities.DiasEntity;
import sv.edu.udb.util.JpaUtil;

/**
 *
 * @author geovanni
 */
public class DiasModel {
     public List<DiasEntity> listarDias() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("DiasEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<DiasEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

  //Obteniendo Dias por id
    public DiasEntity getDiaById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            DiasEntity genero = em.find(DiasEntity.class, id);
            em.close();
            return genero;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarDias(DiasEntity di) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(di); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarDias(DiasEntity dia) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(dia); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
//Eliminando dias
    public int BorrarDias(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            DiasEntity estado = em.find(DiasEntity.class, id);
            if (estado != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(estado);
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
