package customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;

import cn.jzvd.JzvdStd;
import customer.Utils.CommonUtils;
import customer.activity.PreviewActivity;
import customer.adapter.IntelligenceDetailPictureAdapter;
import customer.adapter.IntelligenceDetailVideoAdapter;
import customer.adapter.IntelligenceDetailVoiceAdapter;
import customer.entity.AttachmentBean;
import customer.entity.Media;
import customer.entity.VisitActivityListBean;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerVisitRemarkFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 拜访备注
 *****************************************************************
 */

public class JiuyiCustomerVisitAttachmentFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    private TextView tvTitle,tvRemark;
    private ImageView iv_new;
    private CardView cvRemark,cvAttach;
    private JiuyiAttachment acAttach;
    private String sTitle="";
    private VisitActivityListBean visitActivityListBean;
    private RecyclerView rv_piclist;
    IntelligenceDetailPictureAdapter intelligenceDetailPictureAdapter;
    private RecyclerView rv_videolist;
    IntelligenceDetailVideoAdapter intelligenceDetailVideoAdapter;
    private RecyclerView rv_voicelist;
    IntelligenceDetailVoiceAdapter intelligenceDetailVoiceAdapter;
    private ArrayList<Media> picMediaList,videoMediaList,voiceMediaList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customervisitattachmentfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        visitActivityListBean=mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        iv_new = (ImageView) mRootView.findViewById(R.id.iv_new);
        iv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,sTitle);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_CommonInput,true);
            }
        });
        rv_piclist = mRootView.findViewById(R.id.rv_piclist);
        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_piclist.setNestedScrollingEnabled(false);
        rv_piclist.setLayoutManager(mgr);
        rv_piclist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));
        picMediaList=new ArrayList<>();
        videoMediaList=new ArrayList<>();
        voiceMediaList=new ArrayList<>();
        if(visitActivityListBean.getAttachmentList()!=null && visitActivityListBean.getAttachmentList().size()>0){
            for(int i=0;i<visitActivityListBean.getAttachmentList().size();i++){
                AttachmentBean attachmentBean=visitActivityListBean.getAttachmentList().get(i);
                if(attachmentBean.getFileType().toLowerCase().equals("jpg")){
                    Media media=new Media();
                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    media.setExtension(".jpg");
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    media.setShowIcon(false);
                    media.setMediaType(0);
                    picMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp4")){
                    Media media=new Media();
                    media.setExtension(".mp4");
                    media.setMediaType(3);
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    media.setName(attachmentBean.getFileName());
                    media.setShowIcon(false);
                    media.setUrl(CommonUtils.getAttachUrl(attachmentBean));
                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    videoMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp3")){
                    Media media=new Media();
                    media.setExtension(".mp3");
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    media.setShowIcon(false);
                    media.setFileTime(attachmentBean.getExtData());
                    media.setMediaType(2);
                    media.setUrl(CommonUtils.getAttachUrl(attachmentBean));
//                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    voiceMediaList.add(media);
                }
            }

        }
        intelligenceDetailPictureAdapter = new IntelligenceDetailPictureAdapter(JiuyiMainApplication.getIns(), picMediaList);
        rv_piclist.setAdapter(intelligenceDetailPictureAdapter);
        intelligenceDetailPictureAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailPictureAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra(PickerConfig.PRE_RAW_LIST, intelligenceDetailPictureAdapter.getMviewBeanList());
                intent.putExtra("OPERTYPE","VIEW");  //查看
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 9);  //最大选择数量，默认40（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION, position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1500);
            }
        });


        rv_videolist = mRootView.findViewById(R.id.rv_videolist);
        GridLayoutManager mgr2 = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_videolist.setNestedScrollingEnabled(false);
        rv_videolist.setLayoutManager(mgr2);
        rv_videolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));

        intelligenceDetailVideoAdapter = new IntelligenceDetailVideoAdapter(JiuyiMainApplication.getIns(), videoMediaList);
        rv_videolist.setAdapter(intelligenceDetailVideoAdapter);
        intelligenceDetailVideoAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailVideoAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                JzvdStd.startFullscreen(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JzvdStd.class, intelligenceDetailVideoAdapter.getMviewBeanList().get(position).getUrl(),
                        intelligenceDetailVideoAdapter.getMviewBeanList().get(position).getName());
            }
        });

        rv_voicelist = mRootView.findViewById(R.id.rv_voicelist);
        GridLayoutManager mgr3 = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_voicelist.setNestedScrollingEnabled(false);
        rv_voicelist.setLayoutManager(mgr3);
        rv_voicelist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));

        intelligenceDetailVoiceAdapter = new IntelligenceDetailVoiceAdapter(JiuyiMainApplication.getIns(), voiceMediaList);
        rv_voicelist.setAdapter(intelligenceDetailVoiceAdapter);
        intelligenceDetailVoiceAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailVoiceAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Bundle mBundle=new Bundle();
                mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,intelligenceDetailVoiceAdapter.getMviewBeanList().get(position));
                changePage(mBundle,Pub.Customer_RecordPlay,true);
//                JzvdStd.startFullscreen(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JzvdStd.class, intelligenceDetailVideoAdapter.getMviewBeanList().get(position).getUrl(),
//                        intelligenceDetailVideoAdapter.getMviewBeanList().get(position).getName());
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        // 刷新数据

    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVisitAttachmentFragment newInstance(int nPageType) {
        JiuyiCustomerVisitAttachmentFragment f = new JiuyiCustomerVisitAttachmentFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVisitAttachmentFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerVisitAttachmentFragment f = new JiuyiCustomerVisitAttachmentFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);

    }
    @Override
    public void createReq(boolean IsBg) {
    }
}
