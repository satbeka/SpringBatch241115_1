package db1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 25.11.2015.
 */
public class Tbl {
    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Column> columnList;
    private String name;
}
