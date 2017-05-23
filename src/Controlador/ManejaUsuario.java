/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.math.BigDecimal;
import modelo.AaUsuario;
import org.hibernate.*;

/**
 *
 * @author fabio
 */
public class ManejaUsuario {

    private Session session;
    private Transaction tx;

    public ManejaUsuario() {
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

    public void modificaContrasenia(String contrasenia) throws HibernateException {
        try {
            iniciaOperacion();
            AaUsuario user = (AaUsuario) session.get(AaUsuario.class, BigDecimal.valueOf(1));
            user.setContrasena(contrasenia);
            session.update(user);

        } catch (HibernateException e) {
            manejaExcepcion(e);
            throw e;
        } finally {
            finalizaOperacion();
        }
    }

    public String obtieneContrasenia() throws HibernateException {
        try {
            iniciaOperacion();
            AaUsuario user = (AaUsuario) session.get(AaUsuario.class, BigDecimal.valueOf(1));
            return user.getContrasena();
            
        } catch (HibernateException e) {
            manejaExcepcion(e);
            throw e;
        } finally {
            finalizaOperacion();
        }
    }

}
