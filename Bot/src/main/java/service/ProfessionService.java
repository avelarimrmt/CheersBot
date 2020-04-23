package service;

import bl.SessionUtil;
import dao.ProfessionDAO;
import models.ProfessionEntity;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class ProfessionService extends SessionUtil implements ProfessionDAO {

    public void add(ProfessionEntity profession) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(profession);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<ProfessionEntity> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM profession";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(ProfessionEntity.class);
        List<ProfessionEntity> professionList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return professionList;
    }

    public ProfessionEntity getById(int id_pr) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM profession WHERE id_prof = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(ProfessionEntity.class);
        query.setParameter("id", id_pr);

        ProfessionEntity profession = (ProfessionEntity) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return profession;
    }

    public void update(ProfessionEntity profession) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(profession);

        //close session with a transaction
        closeTransactionSession();
    }

    public void remove(ProfessionEntity profession) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(profession);

        //close session with a transaction
        closeTransactionSession();
    }

}