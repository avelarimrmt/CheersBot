package dao;

import models.ToastsEntity;

import java.sql.SQLException;
import java.util.List;

public interface ToastsDAO {

    //create
    void add(ToastsEntity toasts) throws SQLException;

    //read
    List<ToastsEntity> getAll() throws SQLException;

    ToastsEntity getById(int id_toast) throws SQLException;

    //update
    void update(ToastsEntity toasts) throws SQLException;

    //delete
    void remove(ToastsEntity toasts) throws SQLException;
}