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
import sv.edu.udb.entities.TipoUsuarioEntity;
import sv.edu.udb.util.JpaUtil;

/**
 *
 * @author geovanni
 */
public class TipoUsuarioModel {
    public List<TipoUsuarioEntity> ListarTipoUsuario() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("TipoUsuarioEntity.findAll");
            List<TipoUsuarioEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //Obteniendo Tipo Usuarios por id
    public TipoUsuarioEntity getTipoUsuariosById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TipoUsuarioEntity ti = em.find(TipoUsuarioEntity.class, codigo);
            em.close();
            return ti;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //agregando nuevo tipo de usuarios
    public int agregarTipoUsuarios(TipoUsuarioEntity tipo) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(tipo);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //actualizar ti[po Usaurios
    public int ActualizarTipoUsuarios(TipoUsuarioEntity tipo) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.merge(tipo);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    //eliminado tipo U
    public int BorrarTipoUsuarios(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            TipoUsuarioEntity h = em.find(TipoUsuarioEntity.class, id);
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
    
    //enlista los usuarios que son tipo paciente
    public List<TipoUsuarioEntity> ListarOneTipo() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("TipoUsuarioEntity.findByOne");
            List<TipoUsuarioEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
}
