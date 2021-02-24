package customer.entity;

import java.util.ArrayList;
import java.util.List;

public class TradedeliveryBean {


    /**
     * content : [{"createdBy":"11","createdDate":"2018-06-09","lastModifiedBy":null,"lastModifiedDate":null,"id":1,"deleted":null,"sort":null,"fromClientType":null,"orderNumber":"4500015246","lineNumber":null,"quantity":null,"amount":null,"deliveryDate":null,"denier":"37","zzszs":800,"zksks":3,"number":"A0908","invoiceNumber":"9180000000","invoiceLineNumber":"000010","materialNumber":null,"materialBatchNumber":"020","materialSpec":"1130","orderSapNumber":null,"orderLineNumber":"10","shippedQuantity":100,"status":"DELIVERY","licensePlateNumber":null,"driverName":null,"driverPhone":null,"memberName":"大松树","member":null,"posnr":null,"werks":null,"lgort":null,"vrkme":null,"bstkd":null,"posex":null,"kunnr":null,"wadatist":null,"wbstk":null,"ernam":null,"aedat":null,"maktx":null,"volum":0,"memo":null,"lfart":null,"foreignInvoiceDetails":null}]
     * last : true
     * totalPages : 1
     * totalElements : 1
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"lastModifiedDate","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
     * numberOfElements : 1
     * first : true
     */

