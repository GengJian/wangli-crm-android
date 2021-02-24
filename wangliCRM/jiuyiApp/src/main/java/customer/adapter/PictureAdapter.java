package customer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiMainApplication;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customer.Utils.WaterWaveProgress;
import customer.entity.Media;
import customer.listener.ItemClickListener;




/**
 * 附件图片显示
 */
@SuppressLint("HandlerLeak")
public class PictureAdapter extends BaseAdapter {

    public List<Media> getImageBeanList() {
        return imageBeanList;
    }

    public void setImageBeanList(List<Media> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }

    private List<Media> imageBeanList = new ArrayList<>();
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;
    private ItemClickListener itemClickListener;

    public PictureAdapter(JiuyiMainApplication ins, List<Media> attachList) {
        inflater = LayoutInflater.from(context);
        this.imageBeanList = attachList;
    }

    public boolean isShape() {
        return shape;
    }

    private Activity context;

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public PictureAdapter(Activity context, ItemClickListener itemClickListener,List<Media> beansList) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(context);
        this.imageBeanList = beansList;
    }




    public PictureAdapter(Activity context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        if (imageBeanList.size() == 9) {
            return 9;
        }
        return (imageBeanList.size() + 1);
//        return this.imageBeanList.size();
    }


    @Override
    public Object getItem(int arg0) {
        return 0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_singal_item,
                    parent, false);

            holder = new ViewHolder();
            // 照片
//            holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            holder.image = (SimpleDraweeView) convertView.findViewById(R.id.item_grida_image);

            // 删除图标
            holder.img_delete = (ImageView) convertView.findViewById(R.id.img_delete);
            // progressbar
            holder.waveProgress = (WaterWaveProgress) convertView.findViewById(R.id.waterWaveProgress1);
            // 是否显示进度条
            holder.waveProgress.setShowProgress(false);
            // 是否显示进度值
            holder.waveProgress.setShowNumerical(false);
            // 有波浪动画
            holder.waveProgress.animateWave();
            // 水的透明度
            holder.waveProgress.setWaterAlpha(0.5f);
            // 背景
            holder.waveProgress.setWaterBgColor(context.getResources().getColor(R.color.transparent));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    Log.e("POSITION", position+"");
                    itemClickListener.itemClickListener(v, position);
                }
            }
        });

        if (position == imageBeanList.size()) {
            holder.img_delete.setVisibility(View.GONE);
            holder.waveProgress.setVisibility(View.GONE);
            holder.image.setImageBitmap(BitmapFactory.decodeResource(
                    convertView.getResources(), R.drawable.enclosure));
            // 图片不能超过5张
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            Media bean = imageBeanList.get(position);
            if (!bean.isShowIcon()) {
                holder.img_delete.setVisibility(View.GONE);
            } else {
                holder.img_delete.setVisibility(View.VISIBLE);
            }
            if(!Func.IsStringEmpty(bean.getThumbnailPath())){
                LoaderManager.getLoader().loadNet(holder.image, bean.getThumbnailPath(), new ILoader.Options(R.drawable.ic_default_image, R.drawable.ic_default_image));
            }else if(!Func.IsStringEmpty(bean.getPath())){
                GlideApp.with(JiuyiMainApplication.getIns()).load(bean.getPath()).placeholder(R.drawable.ic_default_image).into(holder.image);
            } else if(!Func.IsStringEmpty(bean.getUrl())){
                LoaderManager.getLoader().loadNet(holder.image, bean.getUrl(), new ILoader.Options(R.drawable.ic_default_image, R.drawable.ic_default_image));
//                File qemu_file = new File(bean.getUrl());
//                if (qemu_file.exists()) {
//                    LoaderManager.getLoader().loadFile(holder.image,qemu_file,new ILoader.Options(R.drawable.ic_default_image, R.drawable.ic_default_image));
//                }
            }
        }
        return convertView;
    }

    public class ViewHolder {
        public SimpleDraweeView image;
//        public ImageView image;
        private ImageView img_delete;
        private WaterWaveProgress waveProgress;
    }
}