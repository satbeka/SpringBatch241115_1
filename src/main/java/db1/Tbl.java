package db1;

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


    private int rowNumFrom=0;

    public int getRowNumFrom() {
        return rowNumFrom;
    }

    public void setRowNumFrom(int rowNumFrom) {
        this.rowNumFrom = rowNumFrom;
    }

    public int getRowNumTo() {
        return rowNumTo;
    }

    public void setRowNumTo(int rowNumTo) {
        this.rowNumTo = rowNumTo;
    }

    private int rowNumTo=5000;
}
