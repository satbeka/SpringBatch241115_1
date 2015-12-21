package db1;

import db1.dao.TblJDBCTemplate;
import db2.dao.Tbl2JDBCTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 1 on 25.11.2015.
 */
public class TblSys implements Tasklet{

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    private String tblName;

    public static String getParmsForSelectSQL(Tbl tbl){

        List list=tbl.getColumnList();
        if (tbl==null){return "";};
        if (list==null){return "";};

        StringBuilder strBld=new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Column columnV=(Column)list.get(i);
            strBld.append(":"+columnV.getcOLUMN_NAME());
            if (i!=list.size()-1){
                strBld.append(",");
            }

        }

        return strBld.toString();

    }
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
        String sqlCreateTbl="select * from "+tbl.getName();

        return sqlCreateTbl;

    }


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
            //strBld.append("  " + columnV.getdATA_TYPE());
            String type=columnV.getdATA_TYPE();

            if (! (type.contains("TIMESTAMP")||
            type.contains("DATE")||
            type.contains("NUMBER")
                    )  ){


                /*
                if (type.contains("VARCHAR2")){
                    if (columnV.getcHAR_USED().contains("C")){
                        strBld.append("("+columnV.getcHAR_LENGTH()+" CHAR)");
                    }
                    else strBld.append("("+columnV.getdATA_LENGTH()+")");
                }
                */
                if (type.contains("VARCHAR2")){
                   strBld.append(" text ");
                }

            }

            if (type.contains("NUMBER")){
                strBld.append(" double precision ");
            }

            if (type.contains("DATE")){
                strBld.append(" date ");
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

    public boolean execute(String tblN) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:src/main/resources/spring-config.xml");

        TblJDBCTemplate tblJDBCTemplate =
                (TblJDBCTemplate)context.getBean("tblJDBCTemplate");

        //Tbl tbl=(Tbl)context.getBean("tblName");
        Tbl tbl=new Tbl();
        String tblName=tblN;
        tbl.setName(tblName);

        System.out.println("tblName="+tblName);

        System.out.println("------Tbl selectTblForCreate--------");
        tblJDBCTemplate.selectTbl(tbl);

        String sqlCreateTbl=getCreateSQL(tbl);
        System.out.println("sqlCreateTbl=" + sqlCreateTbl);

        Locale.setDefault(new Locale("en", "US"));

        Tbl2JDBCTemplate tblJDBCTemplate2 =
                (Tbl2JDBCTemplate)context.getBean("tblJDBCTemplate2");
        tblJDBCTemplate2.dropTbl(tbl);
        tblJDBCTemplate2.createTbl(sqlCreateTbl);


        //insert�
        int maxRecTbl=tblJDBCTemplate.maxRecTbl(tbl);
        System.out.println("maxRecTbl="+maxRecTbl);
        int rownum=0;

        while (tbl.getRowNumFrom()<maxRecTbl) {
            System.out.println("------Tbl selectRecTbl--------");
            List<Map<String, Object>> mapList=tblJDBCTemplate.selectRecTbl(tbl);

            int[] intMassive=tblJDBCTemplate2.insTbl(tbl,mapList);
            rownum=tbl.getRowNumTo()-tbl.getRowNumFrom();
            tbl.setRowNumFrom(tbl.getRowNumTo());
            tbl.setRowNumTo(tbl.getRowNumTo()+rownum);

        };

        return true;

   }

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("tblName="+tblName);
        execute(this.tblName);
        return RepeatStatus.FINISHED;
    }
}
