package commonlyused.bean;

import android.widget.ImageView;

public class NormalItemBean {
    private String item;
    private ImageView item_image;
    private String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ImageView getItem_image() {
        return item_image;
    }

    public void setItem_image(ImageView item_image) {
        this.item_image = item_image;
    }
}
