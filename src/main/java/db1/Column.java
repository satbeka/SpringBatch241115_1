package db1;


public class Column {

    public String getcOLUMN_NAME() {
        return cOLUMN_NAME;
    }

    public void setcOLUMN_NAME(String cOLUMN_NAME) {
        this.cOLUMN_NAME = cOLUMN_NAME;
    }

    public String getdATA_TYPE() {
        return dATA_TYPE;
    }

    public void setdATA_TYPE(String dATA_TYPE) {
        this.dATA_TYPE = dATA_TYPE;
    }

    public String getdATA_LENGTH() {
        return dATA_LENGTH;
    }

    public void setdATA_LENGTH(String dATA_LENGTH) {
        this.dATA_LENGTH = dATA_LENGTH;
    }

    public String getnULLABLE() {
        return nULLABLE;
    }

    public void setnULLABLE(String nULLABLE) {
        this.nULLABLE = nULLABLE;
    }

    private String cOLUMN_NAME;
    private String dATA_TYPE;
    private String dATA_LENGTH;
    private String nULLABLE;

    public String getcHAR_LENGTH() {
        return cHAR_LENGTH;
    }

    public void setcHAR_LENGTH(String cHAR_LENGTH) {
        this.cHAR_LENGTH = cHAR_LENGTH;
    }

    private String cHAR_LENGTH;

    public String getcHAR_USED() {
        return cHAR_USED;
    }

    public void setcHAR_USED(String cHAR_USED) {
        this.cHAR_USED = cHAR_USED;
    }

    private String cHAR_USED;

}
