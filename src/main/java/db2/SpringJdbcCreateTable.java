package db2;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by 1 on 25.11.2015.
 * http://www.java4s.com/spring/spring-jdbc-hello-world-example-create-table-in-database/
 *
 * http://www.tutorialspoint.com/spring/spring_jdbc_example.htm
 */
public class SpringJdbcCreateTable
{
    JdbcTemplate jt;
    String sqlCreateTbl="-- Create table\n" +
            "create table tbl_name\n" +
            "(\n" +
            "  id          NUMBER not null,\n" +
            "  id_users    NUMBER,\n" +
            "  id_roles    NUMBER,\n" +
            "  create_date DATE default sysdate\n" +
            ")\n" +
            ";\n" +
            "-- Add comments to the table \n" +
            "comment on table TISR_KKB222\n" +
            "  is 'Пользователи в ролях';\n" +
            "-- Add comments to the columns \n" +
            "comment on column TISR_KKB222.id\n" +
            "  is 'Уникальный идентификатор';\n" +
            "comment on column TISR_KKB222.id_users\n" +
            "  is 'Идентфикатор из adm_users(id)';\n" +
            "comment on column TISR_KKB222.id_roles\n" +
            "  is 'Идентфикатор из adm_roles(id)';\n" +
            "comment on column TISR_KKB222.create_date\n" +
            "  is 'Дата создания';\n" +
            "-- Create/Recreate primary, unique and foreign key constraints \n" +
            "alter table tbl_name\n" +
            "  add primary key (ID);\n" +
            "alter table tbl_name\n" +
            "  add constraint FK_ADM_USERS_ROLE_ROLES foreign key (ID_ROLES)\n" +
            "  references ADM_ROLES (ID) on delete cascade;\n" +
            "alter table tbl_name\n" +
            "  add constraint FK_ADM_USERS_ROLE_USERS foreign key (ID_USERS)\n" +
            "  references tbl_name (ID) on delete cascade;";

    public void setJt(JdbcTemplate jt)
    {
        this.jt = jt;
    }

    public void createTable(String tblName)
    {



        getSimpleJdbcTemplate()
        jt.execute(sqlCreateTbl);
        // execute() returns void
        System.out.println("table created");
    }
}
