package db2.dao;

import db1.Column;
import db1.Tbl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by 1 on 25.11.2015.
 */
public class Tbl2JDBCTemplate implements Tbl2Dao {

    private DataSource dataSource2;
    private JdbcTemplate jdbcTemplateObject2;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate2;

    @Override
    public void setDataSource(DataSource dataSource2) {

        this.dataSource2 = dataSource2;
        this.jdbcTemplateObject2 = new JdbcTemplate(dataSource2);
        this.namedParameterJdbcTemplate2 = new NamedParameterJdbcTemplate(dataSource2);
    }



    @Override
    public void createTbl(String sqlCreateTbl) {
        //String SQL = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where cc.TABLE_NAME= ?";
        System.out.println("sqlCreateTbl=" + sqlCreateTbl);

        String SQL = sqlCreateTbl;
        jdbcTemplateObject2.execute(SQL);
        System.out.println("end createTbl ");


        return;

    }

    @Override
    public void dropTbl(Tbl tbl) {
        String sqlDropTbl="drop table "+tbl.getName()+"222"+";";
        jdbcTemplateObject2.execute(sqlDropTbl);
        System.out.println("end dropTbl() ");


    }
}
