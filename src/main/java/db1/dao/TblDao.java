package db1.dao;

import db1.Tbl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 25.11.2015.
 */
public interface TblDao {

    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    public void setDataSource(DataSource ds);


    //public Tbl selectTbl(String name);

    public void selectTbl(Tbl tbl);

    public List<Map<String, Object>> selectRecTbl(Tbl tbl);


    int maxRecTbl(Tbl tbl);
}
