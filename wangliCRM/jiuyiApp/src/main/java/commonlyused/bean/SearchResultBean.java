package commonlyused.bean;

import java.util.List;

public class SearchResultBean {

    public List<NormalOperatorBean.ContentBean> getOperators() {
        return operators;
    }

    public void setOperators(List<NormalOperatorBean.ContentBean> operators) {
        this.operators = operators;
    }

    public List<ContactBean.ContentBean> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactBean.ContentBean> contacts) {
        this.contacts = contacts;
    }


    private String keyword;
    private List<NormalOperatorBean.ContentBean> operators;
    private List<ContactBean.ContentBean> contacts;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
