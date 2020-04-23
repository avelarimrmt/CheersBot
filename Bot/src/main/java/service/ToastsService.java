package service;

import bl.SessionUtil;
import dao.ToastsDAO;
import models.ToastsEntity;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class ToastsService extends SessionUtil implements ToastsDAO {

    public void add(ToastsEntity toasts) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(toasts);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<ToastsEntity> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM toasts";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(ToastsEntity.class);
        List<ToastsEntity> toastsList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return toastsList;
    }

    public ToastsEntity getById(int id_toast) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM toasts WHERE id_toast = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(ToastsEntity.class);
        query.setParameter("id", id_toast);

        ToastsEntity toasts = (ToastsEntity) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return toasts;
    }

    public void update(ToastsEntity toasts) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(toasts);

        //close session with a transaction
        closeTransactionSession();
    }

    public void remove(ToastsEntity toasts) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(toasts);

        //close session with a transaction
        closeTransactionSession();
    }

}