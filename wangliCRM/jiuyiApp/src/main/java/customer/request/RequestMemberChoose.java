package customer.request;

import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;

import java.util.List;

import customer.entity.MemberchooseBean;
import customer.shared.MemberchooseConditionShared;

public class RequestMemberChoose {
    public static RequestMemberChoose Instance;

    public static RequestMemberChoose getIns() {
        if (Instance == null)
            Instance = new RequestMemberChoose();
        return Instance;
    }
    public List<MemberchooseBean> getMemberchooseList(){
        JiuyiHttp.GET("member/memberChoose")
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<MemberchooseBean>() {
                    @Override
                    public void onSuccess(MemberchooseBean data) {
                        if(data!=null){
                            MemberchooseConditionShared.getIns().setmMemberchooseBean(data);
                        }
                        JiuyiLog.e("httplogin","request onSuccess!" + data);
//                        return null;
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
//                        DialogBulid bulid = DialogStuct.bulid()
//                                .setTitle("系统信息")
//                                .setContent(errMsg)
//                                .setLeftText("确认");
//                        bulid.startDialog();
                    }
                });
        return null;
    }
}