    private boolean last;
    private int totalPages;
    private int totalElements;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean first;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

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

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean {
        /**
         * createdBy : 11
         * createdDate : 2018-06-09
         * lastModifiedBy : null
         * lastModifiedDate : null
         * id : 1
         * deleted : null
         * sort : null
         * fromClientType : null
         * orderNumber : 4500015246
         * lineNumber : null
         * quantity : null
         * amount : null
         * deliveryDate : null
         * denier : 37
         * zzszs : 800
         * zksks : 3
         * number : A0908
         * invoiceNumber : 9180000000
         * invoiceLineNumber : 000010
         * materialNumber : null
         * materialBatchNumber : 020
         * materialSpec : 1130
         * orderSapNumber : null
         * orderLineNumber : 10
         * shippedQuantity : 100
         * status : DELIVERY
         * licensePlateNumber : null
         * driverName : null
         * driverPhone : null
         * memberName : 大松树
         * member : null
         * posnr : null
         * werks : null
         * lgort : null
         * vrkme : null
         * bstkd : null
         * posex : null
         * kunnr : null
         * wadatist : null
         * wbstk : null
         * ernam : null
         * aedat : null
         * maktx : null
         * volum : 0
         * memo : null
         * lfart : null
         * foreignInvoiceDetails : null
         */

        private String createdBy;
        private String createdDate;
        private Object lastModifiedBy;
        private Object lastModifiedDate;
        private long id;
        private Object deleted;
        private Object sort;
        private Object fromClientType;
        private String orderNumber;
        private Object lineNumber;
        private Object quantity;
        private Object amount;
        private Object deliveryDate;
        private String denier;
        private int zzszs;
        private int zksks;
        private String number;
        private String invoiceNumber;
        private String invoiceLineNumber;
        private Object materialNumber;
        private String materialBatchNumber;
        private String materialSpec;
        private Object orderSapNumber;
        private String orderLineNumber;
        private double shippedQuantity;
        private String status;
        private Object licensePlateNumber;
        private Object driverName;
        private Object driverPhone;
        private String memberName;
        private Object member;
        private Object posnr;
        private Object werks;
        private Object lgort;
        private Object vrkme;
        private Object bstkd;
        private Object posex;
        private Object kunnr;
        private Object wadatist;
        private Object wbstk;
        private Object ernam;
        private Object aedat;
        private Object maktx;
        private double volum;
        private Object memo;
        private Object lfart;
        private Object foreignInvoiceDetails;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public boolean isSelect;

        public ArrayList<Media> getVideoList() {
            return videoList;
        }

        public void setVideoList(ArrayList<Media> videoList) {
            this.videoList = videoList;
        }

        public ArrayList<Media> videoList;

        public ArrayList<Media> getImageList() {
            return imageList;
        }

        public void setImageList(ArrayList<Media> imageList) {
            this.imageList = imageList;
        }

        public ArrayList<Media> imageList;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        private  int position=-1;

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

        public Object getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(Object lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public Object getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(Object lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Object getDeleted() {
            return deleted;
        }

        public void setDeleted(Object deleted) {
            this.deleted = deleted;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public Object getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(Object lineNumber) {
            this.lineNumber = lineNumber;
        }

        public Object getQuantity() {
            return quantity;
        }

        public void setQuantity(Object quantity) {
            this.quantity = quantity;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(Object deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getDenier() {
            return denier;
        }

        public void setDenier(String denier) {
            this.denier = denier;
        }

        public int getZzszs() {
            return zzszs;
        }

        public void setZzszs(int zzszs) {
            this.zzszs = zzszs;
        }

        public int getZksks() {
            return zksks;
        }

        public void setZksks(int zksks) {
            this.zksks = zksks;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public String getInvoiceLineNumber() {
            return invoiceLineNumber;
        }

        public void setInvoiceLineNumber(String invoiceLineNumber) {
            this.invoiceLineNumber = invoiceLineNumber;
        }

        public Object getMaterialNumber() {
            return materialNumber;
        }

        public void setMaterialNumber(Object materialNumber) {
            this.materialNumber = materialNumber;
        }

        public String getMaterialBatchNumber() {
            return materialBatchNumber;
        }

        public void setMaterialBatchNumber(String materialBatchNumber) {
            this.materialBatchNumber = materialBatchNumber;
        }

        public String getMaterialSpec() {
            return materialSpec;
        }

        public void setMaterialSpec(String materialSpec) {
            this.materialSpec = materialSpec;
        }

        public Object getOrderSapNumber() {
            return orderSapNumber;
        }

        public void setOrderSapNumber(Object orderSapNumber) {
            this.orderSapNumber = orderSapNumber;
        }

        public String getOrderLineNumber() {
            return orderLineNumber;
        }

        public void setOrderLineNumber(String orderLineNumber) {
            this.orderLineNumber = orderLineNumber;
        }

        public double getShippedQuantity() {
            return shippedQuantity;
        }

        public void setShippedQuantity(double shippedQuantity) {
            this.shippedQuantity = shippedQuantity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getLicensePlateNumber() {
            return licensePlateNumber;
        }

        public void setLicensePlateNumber(Object licensePlateNumber) {
            this.licensePlateNumber = licensePlateNumber;
        }

        public Object getDriverName() {
            return driverName;
        }

        public void setDriverName(Object driverName) {
            this.driverName = driverName;
        }

        public Object getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(Object driverPhone) {
            this.driverPhone = driverPhone;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getPosnr() {
            return posnr;
        }

        public void setPosnr(Object posnr) {
            this.posnr = posnr;
        }

        public Object getWerks() {
            return werks;
        }

        public void setWerks(Object werks) {
            this.werks = werks;
        }

        public Object getLgort() {
            return lgort;
        }

        public void setLgort(Object lgort) {
            this.lgort = lgort;
        }

        public Object getVrkme() {
            return vrkme;
        }

        public void setVrkme(Object vrkme) {
            this.vrkme = vrkme;
        }

        public Object getBstkd() {
            return bstkd;
        }

        public void setBstkd(Object bstkd) {
            this.bstkd = bstkd;
        }

        public Object getPosex() {
            return posex;
        }

        public void setPosex(Object posex) {
            this.posex = posex;
        }

        public Object getKunnr() {
            return kunnr;
        }

        public void setKunnr(Object kunnr) {
            this.kunnr = kunnr;
        }

        public Object getWadatist() {
            return wadatist;
        }

        public void setWadatist(Object wadatist) {
            this.wadatist = wadatist;
        }

        public Object getWbstk() {
            return wbstk;
        }

        public void setWbstk(Object wbstk) {
            this.wbstk = wbstk;
        }

        public Object getErnam() {
            return ernam;
        }

        public void setErnam(Object ernam) {
            this.ernam = ernam;
        }

        public Object getAedat() {
            return aedat;
        }

        public void setAedat(Object aedat) {
            this.aedat = aedat;
        }

        public Object getMaktx() {
            return maktx;
        }

        public void setMaktx(Object maktx) {
            this.maktx = maktx;
        }

        public double getVolum() {
            return volum;
        }

        public void setVolum(double volum) {
            this.volum = volum;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public Object getLfart() {
            return lfart;
        }

        public void setLfart(Object lfart) {
            this.lfart = lfart;
        }

        public Object getForeignInvoiceDetails() {
            return foreignInvoiceDetails;
        }

        public void setForeignInvoiceDetails(Object foreignInvoiceDetails) {
            this.foreignInvoiceDetails = foreignInvoiceDetails;
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : lastModifiedDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : true
         * ascending : false
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean descending;
        private boolean ascending;

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

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }
    }
}
