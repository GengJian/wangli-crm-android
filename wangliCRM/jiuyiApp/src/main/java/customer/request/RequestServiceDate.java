package customer.request;

import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;

import java.util.List;

import commonlyused.bean.ServerDate;
import customer.entity.MemberchooseBean;
import customer.shared.MemberchooseConditionShared;

/**
 * @author my
 */
public class RequestServiceDate {
    public static RequestServiceDate Instance;

    public static RequestServiceDate getIns() {
        if (Instance == null) {
            Instance = new RequestServiceDate();
        }
        return Instance;
    }
    public void getServerDate(){
        JiuyiHttp.GET("date/today")
                .tag("request_get_")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ServerDate>() {
                    @Override
                    public void onSuccess(ServerDate data) {
                        if(data!=null){
                            Rc.serviceDate=data.getToday();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
    }
}
