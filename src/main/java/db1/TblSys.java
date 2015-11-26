package db1;

import db1.dao.TblJDBCTemplate;
import db2.dao.Tbl2JDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 25.11.2015.
 */
public class TblSys {


    public static String getColmnForSelectSQL(Tbl tbl){

        List list=tbl.getColumnList();
        if (tbl==null){return "";};
        if (list==null){return "";};

        StringBuilder strBld=new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Column columnV=(Column)list.get(i);
            strBld.append(" " + columnV.getcOLUMN_NAME());
            if (i!=list.size()-1){
                strBld.append(",");
            }

        }

        return strBld.toString();

    }

    public static String getSelectSQL(Tbl tbl){
        String sqlCreateTbl="select * from "+tbl.getName()+"222";



        return sqlCreateTbl;

    }


    public static String getCreateSQL(Tbl tbl){
        String sqlCreateTbl="create table "+tbl.getName()+"222"+"\n" +
                "(\n" ;

        List list=tbl.getColumnList();
        if (list.size()==0||!(tbl!=null)){return "";};

        StringBuilder strBld=new StringBuilder();
        strBld.append(sqlCreateTbl);
        for (int i = 0; i < list.size(); i++) {
            Column columnV=(Column)list.get(i);
            strBld.append(" " + columnV.getcOLUMN_NAME() + " ");
            strBld.append("  " + columnV.getdATA_TYPE());
            String type=columnV.getdATA_TYPE();
            if (! (type.contains("TIMESTAMP")||
            type.contains("DATE")||
            type.contains("NUMBER")
                    )  ){
                strBld.append("("+columnV.getdATA_LENGTH()+")");
            }

            if (columnV.getnULLABLE().contains("N")){
                strBld.append(" not null ");
            }
            if (i!=list.size()-1){
                strBld.append(",");
            }


        }

        strBld.append(")");

        return strBld.toString();

    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        TblJDBCTemplate tblJDBCTemplate =
                (TblJDBCTemplate)context.getBean("tblJDBCTemplate");

        Tbl tbl=(Tbl)context.getBean("tblName");
        String tblName=tbl.getName();
        System.out.println("tblName="+tblName);


        System.out.println("------Tbl selectTblForCreate--------");
        tblJDBCTemplate.selectTbl(tbl);

        String sqlCreateTbl=getCreateSQL(tbl);
        System.out.println("sqlCreateTbl=" + sqlCreateTbl);

        Tbl2JDBCTemplate tblJDBCTemplate2 =
                (Tbl2JDBCTemplate)context.getBean("tblJDBCTemplate2");
        tblJDBCTemplate2.dropTbl(tbl);
        tblJDBCTemplate2.createTbl(sqlCreateTbl);

        //insertû
        System.out.println("------Tbl selectRecTbl--------");
        List<Map<String, Object>> mapList=tblJDBCTemplate.selectRecTbl(tbl);



    }

}
