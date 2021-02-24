package customer.api;


import java.util.List;
import java.util.Map;

import commonlyused.bean.ContactBean;
import customer.entity.AttachmentBean;
import customer.entity.CompeteInfoBean;
import customer.entity.DemandPlanBean;
import customer.entity.EquipmentBean;
import customer.entity.GatheringPlanBean;
import customer.entity.MarketDynamicBean;
import customer.entity.MemberUpdateBean;
import customer.entity.ProductcheckBean;
import customer.entity.ProductdynamicBean;
import customer.entity.ProductimportBean;
import customer.entity.ProductinfoBean;
import customer.entity.ProductlicenseBean;
import customer.entity.ProductmaterialBean;
import customer.entity.ProductpurchaseBean;
import customer.entity.ProducttaxBean;
import customer.entity.ReceiveAddressBean;
import customer.entity.RecruitmentBean;
import customer.entity.UpdateAddressBean;
import customer.entity.UpdatePictureBean;
import io.reactivex.Observable;
import mine.bean.UpdateAvatarBean;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * @Description: 自定义Retrofit接口服务
 */
public interface CustomerService {
    @GET("address/page")
    Observable<ReceiveAddressBean> getAuthor();
/*    @Headers("Content-Type:application/x-www-form-urlencoded")
    @HTTP(method = "DELETE", path = Constant.NEWATTENTION, hasBody = true)
    @FormUrlEncoded
    Call<ResultInfo> setNotAttention(@Field("user_id") String user_id, @Field("token") String token, @Field("attu_id") String attu_id, @Field("type") String type);*/
/*@HTTP(method = "DELETE",path = "address/delete",hasBody = true)
Call<Void> remove (@Body Map<String, String> content);*/
    @DELETE("address/delete/{id}")
    Call<ResponseBody> remove(@Path("id") String commentId);

    @PUT("address/update")
    Call<UpdateAddressBean> updateExtras(
            @Body UpdateAddressBean bean);

    @PUT("address/update")
    Call<ReceiveAddressBean.ContentBean> updateAddress(
            @Body ReceiveAddressBean.ContentBean bean);

    @DELETE("favorite/delete/{id}")
    Call<ResponseBody> deleteFavorite(@Path("id") String id);

    @PUT("member/updateMessageBean")
    Call<MemberUpdateBean> updateMember(
            @Body MemberUpdateBean bean);

    @PUT("actual/update")
    Call<MemberUpdateBean> updateActual(
            @Body MemberUpdateBean bean);

    @PUT("product/update")
    Call<ProductinfoBean.ContentBean> updateProductInfo(
            @Body ProductinfoBean.ContentBean bean);
    @DELETE("product/delete/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") String id);

    @PUT("equipment/update")
    Call<EquipmentBean.ContentBean> updateEquipment(
            @Body EquipmentBean.ContentBean bean);
    @DELETE("equipment/delete/{id}")
    Call<ResponseBody> deleteEquipment(@Path("id") String id);

    @PUT("performance/update")
    Call<ProductdynamicBean.ContentBean> updateProductdynamic(
            @Body ProductdynamicBean.ContentBean bean);
    @DELETE("performance/delete/{id}")
    Call<ResponseBody> deleteProductdynamic(@Path("id") String id);
    @PUT("row_material/update")
    Call<ProductmaterialBean.ContentBean> updateProductmaterial(
            @Body ProductmaterialBean.ContentBean bean);
    @DELETE("row_material/delete/{id}")
    Call<ResponseBody> deleteProductmaterial(@Path("id") String id);
    @PUT("competition_product/update")
    Call<CompeteInfoBean.ContentBean> updateCompeteInfo(
            @Body CompeteInfoBean.ContentBean bean);
    @DELETE("competition_product/delete/{id}")
    Call<ResponseBody> deleteCompeteInfo(@Path("id") String id);
    @PUT("recruitment/update")
    Call<RecruitmentBean.ContentBean> updateRecruitment(
            @Body RecruitmentBean.ContentBean bean);
    @DELETE("recruitment/delete/{id}")
    Call<ResponseBody> deleteRecruitment(@Path("id") String id);
    @PUT("production_license/update")
    Call<ProductlicenseBean.ContentBean> updateProductlicense(
            @Body ProductlicenseBean.ContentBean bean);
    @DELETE("production_license/delete/{id}")
    Call<ResponseBody> deleteProductlicense(@Path("id") String id);
    @PUT("purchase_tender/update")
    Call<ProductpurchaseBean.ContentBean> updateProductpurchase(
            @Body ProductpurchaseBean.ContentBean bean);
    @DELETE("purchase_tender/delete/{id}")
    Call<ResponseBody> deleteProductpurchase(@Path("id") String id);
    @PUT("in_ex_port_info/update")
    Call<ProductimportBean.ContentBean> updateProductimport(
            @Body  ProductimportBean.ContentBean bean);
    @DELETE("in_ex_port_info/delete/{id}")
    Call<ResponseBody> deleteProductimport(@Path("id") String id);

    @PUT("tax_rating/update")
    Call<ProducttaxBean.ContentBean> updateProducttax(
            @Body  ProducttaxBean.ContentBean bean);
    @DELETE("tax_rating/delete/{id}")
    Call<ResponseBody> deleteProducttax(@Path("id") String id);

    @PUT("spot_check/update")
    Call<ProductcheckBean.ContentBean> updateProductcheck(
            @Body  ProductcheckBean.ContentBean bean);
    @DELETE("spot_check/delete/{id}")
    Call<ResponseBody> deleteProductcheck(@Path("id") String id);

    @PUT("market-trend/update")
    Call<MarketDynamicBean.ContentBean> updateMarketTrend(
            @Body  MarketDynamicBean.ContentBean bean);
    @DELETE("sales-contract/delete/{id}")
    Call<ResponseBody> deleteContract(@Path("id") String id);


    @DELETE("sales-billing/delete/{id}")
    Call<ResponseBody> deleteInvoice(@Path("id") String id);


    @PUT("contact/update")
    Call<ContactBean.ContentBean> updateContact(
            @Body  ContactBean.ContentBean bean);

    @DELETE("contact/delete/{id}")
    Call<ResponseBody> deleteContact(@Path("id") String id);

    @PUT("demand_plan/update")
    Call<DemandPlanBean.ContentBean> updateDemandPlan(
            @Body  DemandPlanBean.ContentBean bean);
    @PUT("gathering_plan/update")
    Call<GatheringPlanBean.ContentBean> updateGatheringPlan(
            @Body  GatheringPlanBean.ContentBean bean);

    @DELETE("demand_plan/delete/{id}")
    Call<ResponseBody> deleteDemandPlan(@Path("id") String id);

    @DELETE("gathering_plan/delete/{id}")
    Call<ResponseBody> deleteGatheringPlan(@Path("id") String id);

    @DELETE("risk-warn/delete-feed/{id}")
    Call<ResponseBody> deleteRisk(@Path("id") String id);

    @DELETE("market-trend/delete-feed/{id}")
    Call<ResponseBody> deleteMarketTrend(@Path("id") String id);

    @PUT("operator/update-picture")
    Call<UpdateAvatarBean> updatePicture(@Body  UpdateAvatarBean bean);
    @PUT("member/update_url")
    Call<UpdateAvatarBean> updateCustomerPicture(@Body  UpdateAvatarBean bean);

    @DELETE("member/label/remove/{id}/{name}")
    Call<ResponseBody> deleteLabel(@Path("id") String id,@Path("name") String name);

}
