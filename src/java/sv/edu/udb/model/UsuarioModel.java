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
import sv.edu.udb.entities.UsuarioEntity;
import sv.edu.udb.util.JpaUtil;

/**
 *
 * @author geovanni
 */
public class UsuarioModel {

    public List<UsuarioEntity> ListarUsuario() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("UsuarioEntity.findAll");
            List<UsuarioEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
//enlista Usuarios que son pacientes
    public List<UsuarioEntity> ListarPaciente() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("UsuarioEntity.findByPaciente");
            List<UsuarioEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //funcion que enlista usuarios tipo medico
    public List<UsuarioEntity> ListarMedico() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("UsuarioEntity.findByMedico");
            List<UsuarioEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    //Obteniendo medicos por id

    public UsuarioEntity getUsuariosById(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            UsuarioEntity hora = em.find(UsuarioEntity.class, codigo);
            em.close();
            return hora;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int agregarUsuarios(UsuarioEntity horar) {
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

    //actualizar medicos
    public int ActualizarUsuarios(UsuarioEntity horari) {
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

    //eliminando un Usuario
    public int BorrarUsuarios(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;

        try {
            UsuarioEntity h = em.find(UsuarioEntity.class, id);
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

    //funcion para el logeo al sistema
    public UsuarioEntity encontrarUsuarioxLogin(String email, String password) {
        try {
            EntityManager em = JpaUtil.getEntityManager();
            Query q = em.createNamedQuery("UsuarioEntity.findByLogin", UsuarioEntity.class).setParameter("email", email).
                    setParameter("contrasenia", password);
            List<UsuarioEntity> listado = q.getResultList();
            if (!listado.isEmpty()) {
                em.close();
                return listado.get(0);
            } else {
                UsuarioEntity us = new UsuarioEntity();
                return us;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    //lista los pacientes logeados
    public List<UsuarioEntity> listarPacienteLogeado(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query sql = em.createNamedQuery("UsuarioEntity.findByPacienteL").setParameter("id", id);
            List<UsuarioEntity> lista = sql.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

}
