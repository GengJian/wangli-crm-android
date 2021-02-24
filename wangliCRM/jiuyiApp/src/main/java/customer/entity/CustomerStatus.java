package customer.entity;

public enum CustomerStatus {
    POTENTIAL("潜在的"),
    OFFICIAL("正式的"),
    FROZEN("冻结的");

    private String desp;

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

     CustomerStatus(String desp) {
        this.desp = desp;
    }
}