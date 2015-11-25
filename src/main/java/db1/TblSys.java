package db1;

import dao.TblJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 1 on 25.11.2015.
 */
public class TblSys {

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
