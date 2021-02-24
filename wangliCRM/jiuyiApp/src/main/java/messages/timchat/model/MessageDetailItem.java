package messages.timchat.model;

import com.control.widget.entity.MultiItemEntity;

public class MessageDetailItem implements MultiItemEntity {
    public static final int TYPE_CUSTOM_LIST = 1;
    public static final int TYPE_CUSTOM_TEXT = 2;
    public static final int TYPE_CUSTOM_CONTENTONLY = 3;
    private int itemType;
    private String title;
    public MessageDetailItem(int itemType) {
        this.itemType = itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {

        return itemType;
    }
}
