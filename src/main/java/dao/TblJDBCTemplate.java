package dao;

import db1.Column;
import db1.Tbl;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by 1 on 25.11.2015.
 */
public class TblJDBCTemplate implements TblDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void selectTbl(Tbl tbl) {
        String SQL = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where cc.TABLE_NAME= ?";
        List<Column> columns= jdbcTemplateObject.query(SQL,new ColumnMapper(),tbl.getName());
        tbl.setColumnList(columns);
        System.out.println("create TBL");
        return;

    }
}
