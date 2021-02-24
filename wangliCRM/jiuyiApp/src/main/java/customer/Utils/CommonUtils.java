package customer.Utils;

import com.control.utils.Func;
import com.control.utils.Rc;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import customer.entity.AttachmentBean;
import customer.entity.MemberBeanID;
import customer.entity.UserViewInfo;

public class CommonUtils {
    public static List<MemberBeanID> getMemberList() {
        final  List <MemberBeanID> memberBeanIDList=new ArrayList<>();
        JiuyiHttp.GET("member/find_member_list")
                .tag("find_member_list")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<MemberBeanID>>() {
                    @Override
                    public void onSuccess(List<MemberBeanID> data) {

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
        return null;
    }

    public static String getAttachUrl(AttachmentBean attachmentBean) {
        String url="";
        if(attachmentBean==null){
            return "";
        }
        url+=Constant.VideoUrl+"file/";
        if(!Func.IsStringEmpty(attachmentBean.getYyyymm())){
            url+=attachmentBean.getYyyymm()+"/";
        }

        if(attachmentBean.getQiniuKey()!=null  ){
            url+=attachmentBean.getQiniuKey();
        }
        if(attachmentBean.getFileType()!=null  ){
            url+="."+attachmentBean.getFileType();
        }
        return url;
    }

}
