package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称:BatchTransBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/9/12 16:56
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/9/12 1.00 初始版本
 * ****************************************************************
 */
public class BatchTransBean implements Parcelable {
    private long id;
    private String factoryCode;

    public BatchTransBean() {
    }

    private String factoryName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getProductLevelName() {
        return productLevelName;
    }

    public void setProductLevelName(String productLevelName) {
        this.productLevelName = productLevelName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public static Creator<BatchTransBean> getCREATOR() {
        return CREATOR;
    }

    private String batchNumber;
    private double weight;
    private double quantity;
    private String matnr;
    private String spec;
    private String productLevel;
    private int viewType;
    private String productLevelName;
    private String prescription;

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    private String grade;


    protected BatchTransBean(Parcel in) {
        id = in.readLong();
        factoryCode = in.readString();
        factoryName = in.readString();
        batchNumber = in.readString();
        weight = in.readDouble();
        quantity = in.readDouble();
        matnr = in.readString();
        spec = in.readString();
        prescription = in.readString();
        productLevel = in.readString();
        viewType = in.readInt();
        productLevelName = in.readString();
        grade = in.readString();
    }

    public static final Creator<BatchTransBean> CREATOR = new Creator<BatchTransBean>() {
        @Override
        public BatchTransBean createFromParcel(Parcel in) {
            return new BatchTransBean(in);
        }

        @Override
        public BatchTransBean[] newArray(int size) {
            return new BatchTransBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(factoryCode);
        dest.writeString(factoryName);
        dest.writeString(batchNumber);
        dest.writeDouble(weight);
        dest.writeDouble(quantity);
        dest.writeString(matnr);
        dest.writeString(spec);
        dest.writeString(prescription);
        dest.writeString(productLevel);
        dest.writeInt(viewType);
        dest.writeString(productLevelName);
        dest.writeString(grade);
    }
}
