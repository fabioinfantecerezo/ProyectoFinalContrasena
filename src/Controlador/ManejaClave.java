/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import modelo.AaClave;
import modelo.AaUsuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fabio
 */
public class ManejaClave {

    private Session session;
    private Transaction tx;

    public ManejaClave() {
    }

    public void iniciaOperacion() throws HibernateException {
        System.out.println("Conecta con hibernate");
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    public void finalizaOperacion() throws HibernateException {
        System.out.println("Finaliza operacion");
        tx.commit();
        session.close();
    }

    public void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        System.out.println("Error: " + he.getMessage());
        System.exit(0);
    }

    public void anadeContrasena(String aplicacion, String contrasenia, String descripcion) throws HibernateException {
        try {
            iniciaOperacion();
            AaUsuario user = (AaUsuario) session.get(AaUsuario.class, BigDecimal.valueOf(1));
            Query query = session.createSQLQuery("Select id from AA_clave");
            ArrayList<BigDecimal> claveList = (ArrayList<BigDecimal>) query.list();
            AaClave clave = new AaClave(claveList.get((claveList.size() - 1)).add(BigDecimal.valueOf(1)), user, aplicacion, contrasenia, descripcion);
            session.save(clave);
        } catch (Exception e) {
        } finally {
            finalizaOperacion();
        }
    }

    public ArrayList<AaClave> listadoClaves() throws HibernateException {
        ArrayList<AaClave> listaclaves = new ArrayList<>();
        try {
            iniciaOperacion();
            Query query = session.createSQLQuery("Select * from aa_clave");
            query.executeUpdate();
            List<AaClave> list = query.list();

            if (!list.isEmpty()) {
                listaclaves = (ArrayList<AaClave>) list;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            finalizaOperacion();
            return listaclaves;
        }
    }

    public void borraContrasena(int id) throws HibernateException {
        try {
            iniciaOperacion();

            Query query = session.createSQLQuery("delete from aa_clave where id='"+id+"'");
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            finalizaOperacion();
        }
    }
}
