package customer.entity;

public class FavoriteBean {


    private String fromClientType;
    private String fkType;
    private long fkId;

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getFkType() {
        return fkType;
    }

    public void setFkType(String fkType) {
        this.fkType = fkType;
    }

    public long getFkId() {
        return fkId;
    }

    public void setFkId(long fkId) {
        this.fkId = fkId;
    }
}
