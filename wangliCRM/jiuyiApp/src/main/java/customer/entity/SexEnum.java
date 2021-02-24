package customer.entity;

public enum SexEnum {
    FEMALE("女"),
    MALE("男");

    private String desp;

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    SexEnum(String desp) {
        this.desp = desp;
    }
}