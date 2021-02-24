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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customer.Utils.WaterWaveProgress;
import customer.entity.ImageBean;
import customer.listener.ItemClickListener;
import customer.view.Bimp;


/**
 * 附件图片显示
 */
@SuppressLint("HandlerLeak")
public class InfomationInvoiceAdapter extends BaseAdapter {

    private List<ImageBean> imageBeanList = new ArrayList<>();
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;

    private ItemClickListener itemClickListener;

    public InfomationInvoiceAdapter(JiuyiMainApplication ins, List<ImageBean> attachList) {
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

    public InfomationInvoiceAdapter(Activity context, ItemClickListener itemClickListener, List<ImageBean> beansList) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(context);
        this.imageBeanList = beansList;
    }




    public InfomationInvoiceAdapter(Activity context, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(context);
    }


    public int getCount() {
        if (Bimp.tempSelectBitmap.size() == 9) {
            return 9;
        }
        return (Bimp.tempSelectBitmap.size());
    }


    public Object getItem(int arg0) {
        return 0;
    }

    public long getItemId(int arg0) {
        return arg0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.published_singal_voice_item,
                    parent, false);

            holder = new ViewHolder();
            holder.rl_grida_image = (RelativeLayout) convertView.findViewById(R.id.rl_grida_image);
            holder.item_grida_image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            // 照片
//            holder.image = (SimpleDraweeView) convertView.findViewById(R.id.item_grida_image);

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

        if (position == Bimp.tempSelectBitmap.size()) {
            holder.img_delete.setVisibility(View.GONE);
            holder.waveProgress.setVisibility(View.GONE);
            holder.item_grida_image.setVisibility(View.GONE);
            holder.tv_time.setVisibility(View.GONE);
            holder.rl_grida_image.setVisibility(View.GONE);
        } else {
            ImageBean bean = Bimp.tempSelectBitmap.get(position);
            if (bean.isShowIcon()) {
                holder.img_delete.setVisibility(View.GONE);
            } else {
                holder.img_delete.setVisibility(View.VISIBLE);
            }
            if(bean.getBitmap()!=null){
//                holder.image.setImageBitmap(bean.getBitmap());
            }else if(!Func.IsStringEmpty(bean.getThumbnailPath())){
//                LoaderManager.getLoader().loadNet(holder.image, bean.getThumbnailPath(), new ILoader.Options(R.drawable.ic_default_image, R.drawable.ic_default_image));
            }else if(!Func.IsStringEmpty(bean.getPath())){
//                File qemu_file = new File(bean.getPath());
//                if (qemu_file.exists()) {
//                    LoaderManager.getLoader().loadFile(holder.image,qemu_file,new ILoader.Options(R.drawable.ic_default_image, R.drawable.ic_default_image));
//                }
            }
            holder.waveProgress.setProgress(bean.getProgress());
        }
        return convertView;
    }

    public class ViewHolder {
//        public SimpleDraweeView image;
        private RelativeLayout rl_grida_image;
        private ImageView item_grida_image;
        private TextView tv_time;
        private ImageView img_delete;
        private WaterWaveProgress waveProgress;
    }
}