package dao;

import models.GenreEntity;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {

    //create
    void add(GenreEntity genre) throws SQLException;

    //read
    List<GenreEntity> getAll() throws SQLException;

    GenreEntity getById(int id_genre) throws SQLException;

    //update
    void update(GenreEntity genre) throws SQLException;

    //delete
    void remove(GenreEntity genre) throws SQLException;
}