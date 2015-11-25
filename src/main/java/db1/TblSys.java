package db1;

import dao.TblJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 1 on 25.11.2015.
 */
public class TblSys {

    public static String getCreateSQL(Tbl tbl){
        String sqlCreateTbl="create table "+tbl.getName()+"\n" +
                "(\n" ;

        List list=tbl.getColumnList();
        if (list.size()==0||!(tbl!=null)){return "";};

        StringBuilder strBld=new StringBuilder();
        strBld.append(sqlCreateTbl);
        for (int i = 0; i < list.size(); i++) {
            Column columnV=(Column)list.get(i);
            strBld.append(" " + columnV.getcOLUMN_NAME() + " ");
            strBld.append("  "+columnV.getdATA_TYPE()+"(");
            strBld.append(columnV.getdATA_LENGTH()+")");
            if (columnV.getnULLABLE().contains("N")){
                strBld.append(" not null, ");
            }


        }

        strBld.append(");");

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

        System.out.println("------Tbl selectTbl--------" );
        tblJDBCTemplate.selectTbl(tbl);


    }

}
