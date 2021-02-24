package customer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.WaterWaveProgress;
import customer.listener.ItemClickListener;
import com.control.widget.NoScrollGridView;


/**
 * Created by wcz on 2016/10/14.
 */
@SuppressLint("HandlerLeak")
public class JiuyiPictureAdapter extends BaseAdapter {
    private Context context;

//    private List<ImageBean> imageBeanList = new ArrayList<>();
    private List<String> imageBeanList = new ArrayList<>();
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;

    private ItemClickListener itemClickListener;

    public JiuyiPictureAdapter(Context context, List<String> attachList) {
        super();
        inflater = LayoutInflater.from(context);
        this.imageBeanList = attachList;
    }

    public boolean isShape() {
        return shape;
    }



    public void setShape(boolean shape) {
        this.shape = shape;
    }



    public int getCount() {
        return imageBeanList != null ? imageBeanList.size() : 0;
    }


    public Object getItem(int arg0) {
        return arg0;
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
            convertView = inflater.inflate(R.layout.item_published_singal_item,
                    parent, false);

            holder = new ViewHolder();
            // 照片
//            holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            holder.image = (SimpleDraweeView) convertView.findViewById(R.id.item_grida_image);

            // 删除图标
            holder.img_delete = (ImageView) convertView.findViewById(R.id.img_delete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (parent instanceof NoScrollGridView) {
            if (((NoScrollGridView)parent).isOnMeasure) {
                return convertView;
            }
        }

        /*if(imageBeanList!=null && imageBeanList.size()>0){
            for(int i=0;i<imageBeanList.size();i++){
                String beanURL=imageBeanList.get(i);
                holder.img_delete.setVisibility(View.GONE);
                LoaderManager.getLoader().loadNet(holder.image, beanURL, new ILoader.Options(R.mipmap.ic_launcher, R.mipmap.ic_launcher));
//                holder.image.setImageResource(R.drawable.client_directsales);
//                holder.image.setImageURI(beanURL);
                holder.image.setVisibility(View.VISIBLE);
            }


        }*/

        String beanURL=imageBeanList.get(position);
        holder.img_delete.setVisibility(View.GONE);
        LoaderManager.getLoader().loadNet(holder.image, beanURL, new ILoader.Options(R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        return convertView;
    }

    public class ViewHolder {
        public SimpleDraweeView image;
//        public ImageView image;
        private ImageView img_delete;
        private WaterWaveProgress waveProgress;
    }
}