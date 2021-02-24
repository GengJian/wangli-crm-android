package dynamic.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanglicrm.android.R;
import com.amap.api.navi.view.PoiInputResItemWidget;
import com.common.GsonUtil;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.keyboard.JiuyiKeyBoardManager;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.webview.JiuyiWebView;
import com.dalong.dialoglib.BottomDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.haibin.calendarview.Calendar;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.Utils.CommonUtils;
import customer.Utils.ViewOperatorType;
import customer.activity.ImageBrowseActivity;
import customer.entity.AttachmentBean;
import customer.entity.FavoriteBean;
import customer.entity.FavoriteReturnBean;
import customer.entity.UserViewInfo;
import customer.entity.VisitActivityBean;
import customer.view.DynamicUrlTextView;
import customer.view.NineGridVideoLayout;
import customer.view.NineGridVoiceLayout;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.DynamicConstant;
import dynamic.Utils.StringUtils;
import dynamic.adapter.DynamicCommentAdapter;
import dynamic.adapter.DynamicListAdapter;
import dynamic.entity.CommentBean;
import dynamic.entity.CommentCreatBean;
import dynamic.entity.DynamicBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonInputInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 动态详情界面
 *****************************************************************
 */
public class JiuyiDynamicDetailActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiEditTextField content_edt;
    private String operatorType, sTitle;
    private long customerid = -1;
    private ImageView iv_back;
    private String content = "";
    private String colKey = "";
    private DynamicBean.ContentBean contentBean;
    private long billId = 0;
    private TextView tvIcon;
    private SimpleDraweeView ivIcon;
    private TextView tvTitle;
    private TextView tvType;
    private TextView tvDate, tv_newcomment;
    private DynamicUrlTextView tvContent;
    private NineGridImageView noScrollgridview;
    private NineGridVoiceLayout noScrollVoicegridview;
    private NineGridVideoLayout noScrollVideogridview;
    private TextView tvCollection;
    private TextView tvGood;
    Drawable drawableLeft = null;
    private FavoriteBean favoriteBean;
    private RecyclerView swipeMenuRecyclerView;
    private DynamicCommentAdapter adapter;
    private EditText mEditText;
    private TextView mButton;
    private BottomDialog dialog;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_dynamicdetail_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        billId = mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
        contentBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        sTitle = mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if (Func.IsStringEmpty(sTitle)) {
            sTitle = "";
        }
        setTitle();
        tvIcon = (TextView) mBodyLayout.findViewById(R.id.tv_icon);
        ivIcon = (SimpleDraweeView) mBodyLayout.findViewById(R.id.iv_icon);
        tvTitle = (TextView) mBodyLayout.findViewById(R.id.tv_title);
        tvType = (TextView) mBodyLayout.findViewById(R.id.tv_type);
        tvDate = (TextView) mBodyLayout.findViewById(R.id.tv_date);
        tvContent = (DynamicUrlTextView) mBodyLayout.findViewById(R.id.tv_content);
        noScrollgridview = (NineGridImageView) mBodyLayout.findViewById(R.id.noScrollgridview);
        noScrollVoicegridview = (NineGridVoiceLayout) mBodyLayout.findViewById(R.id.noScrollVoicegridview);
        noScrollVideogridview = (NineGridVideoLayout) mBodyLayout.findViewById(R.id.noScrollVideogridview);
        tvCollection = (TextView) mBodyLayout.findViewById(R.id.tv_collection);
        tvGood = (TextView) mBodyLayout.findViewById(R.id.tv_good);
        tv_newcomment = (TextView) mBodyLayout.findViewById(R.id.tv_newcomment);
        tv_newcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
        swipeMenuRecyclerView = (RecyclerView) mBodyLayout.findViewById(R.id.rv_infolist);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiDynamicDetailActivity.this, 1, false));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiDynamicDetailActivity.this, LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));
        RefreshLayout refreshLayout = (RefreshLayout) mBodyLayout.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getCommentList(billId);
                refreshlayout.finishRefresh(2000);
                refreshlayout.setLoadmoreFinished(false);
            }
        });
        initData();
        getCommentList(billId);
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        mtvcomplete.setVisibility(View.INVISIBLE);


    }

    private void initData() {
        if (contentBean != null) {
            if (!Func.IsStringEmpty(contentBean.getAvatarUrl())) {
                LoaderManager.getLoader().loadNet(ivIcon, Constant.BaseUrl + "file/" + contentBean.getAvatarUrl().toString(), new ILoader.Options(R.drawable.m_avatar_def, R.drawable.m_avatar_def));
                tvIcon.setVisibility(View.GONE);
                ivIcon.setVisibility(View.VISIBLE);
            } else {
                tvIcon.setVisibility(View.VISIBLE);
                ivIcon.setVisibility(View.GONE);
            }
            if (contentBean.getOperatorNameSrFrAr() != null) {
                tvTitle.setText(contentBean.getOperatorNameSrFrAr());
                String sname = contentBean.getOperatorNameSrFrAr();
                if (sname.length() > 2) {
                    if (sname.contains("(") && sname.indexOf("(") > 0) {
                        String sSubname = sname.substring(0, sname.indexOf("("));
                        if (sSubname.length() > 1) {
                            tvIcon.setText(sSubname.substring(sSubname.length() - 2, sSubname.length()));
                        }

                    } else {
                        tvIcon.setText(sname.substring(sname.length() - 2, sname.length()));
                    }

                } else if (sname.length() > 0) {
                    tvIcon.setText(sname.substring(0, sname.length()));
                }
            }
            if (contentBean.getCreatedDate() != null) {
                tvDate.setText(contentBean.getCreatedDate());
            }
            if (!Func.IsStringEmpty(contentBean.getContent())) {
                tvContent.setLineSpacing(3, 1.3F);
                if (!Func.IsStringEmpty(contentBean.getContent())) {
                    SpannableString dynamicContent = StringUtils.getDynamicContent(JiuyiMainApplication.getIns(), tvContent, contentBean.getContent().replace("<br/>", ""));
                    tvContent.setText(dynamicContent);
                }
            }
            if (contentBean.getFkTypeValue() != null) {
                tvType.setText(contentBean.getFkTypeValue());
            }

            if (contentBean.getFavorited() > 0) {
                drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.orderassistant_collection_s);
                tvCollection.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                tvCollection.setCompoundDrawablePadding(Res.Dip2Pix(8));
            } else {
                drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.dynamiccollection);
                tvCollection.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                tvCollection.setCompoundDrawablePadding(Res.Dip2Pix(8));
            }
            if (contentBean.getAttachmentList() != null && contentBean.getAttachmentList().size() > 0) {
                List<UserViewInfo> imgUrls = new ArrayList<>();
                for (int i = 0; i < contentBean.getAttachmentList().size(); i++) {
                    UserViewInfo userViewInfo;
                    String url = "";
                    AttachmentBean attachmentBean = contentBean.getAttachmentList().get(i);
                    if (attachmentBean.getQiniuKey() != null && attachmentBean.getFileType().toLowerCase().equals("jpg")) {
                        userViewInfo = new UserViewInfo(contentBean.getId(), Constant.BaseUrl + "file/" + attachmentBean.getQiniuKey(), Constant.BaseUrl + "file/" + attachmentBean.getQiniuKey() + "/_thumbnail");
                        imgUrls.add(userViewInfo);
                    }

                }
                noScrollgridview.setAdapter(mAdapter);
                noScrollgridview.setImagesData(imgUrls);

            } else {
                noScrollgridview.setVisibility(View.GONE);
            }

            if (contentBean.getAttachmentList() != null && contentBean.getAttachmentList().size() > 0) {
                List<UserViewInfo> imgUrls = new ArrayList<>();
                for (int i = 0; i < contentBean.getAttachmentList().size(); i++) {
                    UserViewInfo userViewInfo;
                    AttachmentBean attachmentBean = contentBean.getAttachmentList().get(i);
                    if (attachmentBean.getQiniuKey() != null && attachmentBean.getFileType().toLowerCase().equals("mp4")) {
                        userViewInfo = new UserViewInfo(contentBean.getId(), CommonUtils.getAttachUrl(attachmentBean), CommonUtils.getAttachUrl(attachmentBean) + "/_thumbnail");
                        userViewInfo.setUser(attachmentBean.getFileName());
                        imgUrls.add(userViewInfo);
                    }

                }
                noScrollVideogridview.setIsShowAll(true);
                noScrollVideogridview.setUrlList(imgUrls);

            } else {
                noScrollVideogridview.setVisibility(View.GONE);
            }


            if (contentBean.getAttachmentList() != null && contentBean.getAttachmentList().size() > 0) {
                List<UserViewInfo> imgUrls = new ArrayList<>();
                for (int i = 0; i < contentBean.getAttachmentList().size(); i++) {
                    UserViewInfo userViewInfo;
                    AttachmentBean attachmentBean = contentBean.getAttachmentList().get(i);
                    if (attachmentBean.getQiniuKey() != null && attachmentBean.getFileType().toLowerCase().equals("mp3")) {
                        userViewInfo = new UserViewInfo(contentBean.getId(), CommonUtils.getAttachUrl(attachmentBean), CommonUtils.getAttachUrl(attachmentBean) + "/_thumbnail");
                        userViewInfo.setUser(attachmentBean.getExtData());
                        imgUrls.add(userViewInfo);
                    }

                }
                noScrollVoicegridview.setIsShowAll(true);
                noScrollVoicegridview.setUrlList(imgUrls);

            } else {
                noScrollVoicegridview.setVisibility(View.GONE);
            }
            if (contentBean.getLiked() > 0) {
                drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.gooded);
                tvGood.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                tvGood.setCompoundDrawablePadding(Res.Dip2Pix(8));
            } else {
                drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.dynamicungood);
                tvGood.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                tvGood.setCompoundDrawablePadding(Res.Dip2Pix(8));
            }
            if (contentBean.getLikedCount() >= 0) {
                tvGood.setText(contentBean.getLikedCount() + "");
            }
            tvCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contentBean.getFavorited() <= 0) {
                        favoriteBean = new FavoriteBean();
                        favoriteBean.setFkId(contentBean.getId());
                        favoriteBean.setFkType("FEED_FLOW");
                        favoriteBean.setFromClientType("android");
                        addFavorite(favoriteBean);
                    } else if (contentBean.getFavorited() > 0) {
                        deleteFavorite(contentBean.getFavorited());
                    }
                }
            });
            tvGood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contentBean.getLiked() <= 0) {
                        favoriteBean = new FavoriteBean();
                        favoriteBean.setFkId(contentBean.getId());
                        favoriteBean.setFkType("FEED_FLOW");
                        favoriteBean.setFromClientType("android");
                        addLike(favoriteBean);
                    } else if (contentBean.getLiked() > 0) {
                        deleteLike(contentBean.getLiked());
                    }
                }
            });
        }
    }

    NineGridImageViewAdapter<UserViewInfo> mAdapter = new NineGridImageViewAdapter<UserViewInfo>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, UserViewInfo s) {
            GlideApp.with(context).load(s.getThumbnail()).placeholder(R.drawable.ic_default_image).into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<UserViewInfo> list) {
            ArrayList<String> imageList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    UserViewInfo userViewInfo = list.get(i);
                    imageList.add(userViewInfo.getUrl());
                }
            }

            Intent intent = new Intent(context, ImageBrowseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("param_flag_enum", ImageBrowseActivity.FLAG_ENUM[0]);
            bundle.putInt("param_position", index);
            bundle.putStringArrayList("image_url_group", imageList);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    };

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if (nAction == DialogID.DialogTrue) {
            backPage();
        }
    }

    public void setTitle() {
        if (!Func.IsStringEmpty(sTitle)) {
            mTitle = sTitle;
        } else {
            mTitle = "动态详情";
        }
        setTitle(mTitle);
    }

    public void addFavorite(FavoriteBean favoriteBean) {
        JiuyiHttp.POST("favorite/create")
                .setJson(GsonUtil.gson().toJson(favoriteBean))
                .addHeader("Authorization", Rc.id_tokenparam)

                .request(new ACallback<FavoriteReturnBean>() {
                    @Override
                    public void onSuccess(FavoriteReturnBean result) {
                        Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "收藏成功!", Toast.LENGTH_SHORT).show();
                        if (result != null) {
                            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.orderassistant_collection_s);
                            tvCollection.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                            tvCollection.setCompoundDrawablePadding(Res.Dip2Pix(8));
                            contentBean.setFavorited(result.getId());
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public void deleteFavorite(long id) {
        JiuyiHttp.DELETE("favorite/delete/" + id)
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if (data != null) {
                            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.dynamiccollection);
                            tvCollection.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                            tvCollection.setCompoundDrawablePadding(Res.Dip2Pix(8));
                            contentBean.setFavorited(-8);
                            Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "取消收藏成功!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


    public void addLike(FavoriteBean favoriteBean) {
        JiuyiHttp.POST("like-record/add")
                .setJson(GsonUtil.gson().toJson(favoriteBean))
                .addHeader("Authorization", Rc.id_tokenparam)

                .request(new ACallback<FavoriteReturnBean>() {
                    @Override
                    public void onSuccess(FavoriteReturnBean result) {
                        Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "点赞成功!", Toast.LENGTH_SHORT).show();
                        if (result != null) {
                            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.gooded);
                            tvGood.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                            tvGood.setCompoundDrawablePadding(Res.Dip2Pix(8));
                            contentBean.setLiked(result.getId());
                            if (contentBean.getLikedCount() >= 0) {
                                contentBean.setLikedCount(contentBean.getLikedCount()+1);
                                tvGood.setText(contentBean.getLikedCount() + "");
                            }

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public void deleteLike(long id) {
        JiuyiHttp.DELETE("like-record/remove/" + id)
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if (data != null) {
                            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.dynamicungood);
                            tvGood.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                            tvGood.setCompoundDrawablePadding(Res.Dip2Pix(8));
                            if (contentBean.getLikedCount() >= 1) {
                                contentBean.setLikedCount(contentBean.getLikedCount()-1);
                                tvGood.setText(contentBean.getLikedCount() + "");
                            }
                            contentBean.setLiked(-8);
                            Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "取消点赞!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

    /**
     * 弹出评论dialog
     */
    private void showCommentDialog() {
        dialog= BottomDialog.create(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_comment_view)
                .setViewListener(new BottomDialog.ViewListener() {
                    @Override
                    public void bindView(View v) {
                        initView(v);
                    }
                })
                .setDimAmount(0.4f)
                .setCancelOutside(false)
                .setTag("comment");
        dialog.show();
    }

    private void initView(View v) {
        mEditText = (EditText) v.findViewById(R.id.edit_text);
        mButton = (TextView) v.findViewById(R.id.comment_btn);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    mButton.setBackgroundResource(R.drawable.dialog_send_btn);
                    mButton.setEnabled(false);
                } else {
                    mButton.setBackgroundResource(R.drawable.dialog_send_btn_pressed);
                    mButton.setEnabled(true);
                }
            }
        });
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) JiuyiDynamicDetailActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEditText, 0);
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentCreatBean commentCreatBean=new CommentCreatBean();
                commentCreatBean.setContent(mEditText.getText().toString());
                commentCreatBean.setFkId(contentBean.getId()+"");
                commentCreatBean.setFkType("FEED_FLOW");
                addComment(commentCreatBean);
                if(dialog!=null){
                    dialog.dismiss();
                    onInit();
                }

            }
        });

    }

    public void  getCommentList(long id){
        JiuyiHttp.GET("comment/list/FEED_FLOW/"+id)
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<List<CommentBean>>() {
                    @Override
                    public void onSuccess(List<CommentBean> data) {
                        showProcessBar(100);
                        adapter = new DynamicCommentAdapter(R.layout.jiuyi_commentlist_item, data);
                        swipeMenuRecyclerView.setAdapter(adapter);
                        if(data==null ||data.size()==0){
                            adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public void addComment(CommentCreatBean commentCreatBean) {
        JiuyiHttp.POST("comment/create")
                .setJson(GsonUtil.gson().toJson(commentCreatBean))
                .addHeader("Authorization", Rc.id_tokenparam)

                .request(new ACallback<CommentBean>() {
                    @Override
                    public void onSuccess(CommentBean result) {
                        Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "评论提交成功!", Toast.LENGTH_SHORT).show();
                        List<CommentBean> addList =new ArrayList<>();
                        addList.add(result);
                        adapter.add(addList);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

}
