package customer.entity;

public class MemberUpdateBean {

    /**
     * id : 10
     * field : legalName
     * value : 张三
     */

    private long id;
    private String field;
    private Object value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
