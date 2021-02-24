package customer.view;



import java.util.List;

import customer.entity.ImageBean;

public class ImageBucket {
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public List<ImageBean> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageBean> imageList) {
		this.imageList = imageList;
	}

	public int count = 0;
	public String bucketName;
	public List<ImageBean> imageList;

}
