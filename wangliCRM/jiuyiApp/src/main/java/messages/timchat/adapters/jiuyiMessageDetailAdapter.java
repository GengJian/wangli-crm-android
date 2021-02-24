package messages.timchat.adapters;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuyi.app.JiuyiMainApplication;
import com.control.widget.recyclerView.BaseMultiItemQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.control.utils.Func;
import com.control.utils.Res;
import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.List;


import messages.timchat.model.CustomMessage;
import messages.timchat.model.jiuyiCustomerMessageResult;
import messages.timchat.utils.AfterSaleUtils;
import com.control.utils.MarketUtils;
import messages.timchat.utils.NoticeUtils;
import com.control.utils.OrderUtils;
import messages.timchat.utils.PerformaceUtils;
import messages.timchat.utils.PlanUtils;
import com.control.utils.ReceiveUtils;
import messages.timchat.utils.TaskUtils;
import messages.timchat.utils.TimHelperType;

public class jiuyiMessageDetailAdapter extends BaseMultiItemQuickAdapter<jiuyiCustomerMessageResult, BaseViewHolder> {
    private List<String> lookedHistory;

    public jiuyiMessageDetailAdapter(List data) {
        super(data);
        addItemType(jiuyiCustomerMessageResult.TYPE_CUSTOM_LIST, R.layout.jiuyi_messagedetaillist_item);
        addItemType(jiuyiCustomerMessageResult.TYPE_CUSTOM_TEXT, R.layout.jiuyi_messagedetailtext_item);
        addItemType(jiuyiCustomerMessageResult.TYPE_CUSTOM_CONTENTONLY, R.layout.jiuyi_messagedetailcontent_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, jiuyiCustomerMessageResult item) {
         List<jiuyiCustomerMessageResult.ContentsBean> contentsBeanList = new ArrayList<>();
        TextView tvdot=holder.getView(R.id.tv_dot);
        String sUrl="";
        String id="";
        sUrl=item.getUrl();

        lookedHistory=TaskUtils.getInstance().getSearchList();

        if(lookedHistory!=null &&lookedHistory.contains(item.getInfoSeq()+"")||(!Func.IsStringEmpty(id)&& lookedHistory.contains(id))){
            tvdot.setVisibility(View.GONE);
        }else {
            tvdot.setVisibility(View.VISIBLE);
        }
        switch (holder.getItemViewType()) {
            case CustomMessage.TYPE_CUSTOM_LIST:
                TextView tvtopdate=holder.getView(R.id.tv_topdate);
                if(item.getBusinessDate()!=null){
                    tvtopdate.setText(item.getBusinessDate());
                }
                TextView tvTitle=holder.getView(R.id.tvTitle);
                if(item.getTitle()!=null){
                    tvTitle.setText(item.getTitle());
                }
                TextView tvDate=holder.getView(R.id.tvDate);
                if(item.getSummary()!=null){
                    tvDate.setText(item.getSummary().getBusinessDate());
                }
                TextView tvContent1=holder.getView(R.id.tvContent1);
                SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
                if(item.getContents()!=null){
                    for (int i = 0; i<item.getContents().size(); ++i){
                        jiuyiCustomerMessageResult.ContentsBean contentsBean=(jiuyiCustomerMessageResult.ContentsBean) item.getContents().get(i);
                        String sContent=contentsBean.getContent();
                        String sColor=contentsBean.getColor();
                        int ilocation=contentsBean.getLocation();
                        int ilength=contentsBean.getLength();
                        SpannableString ss = new SpannableString(contentsBean.getContent());
                        if(!Func.IsStringEmpty(sContent)){
                            if(!Func.IsStringEmpty(sColor)&&ilocation>0 &&ilocation+ilength-1<=sContent.length()){
                                ss.setSpan(new ForegroundColorSpan(Color.parseColor(sColor)), ilocation-1, ilocation+ilength-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                        stringBuilder.append(ss);
                    }
                }
                tvContent1.setText(stringBuilder);
                LinearLayout ll_customlists=holder.getView(R.id.ll_customlists);
                ll_customlists.removeAllViews();
                if(item.getCells()!=null){
                    for (int i = 0; i<item.getCells().size(); ++i){
                        LinearLayout ll_customlist1 = (LinearLayout) LayoutInflater.from(JiuyiMainApplication.getIns()).inflate(Res.getLayoutID(JiuyiMainApplication.getIns(), "jiuyi_messagecustomlist_item"), null);
                        TextView tvlabel = (TextView) ll_customlist1.findViewById(Res.getViewID(JiuyiMainApplication.getIns(), "tv_label"));
                        jiuyiCustomerMessageResult.CellsBean cellsBean=(jiuyiCustomerMessageResult.CellsBean) item.getCells().get(i);
                        tvlabel.setText(cellsBean.getLeftContent());
                        TextView tvvalue = (TextView) ll_customlist1.findViewById(Res.getViewID(JiuyiMainApplication.getIns(), "tv_value"));
                        String sContent=cellsBean.getRightContent();
                        String sColor="";
                        if(cellsBean.getRightColor()!=null && !Func.IsStringEmpty(cellsBean.getRightColor().toString())){
                            sColor=cellsBean.getRightColor().toString();
                        }else{
                            sColor="#bfbfbf";
                        }
                        int ilocation=cellsBean.getLocation();
                        int ilength=cellsBean.getLength();
                        if(cellsBean.getRightContent()!=null){
                            SpannableString ss = new SpannableString(cellsBean.getRightContent());
                            if(!Func.IsStringEmpty(sContent)){
                                if(!Func.IsStringEmpty(sColor)&&ilocation>0 &&ilocation+ilength-1<=sContent.length()){
                                    ss.setSpan(new ForegroundColorSpan(Color.parseColor(sColor)), ilocation-1, ilocation+ilength-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }
                            tvvalue.setText(ss);
                        }
                        ll_customlists.addView(ll_customlist1);
                    }
                    View view=(View) LayoutInflater.from(JiuyiMainApplication.getIns()).inflate(Res.getLayoutID(JiuyiMainApplication.getIns(), "jiuyi_messagecustomlistline_item"), null);
                    ll_customlists.addView(view);
                }
                break;
            case CustomMessage.TYPE_CUSTOM_TEXT:
                TextView tvtopdateText=holder.getView(R.id.tv_topdate);
                if(item.getBusinessDate()!=null){
                    tvtopdateText.setText(item.getBusinessDate());
                }
                TextView tvTitleforText=holder.getView(R.id.tvTitle);
                if(item.getTitle()!=null){
                    tvTitleforText.setText(item.getTitle());
                }
                TextView tvDateforText=holder.getView(R.id.tvDate);
                if(item.getSummary()!=null){
                    tvDateforText.setText(item.getSummary().getBusinessDate());
                }
                LinearLayout ll_Contents=holder.getView(R.id.ll_Contents);
                ll_Contents.removeAllViews();
                if(item.getContents()!=null){
                    for (int i = 0; i<item.getContents().size(); ++i){
                        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        TextView tvContentforText1=new TextView(JiuyiMainApplication.getIns());
                        tvContentforText1.setLayoutParams(vlp);//设置TextView的布局
                        jiuyiCustomerMessageResult.ContentsBean contentsBean=(jiuyiCustomerMessageResult.ContentsBean) item.getContents().get(i);
                        String sContent=contentsBean.getContent();
                        String sColor=contentsBean.getColor();
                        int ilocation=contentsBean.getLocation();
                        int ilength=contentsBean.getLength();
                        SpannableString ss = new SpannableString(contentsBean.getContent());
                        if(!Func.IsStringEmpty(sContent)){
                            if(!Func.IsStringEmpty(sColor)&&ilocation>0 &&ilocation+ilength-1<=sContent.length()){
                                ss.setSpan(new ForegroundColorSpan(Color.parseColor(sColor)), ilocation-1, ilocation+ilength-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                        tvContentforText1.setTextColor(Res.getColor(null,"jiuyi_info_color"));
                        tvContentforText1.setText(ss);
                        ll_Contents.addView(tvContentforText1);
                    }
                }

                TextView tv_moreinfo=holder.getView(R.id.tv_moreinfo);
                if(item.getMoreInfo()!=null){
                    jiuyiCustomerMessageResult.MoreInfoBean moreInfoBean=item.getMoreInfo();
                    String sContent=moreInfoBean.getContent();
                    String sColor=moreInfoBean.getColor();
                    int ilocation=moreInfoBean.getLocation();
                    int ilength=moreInfoBean.getLength();
                    if(moreInfoBean.getContent()!=null){
                        SpannableString ss = new SpannableString(moreInfoBean.getContent());
                        if(!Func.IsStringEmpty(sContent)){
                            if(!Func.IsStringEmpty(sColor)&&ilocation>0 &&ilocation+ilength-1<=sContent.length()){
                                ss.setSpan(new ForegroundColorSpan(Color.parseColor(sColor)), ilocation-1, ilocation+ilength-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                        tv_moreinfo.setText(ss);
                    }
                }
                break;
            case CustomMessage.TYPE_CUSTOM_CONTENTONLY:
                TextView tvtopdateOnly=holder.getView(R.id.tv_topdate);
                if(item.getBusinessDate()!=null){
                    tvtopdateOnly.setText(item.getBusinessDate());
                }
                TextView tvTitleforonly=holder.getView(R.id.tvTitle);
                if(item.getTitle()!=null){
                    tvTitleforonly.setText(item.getTitle());
                }
                TextView tvDateforonly=holder.getView(R.id.tvDate);
                if(item.getSummary()!=null){
                    tvDateforonly.setText(item.getSummary().getBusinessDate());
                }
                LinearLayout ll_Contentsonly=holder.getView(R.id.ll_contents);
                ll_Contentsonly.removeAllViews();
                if(item.getContents()!=null){
                    for (int i = 0; i<item.getContents().size(); ++i){
                        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        TextView tvContentforText1=new TextView(JiuyiMainApplication.getIns());
                        tvContentforText1.setLayoutParams(vlp);//设置TextView的布局
                        jiuyiCustomerMessageResult.ContentsBean contentsBean=(jiuyiCustomerMessageResult.ContentsBean) item.getContents().get(i);
                        String sContent=contentsBean.getContent();
                        String sColor=contentsBean.getColor();
                        int ilocation=contentsBean.getLocation();
                        int ilength=contentsBean.getLength();
                        SpannableString ss = new SpannableString(contentsBean.getContent());
                        if(!Func.IsStringEmpty(sContent)){
                            if(!Func.IsStringEmpty(sColor)&&ilocation>0 &&ilocation+ilength-1<=sContent.length()){
                                ss.setSpan(new ForegroundColorSpan(Color.parseColor(sColor)), ilocation-1, ilocation+ilength-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                        tvContentforText1.setTextColor(Res.getColor(null,"jiuyi_info_color"));
                        tvContentforText1.setText(ss);
                        ll_Contentsonly.addView(tvContentforText1);
                    }
                }
                break;
        }
    }
}
