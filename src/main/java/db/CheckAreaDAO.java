package db;

import model.CheckAreaBean;

import java.sql.SQLException;
import java.util.Collection;

public interface CheckAreaDAO {
    void addNewResult(CheckAreaBean result) throws SQLException;
    Collection<CheckAreaBean> getAllResults() throws SQLException;
    void clearResults() throws SQLException;
}
