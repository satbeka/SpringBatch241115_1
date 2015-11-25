package dao;

import db1.Column;
import db1.Tbl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 25.11.2015.
 */
public class TblJDBCTemplate implements TblDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void selectTbl(Tbl tbl) {
        //String SQL = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where cc.TABLE_NAME= ?";
        System.out.println("tbl.getName()="+tbl.getName());
        //Map<String, String> param = Collections.singletonMap("tblname",tbl.getName());

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("tblname",tbl.getName());


        /*
        String SQL = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where cc.TABLE_NAME " +
                "= 'TISR_TWLTRAINING'";
        */

        String SQL = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where cc.TABLE_NAME = '"
                +tbl.getName()+"'";


        /*
        String SQL2 = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where rownum<5" +
                " and cc.TABLE_NAME = ?";
        */


        //String SQL2 = "select * from dual";
        //String rrr= jdbcTemplateObject.queryFor(SQL2);

        //List<Column> columns= jdbcTemplateObject.query(SQL, new ColumnMapper(), new Object[]{tbl.getName()} );
        //List<Column> columns = namedParameterJdbcTemplate.queryForList(SQL,namedParameters,Column.class);
        //List<Column> columns = jdbcTemplateObject.queryForList(SQL,Column.class);

        List<Column> columns= jdbcTemplateObject.query(SQL, new ColumnMapper());
        //List<Column> columns= jdbcTemplateObject.queryForList(SQL2, new Object[]{"TISR_TWLTRAINING"}, Column.class);

        tbl.setColumnList(columns);
        System.out.println("create TBL");
        return;

    }
}
