package dao;

import models.ProfessionEntity;

import java.sql.SQLException;
import java.util.List;

public interface ProfessionDAO {

    //create
    void add(ProfessionEntity profession) throws SQLException;

    //read
    List<ProfessionEntity> getAll() throws SQLException;

    ProfessionEntity getById(Long id_prof) throws SQLException;

    //update
    void update(ProfessionEntity profession) throws SQLException;

    //delete
    void remove(ProfessionEntity profession) throws SQLException;
}