package customer.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.control.utils.Rc;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiMainApplication;

import java.util.List;


import customer.entity.ImageBean;
import customer.view.ImageBucket;

/**
 * Desction:
 * Author:pengjianbo  Dujinyang
 * Date:16/7/4 下午5:40
 */
public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.BucketViewHolder> {

    private final List<ImageBucket> mImageBucketList;
    private final Drawable mDefaultImage;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private ImageBucket mSelectedBean;


    public BucketAdapter(
            List<ImageBucket> mImageBucketList,
            @ColorInt int color) {
        this.mImageBucketList = mImageBucketList;
        this.mDefaultImage = new ColorDrawable(color);
    }

    @Override
    public BucketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_adapter_bucket_item, parent, false);
        return new BucketViewHolder(parent, view);
    }

    @Override
    public void onBindViewHolder(BucketViewHolder holder, int position) {
        ImageBucket bucketBean = mImageBucketList.get(position);
        String bucketName = bucketBean.bucketName;
        if (position != 0) {
            SpannableString nameSpannable = new SpannableString(bucketName + "\n" + bucketBean.imageList.size() + "张");
            nameSpannable.setSpan(new ForegroundColorSpan(Color.GRAY), bucketName.length(), nameSpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            nameSpannable.setSpan(new RelativeSizeSpan(0.8f), bucketName.length(), nameSpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.mTvBucketName.setText(nameSpannable);
        } else {
            holder.mTvBucketName.setText(bucketName);
        }
        if (mImageBucketList != null && TextUtils.equals(mImageBucketList.get(position).bucketName, mSelectedBean.bucketName)
                && mImageBucketList.get(position).getCount()==mSelectedBean.getCount()) {
            holder.mRbSelected.setVisibility(View.VISIBLE);
            holder.mRbSelected.setChecked(true);
        } else {
            holder.mRbSelected.setVisibility(View.GONE);
        }

        String path = bucketBean.imageList.get(0).getPath();
        GlideApp.with(JiuyiMainApplication.getIns()).load(path).placeholder(R.drawable.ic_default_image).into(holder.mIvBucketCover);
    }

    public void setSelectedBucket(ImageBucket bucketBean) {
        this.mSelectedBean = bucketBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mImageBucketList.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnRecyclerViewItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    class BucketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mTvBucketName;
        final ImageView mIvBucketCover;
        final AppCompatRadioButton mRbSelected;

        private final ViewGroup mParentView;

        BucketViewHolder(ViewGroup parent, View itemView) {
            super(itemView);
            this.mParentView = parent;
            mTvBucketName = (TextView) itemView.findViewById(R.id.tv_bucket_name);
            mIvBucketCover = (ImageView) itemView.findViewById(R.id.iv_bucket_cover);
            mRbSelected = (AppCompatRadioButton) itemView.findViewById(R.id.rb_selected);

            itemView.setOnClickListener(this);

            int checkTint = Res.resolveColor(itemView.getContext(), R.attr.gallery_checkbox_button_tint_color, R.color.gallery_default_checkbox_button_tint_color);
            CompoundButtonCompat.setButtonTintList(mRbSelected, ColorStateList.valueOf(checkTint));
        }

        @Override
        public void onClick(View v) {
            if (mOnRecyclerViewItemClickListener != null) {
                mOnRecyclerViewItemClickListener.onItemClick(v, getLayoutPosition());
            }

            setRadioDisChecked(mParentView);
            mRbSelected.setVisibility(View.VISIBLE);
            mRbSelected.setChecked(true);
        }

        /**
         * 设置未所有Item为未选中
         */
        private void setRadioDisChecked(ViewGroup parentView) {
            if (parentView == null || parentView.getChildCount() < 1) {
                return;
            }

            for (int i = 0; i < parentView.getChildCount(); i++) {
                View itemView = parentView.getChildAt(i);
                RadioButton rbSelect = (RadioButton) itemView.findViewById(R.id.rb_selected);
                if (rbSelect != null) {
                    rbSelect.setVisibility(View.GONE);
                    rbSelect.setChecked(false);
                }
            }
        }
    }
}
