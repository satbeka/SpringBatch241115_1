package db1.dao;

import db1.Column;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 1 on 25.11.2015.
 */
public class ColumnMapper implements RowMapper<Column> {
    public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
        Column column = new Column();

        column.setcOLUMN_NAME(rs.getString("COLUMN_NAME"));
        column.setdATA_LENGTH(rs.getString("DATA_LENGTH"));
        column.setdATA_TYPE(rs.getString("DATA_TYPE"));
        column.setnULLABLE(rs.getString("NULLABLE"));
        return column;
    }
}
