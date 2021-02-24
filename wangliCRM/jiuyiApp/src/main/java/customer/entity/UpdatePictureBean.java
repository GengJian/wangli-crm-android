package customer.entity;

public class UpdatePictureBean {

    /**
     * id : 10
     * avatarUrl : http://img.jiuyisoft.com/cava1.jpg
     */

    private long id;
    private String avatarUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
