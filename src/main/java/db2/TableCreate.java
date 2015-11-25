package db2;

/**
 * Created by 1 on 25.11.2015.
 */
public class TableCreate {


    public static void main(String args[]) throws Exception {
        SingleConnectionDataSource ds = new SingleConnectionDataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl("jdbc:hsqldb:data/tutorial");
        ds.setUsername("sa");
        ds.setPassword("");
        //
        // ds.setDriverClassName("com.mysql.jdbc.Driver");
        // ds.setUrl("jdbc:mysql://localhost:3306/spring");
        // ds.setUsername("spring");
        // ds.setPassword("t1cket");
        //
        // ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        // ds.setUrl("jdbc:oracle:thin:@fiji:1521:my10g");
        // ds.setUsername("spring");
        // ds.setPassword("t1cket");

        JdbcTemplate jt = new JdbcTemplate(ds);
        jt.execute("create table employee (id int, name varchar)");
        jt.execute("insert into employee (id, name) values (1, 'A')");
        jt.execute("insert into employee (id, name) values (2, 'B')");
        jt.execute("insert into employee (id, name) values (3, 'C')");
        jt.execute("insert into employee (id, name) values (4, 'D')");
        jt.execute("insert into employee (id, name) values (5, 'E')");
        jt.execute("insert into employee (id, name) values (6, 'F')");


        int count = jt.queryForInt("select count(*) from employee");

        System.out.println(count);
        ds.destroy();
    }
}
