package customer.entity;

import java.io.Serializable;
import java.util.List;

public class ReceiveAddressBean {

    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        public ContentBean() {
        }

        /**
         * createdBy : admin
         * createdDate : 2018-05-29 13:56:20
         * lastModifiedBy : 15355495537
         * lastModifiedDate : 2018-05-29 13:56:21
         * id : 3
         * deleted : false
         * sort : 10
         * member : null
         * abbreviation : null
         * phoneOne : 15355495
         * provinceNumber : 220000
         * provinceName : 吉林省
         * cityNumber : 220400
         * cityName : 辽源市
         * areaNumber : 220421
         * areaName : 东丰县

         * address : 学院路1号楼10001
         * receiver : 叶
         * defaults : true
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private MemberBean member;
        private String abbreviation;
        private String phoneOne;
        private String provinceNumber;
        private String provinceName;
        private String cityNumber;
        private String cityName;
        private String areaNumber;
        private String areaName;
        private String address;
        private String receiver;
        private boolean defaults;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        private boolean selected;

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getPhoneOne() {
            return phoneOne;
        }

        public void setPhoneOne(String phoneOne) {
            this.phoneOne = phoneOne;
        }

        public String getProvinceNumber() {
            return provinceNumber;
        }

        public void setProvinceNumber(String provinceNumber) {
            this.provinceNumber = provinceNumber;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityNumber() {
            return cityNumber;
        }

        public void setCityNumber(String cityNumber) {
            this.cityNumber = cityNumber;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaNumber() {
            return areaNumber;
        }

        public void setAreaNumber(String areaNumber) {
            this.areaNumber = areaNumber;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public boolean isDefaults() {
            return defaults;
        }

        public void setDefaults(boolean defaults) {
            this.defaults = defaults;
        }
    }
    public static class MemberBean {
        /**
         * id : 20
         */

        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}