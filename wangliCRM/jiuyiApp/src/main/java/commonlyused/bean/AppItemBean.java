package commonlyused.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
@Entity
public class AppItemBean {
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

    public String getAndroidIconUrl() {
        return androidIconUrl;
    }

    public void setAndroidIconUrl(String androidIconUrl) {
        this.androidIconUrl = androidIconUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String androidIconUrl;
    private String url;
    @Generated(hash = 656561145)
    public AppItemBean(Long id, String name, String androidIconUrl, String url) {
        this.id = id;
        this.name = name;
        this.androidIconUrl = androidIconUrl;
        this.url = url;
    }

    @Generated(hash = 1938344343)
    public AppItemBean() {
    }
}
