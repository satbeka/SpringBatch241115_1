package db2;

/**
 * Created by 1 on 25.11.2015.
 * http://www.java4s.com/spring/spring-jdbc-hello-world-example-create-table-in-database/
 */
public class SpringJdbcCreateTable
{
    JdbcTemplate jt;

    public void setJt(JdbcTemplate jt)
    {
        this.jt = jt;
    }

    public void createTable()
    {
        jt.execute("create table sptest(sno number(3), sname varchar(10))");
        // execute() returns void
        System.out.println("table created");
    }
}
