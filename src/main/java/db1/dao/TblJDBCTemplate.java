package db1.dao;

import db1.Column;
import db1.Tbl;
import db1.TblSys;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
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

    @Override
    public List<Map<String, Object>> selectRecTbl(Tbl tbl) {
        System.out.println("selectRecTbl tbl.getName()="+tbl.getName());
        int rowFrom=tbl.getRowNumFrom();
        int rowTo=tbl.getRowNumTo();
        String sqlField= TblSys.getColmnForSelectSQL(tbl);

        String SQL ="select "+sqlField+" from  (select rownum r_id,r.* from "+tbl.getName()+" r) tt " +
                "where tt.r_id>"+rowFrom+" and tt.r_id<="+rowTo;

        List<Map<String, Object>> mapList;
        try {
         mapList=jdbcTemplateObject.queryForList(SQL);
        }
        catch (Exception e){
            return null;
        }

        System.out.println("select TBL");
        /*
        Object rId=mapList.get(0);
        mapList.remove(rId);
        */
        return mapList;
    }

    @Override
    public int maxRecTbl(Tbl tbl) {
        System.out.println("maxRecTbl tbl.getName()="+tbl.getName());

        String SQL ="select count(*) from "+tbl.getName() ;

        int max=0;
        try {
            max=jdbcTemplateObject.queryForObject(SQL, Integer.class);
        }
        catch (Exception e){
            return max;
        }

        System.out.println("select max");
        /*
        Object rId=mapList.get(0);
        mapList.remove(rId);
        */
        return max;
    }

}
