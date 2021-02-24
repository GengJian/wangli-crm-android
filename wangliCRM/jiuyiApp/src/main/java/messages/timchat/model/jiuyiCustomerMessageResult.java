package messages.timchat.model;

import com.control.widget.entity.MultiItemEntity;

import java.util.List;

public class jiuyiCustomerMessageResult implements MultiItemEntity {

    /**
     * FormatType : TYPE_CUSTOM_LIST
     * HelperType : TASK
     * Summary : {"title":"消息列表 this is Title ...","subTitle":"消息列表 副标题 。。。","businessDate":"2099-12-4","url":""}
     * Title : 会话框标题
     * BusinessDate : 4-2
     * Url : http://www.google.com
     * Contents : [{"content":"这里是普通文本，后面换行\n","url":null,"color":"#3f3f3f","location":0,"length":0},{"content":"这里是第二行文本，红色","url":"http://www.baidu.com","color":"red","location":9,"length":2}]
     * Cells : [{"leftContent":"表格左边内容","rightContent":"右边内容","rightUrl":null,"rightColor":null,"location":0,"length":0},{"leftContent":"表格左边内容-1","rightContent":"右边内容-2","rightUrl":"http://www.right-url.com","rightColor":"blue","location":0,"length":5}]
     * MoreInfo : {"content":"点击查看更多》》","url":"http://www.baidu.com","color":"blue","location":0,"length":0}
     */

    private String FormatType;
    private String HelperType;
    private SummaryBean Summary;
    private String Title;
    private String BusinessDate;
    private String Url;
    private MoreInfoBean MoreInfo;
    private List<ContentsBean> Contents;
    private List<CellsBean> Cells;
    public static final int TYPE_CUSTOM_LIST = 1;
    public static final int TYPE_CUSTOM_TEXT = 2;
    public static final int TYPE_CUSTOM_CONTENTONLY = 3;
    private int itemType;

    public Long getInfoSeq() {
        return infoSeq;
    }

    public void setInfoSeq(Long infoSeq) {
        this.infoSeq = infoSeq;
    }

    private Long infoSeq;
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {

        return itemType;
    }

    public String getFormatType() {
        return FormatType;
    }

    public void setFormatType(String FormatType) {
        this.FormatType = FormatType;
    }

    public String getHelperType() {
        return HelperType;
    }

    public void setHelperType(String HelperType) {
        this.HelperType = HelperType;
    }

    public SummaryBean getSummary() {
        return Summary;
    }

    public void setSummary(SummaryBean Summary) {
        this.Summary = Summary;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getBusinessDate() {
        return BusinessDate;
    }

    public void setBusinessDate(String BusinessDate) {
        this.BusinessDate = BusinessDate;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public MoreInfoBean getMoreInfo() {
        return MoreInfo;
    }

    public void setMoreInfo(MoreInfoBean MoreInfo) {
        this.MoreInfo = MoreInfo;
    }

    public List<ContentsBean> getContents() {
        return Contents;
    }

    public void setContents(List<ContentsBean> Contents) {
        this.Contents = Contents;
    }

    public List<CellsBean> getCells() {
        return Cells;
    }

    public void setCells(List<CellsBean> Cells) {
        this.Cells = Cells;
    }

    public static class SummaryBean {
        /**
         * title : 消息列表 this is Title ...
         * subTitle : 消息列表 副标题 。。。
         * businessDate : 2099-12-4
         * url :
         */

        private String title;
        private String subTitle;
        private String businessDate;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getBusinessDate() {
            return businessDate;
        }

        public void setBusinessDate(String businessDate) {
            this.businessDate = businessDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class MoreInfoBean {
        /**
         * content : 点击查看更多》》
         * url : http://www.baidu.com
         * color : blue
         * location : 0
         * length : 0
         */

        private String content;
        private String url;
        private String color;
        private int location;
        private int length;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }

    public static class ContentsBean {
        /**
         * content : 这里是普通文本，后面换行

         * url : null
         * color : #3f3f3f
         * location : 0
         * length : 0
         */

        private String content;
        private Object url;
        private String color;
        private int location;
        private int length;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }

    public static class CellsBean {
        /**
         * leftContent : 表格左边内容
         * rightContent : 右边内容
         * rightUrl : null
         * rightColor : null
         * location : 0
         * length : 0
         */

        private String leftContent;
        private String rightContent;
        private Object rightUrl;
        private Object rightColor;
        private int location;
        private int length;

        public String getLeftContent() {
            return leftContent;
        }

        public void setLeftContent(String leftContent) {
            this.leftContent = leftContent;
        }

        public String getRightContent() {
            return rightContent;
        }

        public void setRightContent(String rightContent) {
            this.rightContent = rightContent;
        }

        public Object getRightUrl() {
            return rightUrl;
        }

        public void setRightUrl(Object rightUrl) {
            this.rightUrl = rightUrl;
        }

        public Object getRightColor() {
            return rightColor;
        }

        public void setRightColor(Object rightColor) {
            this.rightColor = rightColor;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
