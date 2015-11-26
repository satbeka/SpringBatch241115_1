package db2.dao;

import db1.Tbl;
import db1.TblSys;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
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
        System.out.println("sqlCreateTbl=" + sqlCreateTbl+"222");

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

    public static Map<String, Object>[] getArrayData(List<Map<String, Object>> list){
        @SuppressWarnings("unchecked")
        Map<String, Object>[] maps = new HashMap[list.size()];

        Iterator<Map<String, Object>> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map<java.lang.String, java.lang.Object> map = (Map<java.lang.String, java.lang.Object>) iterator
                    .next();
            maps[i++] = map;
        }

        return maps;
    }


    @Override
    public int[] insTbl(Tbl tbl, List<Map<String, Object>> mapList) {

            System.out.println("insTbl tbl.getName()="+tbl.getName());
            int[] updateCounts = new int[0];
            //int rowFrom=tbl.getRowNumFrom();
            //int rowTo=tbl.getRowNumTo();
            String sqlField= TblSys.getColmnForSelectSQL(tbl);
            String sqlParms= TblSys.getParmsForSelectSQL(tbl);
            String SQL = "insert into "+tbl.getName()+"222"+" "
                +
                "("+sqlField+")\n" +
                "values\n" +
                "("+sqlParms+")\n";

/*
            SqlParameterSource[] batch = SqlParameterSourceUtils
                .createBatch(mapList.toArray());
*/
            Map<String, Object>[] batchValues = getArrayData(mapList);
            try {
                updateCounts=this.namedParameterJdbcTemplate2.batchUpdate(SQL, batchValues);
            }
            catch (Exception e){
                return updateCounts;
            }

            System.out.println("insTbl TBL222");
        /*
        Object rId=mapList.get(0);
        mapList.remove(rId);
        */
            return updateCounts;




    }
}
