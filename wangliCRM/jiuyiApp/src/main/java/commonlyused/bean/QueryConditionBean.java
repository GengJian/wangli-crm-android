package commonlyused.bean;



import java.util.List;

public class QueryConditionBean
{
    /**
     * number : 0
     * size : 5
     * direction : ASC
     * property : id
     * rules : [{"field":"department.id","option":"IN","values":[10,11,12]}]
     */

    private int number;
    private int size;
    private String direction;
    private String property;
    private List<RulesBean> rules;

    public List<String> getSpecialConditions() {
        return specialConditions;
    }

    public void setSpecialConditions(List<String> specialConditions) {
        this.specialConditions = specialConditions;
    }

    private List<String> specialConditions;

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    private String fromClientType="android";

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public List<RulesBean> getRules() {
        return rules;
    }

    public void setRules(List<RulesBean> rules) {
        this.rules = rules;
    }

    public static class RulesBean {
        /**
         * field : department.id
         * option : IN
         * values : [10,11,12]
         */

        private String field;
        private String option;


        public List<?> getValues() {
            return values;
        }

        public void setValues(List<?> values) {
            this.values = values;
        }

        private List<?> values;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }


    }
}
