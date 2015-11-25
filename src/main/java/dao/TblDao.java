package dao;

import db1.Tbl;

import javax.sql.DataSource;
import java.util.List;

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
    /**
     * This is the method to be used to list down
     * a record from the Student table corresponding
     * to a passed student id.
     */


}
