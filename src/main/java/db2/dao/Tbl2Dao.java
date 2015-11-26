package db2.dao;

import db1.Tbl;

import javax.sql.DataSource;

/**
 * Created by 1 on 25.11.2015.
 */
public interface Tbl2Dao {

    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    public void setDataSource(DataSource ds2);


    //public Tbl selectTbl(String name);

    public void createTbl(String sqlCreateTbl);
    public void dropTbl(Tbl tbl);





}
