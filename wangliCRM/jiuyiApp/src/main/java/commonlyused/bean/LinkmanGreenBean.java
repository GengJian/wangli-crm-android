package commonlyused.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


@Entity
public class LinkmanGreenBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimIdentifier() {
        return timIdentifier;
    }

    public void setTimIdentifier(String timIdentifier) {
        this.timIdentifier = timIdentifier;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getTelOne() {
        return telOne;
    }

    public void setTelOne(String telOne) {
        this.telOne = telOne;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getTelTwo() {
        return telTwo;
    }

    public void setTelTwo(String telTwo) {
        this.telTwo = telTwo;
    }

    public String getTelThree() {
        return telThree;
    }

    public void setTelThree(String telThree) {
        this.telThree = telThree;
    }

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String birthday;
    private String email;
    private String address;
    private String timIdentifier;
    private String letters;//显示拼音的首字母
    private String telOne; //电话1
    private String org;
    private String dept;
    private String duty;
    private String telTwo; //电话2
    private String telThree; //电话3

    @Generated(hash = 1804715003)
    public LinkmanGreenBean(Long id, String name, String sex, String birthday,
            String email, String address, String timIdentifier, String letters,
            String telOne, String org, String dept, String duty, String telTwo,
            String telThree) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
        this.timIdentifier = timIdentifier;
        this.letters = letters;
        this.telOne = telOne;
        this.org = org;
        this.dept = dept;
        this.duty = duty;
        this.telTwo = telTwo;
        this.telThree = telThree;
    }

    @Generated(hash = 2079987334)
    public LinkmanGreenBean() {
    }
}
