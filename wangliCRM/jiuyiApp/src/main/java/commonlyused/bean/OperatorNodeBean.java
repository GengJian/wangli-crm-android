package commonlyused.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class OperatorNodeBean  {

    public Long getId() {
        return id;

    }

    public OperatorNodeBean(Long id, int count) {
        this.id = id;
        this.count = count;
    }

    public OperatorNodeBean(int count) {
        this.count = count;

    }

    @Override
    public String toString() {
        return "OperatorNodeBean{" +
                "id=" + id +
                ", nodeid='" + nodeid + '\'' +
                ", nodepid='" + nodepid + '\'' +
                ", name='" + name + '\'' +
                ", duty='" + duty + '\'' +
                ", count=" + count +
                ", isPeople=" + isPeople +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id(autoincrement = true)
    private Long id;

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getNodepid() {
        return nodepid;
    }

    public void setNodepid(String nodepid) {
        this.nodepid = nodepid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIsPeople() {
        return isPeople;
    }

    public void setIsPeople(int isPeople) {
        this.isPeople = isPeople;
    }

    private String nodeid;
    private String nodepid;
    private String name;
    private String duty;
    private int count;
    private int isPeople;

    @Generated(hash = 870604931)
    public OperatorNodeBean(Long id, String nodeid, String nodepid, String name,
            String duty, int count, int isPeople) {
        this.id = id;
        this.nodeid = nodeid;
        this.nodepid = nodepid;
        this.name = name;
        this.duty = duty;
        this.count = count;
        this.isPeople = isPeople;
    }

    @Generated(hash = 1381118315)
    public OperatorNodeBean() {
    }

}
