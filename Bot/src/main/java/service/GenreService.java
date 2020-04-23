package service;


import bl.SessionUtil;
import dao.GenreDAO;

import models.GenreEntity;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class GenreService extends SessionUtil implements GenreDAO {

    public void add(GenreEntity genre) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(genre);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<GenreEntity> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM genre";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(GenreEntity.class);
        List<GenreEntity> genreList = query.list();

        //close session with a transaction
        closeTransactionSession();

        return genreList;
    }

    public GenreEntity getById(int id_genre) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM genre WHERE id_genre = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(GenreEntity.class);
        query.setParameter("id", id_genre);

        GenreEntity address = (GenreEntity) query.getSingleResult();

        //close session with a transaction
        closeTransactionSession();

        return address;
    }

    public void update(GenreEntity genre) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(genre);

        //close session with a transaction
        closeTransactionSession();
    }

    public void remove(GenreEntity genre) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(genre);

        //close session with a transaction
        closeTransactionSession();
    }

}