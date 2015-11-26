package db2.dao;

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

        String sqlDropTbl="drop table "+tbl.getName()+"222";

try {
    jdbcTemplateObject2.execute(sqlDropTbl);
}
catch (Exception e){
    if (!e.getMessage().contains("ORA-00942: таблица или представление пользователя не существует")){
        System.out.println(" ok e.getMessage()="+e.getMessage().toString());
        return;
    };
    System.out.println("e.getMessage()="+e.getMessage().toString());
};
        System.out.println("end dropTbl() ");


    }

    @Override
    public void insTbl(Tbl tbl,List<Map<String, Object>> mapList) {

            //String SQL = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where cc.TABLE_NAME= ?";
            System.out.println("selectRecTbl tbl.getName()="+tbl.getName());
            //Map<String, String> param = Collections.singletonMap("tblname",tbl.getName());

            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("tblname",tbl.getName());


        /*
        String SQL = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where cc.TABLE_NAME " +
                "= 'TISR_TWLTRAINING'";
        */

            //String SQL = "select * from "+tbl.getName()+" where ROW_COUNT ";
            int rowFrom=tbl.getRowNumFrom();
            int rowTo=tbl.getRowNumTo();
            String sqlField= TblSys.getColmnForSelectSQL(tbl);

            String SQL ="select "+sqlField+" from  (select rownum r_id,r.* from "+tbl.getName()+" r) tt " +
                    "where tt.r_id>"+rowFrom+" and tt.r_id<="+rowTo;

        /*
        String SQL2 = "select cc.COLUMN_NAME,cc.DATA_TYPE,cc.DATA_LENGTH,cc.NULLABLE from all_tab_columns cc where rownum<5" +
                " and cc.TABLE_NAME = ?";
        */


            //String SQL2 = "select * from dual";
            //String rrr= jdbcTemplateObject.queryFor(SQL2);

            //List<Column> columns= jdbcTemplateObject.query(SQL, new ColumnMapper(), new Object[]{tbl.getName()} );
            //List<Column> columns = namedParameterJdbcTemplate.queryForList(SQL,namedParameters,Column.class);
            //List<Column> columns = jdbcTemplateObject.queryForList(SQL,Column.class);
            List<Map<String, Object>> mapList;
            try {
                mapList=jdbcTemplateObject.queryForList(SQL);
            }
            catch (Exception e){
                return;
            }

            System.out.println("select TBL");
        /*
        Object rId=mapList.get(0);
        mapList.remove(rId);
        */
            return;




    }
}
