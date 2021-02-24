package messages.timchat.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import com.control.utils.Func;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.control.widget.recyclerView.BaseViewHolder;
import com.control.widget.entity.MultiItemEntity;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMFaceElem;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import messages.timchat.adapters.ChatAdapter;

/**
 * 自定义消息
 */
public class CustomMessage extends Message implements MultiItemEntity {

    public static final int TYPE_CUSTOM_LIST = 1;
    public static final int TYPE_CUSTOM_TEXT = 2;
    public static final int TYPE_CUSTOM_CONTENTONLY = 3;
    private int itemType;
    private String TAG = getClass().getSimpleName();

    private final int TYPE_TYPING = 14;
    private final int TYPE_CLIENT=1;
    private final int TYPE_CONTACT=2;
    private final int TYPE_ORDER=3;
    private final int TYPE_TASK=4;


    private Type type;
    private String desc;
    private String data;

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    private String typeValue;

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    private Long titleId;

    public CustomMessage(TIMMessage message){
        this.message = message;
        TIMCustomElem elem = (TIMCustomElem) message.getElement(0);
        parse(elem.getData());

    }
    public CustomMessage(){
        super();
    }
    public CustomMessage(int useraction,int typeId,String typeValue,String titleValue, Long titleId){
        message = new TIMMessage();
        switch (useraction){
            case TYPE_CLIENT:
                type=Type.CLIENT;
                break;
            case TYPE_CONTACT:
                type=Type.CONTACT;
                break;
            case TYPE_ORDER:
                type=Type.ORDER;
                break;
            case TYPE_TASK:
                type=Type.TASK;
                break;
        }
        String data = "";
        JSONObject dataJson = new JSONObject();
        try{
            dataJson.put("userAction",useraction);
            dataJson.put("typeId",typeId);
            this.titleId=titleId;
            dataJson.put("typeValue",typeValue);
            this.typeValue=typeValue;
            dataJson.put("titleValue",titleValue);
            dataJson.put("titleId",titleId);
            data = dataJson.toString();
        }catch (JSONException e){
            Log.e(TAG, "generate json error");
        }
        TIMCustomElem elem = new TIMCustomElem();
        elem.setData(data.getBytes());
        message.addElement(elem);
    }

    public CustomMessage(Type type){
        message = new TIMMessage();
        String data = "";
        JSONObject dataJson = new JSONObject();
        try{
            switch (type){
                case TYPING:
                    dataJson.put("userAction",TYPE_TYPING);
                    dataJson.put("actionParam","EIMAMSG_InputStatus_Ing");
                    data = dataJson.toString();
            }
        }catch (JSONException e){
            Log.e(TAG, "generate json error");
        }
        TIMCustomElem elem = new TIMCustomElem();
        elem.setData(data.getBytes());
        message.addElement(elem);
    }
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {

        return itemType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private void parse(byte[] data){
        type = Type.INVALID;
        try{
            String str = new String(data, "UTF-8");
            JSONObject jsonObj = new JSONObject(str);
            int action = jsonObj.getInt("userAction");
            switch (action){
                case TYPE_TYPING:
                    type = Type.TYPING;
                    this.data = jsonObj.getString("actionParam");
                    if (this.data.equals("EIMAMSG_InputStatus_End")){
                        type = Type.INVALID;
                    }
                    break;
                case TYPE_CLIENT:
                    type = Type.CLIENT;
                    this.data = jsonObj.getString("titleValue");
                    this.desc=jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    break;
                case TYPE_CONTACT:
                    type = Type.CONTACT;
                    this.data = jsonObj.getString("titleValue");
                    this.desc=jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    break;
                case TYPE_ORDER:
                    type = Type.ORDER;
                    this.data = jsonObj.getString("titleValue");
                    this.desc=jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    break;
                case TYPE_TASK:
                    type = Type.TASK;
                    this.data = jsonObj.getString("titleValue");
                    this.desc=jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    break;
            }

        }catch (IOException | JSONException e){
            Log.e(TAG, "parse json error");

        }
    }

    /**
     * 显示消息
     *
     * @param viewHolder 界面样式
     * @param context    显示消息的上下文
     */
    @Override
    public void showMessage(ChatAdapter.ViewHolder viewHolder, Context context) {
        clearView(viewHolder);
        if (checkRevoke(viewHolder)) return;
        boolean hasText = false;
        TextView tv = new TextView(JiuyiMainApplication.getIns());
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setTextColor(JiuyiMainApplication.getIns().getResources().getColor(isSelf() ? R.color.white : R.color.black));
        List<TIMElem> elems = new ArrayList<>();
        for (int i = 0; i < message.getElementCount(); ++i){
            elems.add(message.getElement(i));
            if (message.getElement(i).getType() == TIMElemType.Custom){
                hasText = true;
            }
        }
        try{
            String str = getString(elems, context).toString();
            final JSONObject jsonObj = new JSONObject(str);
            int action = jsonObj.getInt("userAction");
            switch (action){
                case TYPE_CLIENT:
                    type = Type.CLIENT;
                    this.data = jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    tv.setText(this.data);
                    getBubbleView(viewHolder).addView(tv);
                    showStatus(viewHolder);
                    break;
                case TYPE_CONTACT:
                    type = Type.CONTACT;
                    this.data = jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    tv.setText(this.data);
                    getBubbleView(viewHolder).addView(tv);
                    showStatus(viewHolder);
                    break;
                case TYPE_ORDER:
                    type = Type.ORDER;
                    this.data = jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    tv.setText(this.data);
                    getBubbleView(viewHolder).addView(tv);
                    showStatus(viewHolder);
                    break;
                case TYPE_TASK:
                    type = Type.TASK;
                    this.data = jsonObj.getString("titleValue");
                    this.titleId=jsonObj.getLong("titleId");
                    this.typeValue=jsonObj.getString("typeValue");
                    tv.setText(this.data);
                    getBubbleView(viewHolder).addView(tv);
                    showStatus(viewHolder);
                    break;
            }

        }catch (JSONException e){
            Log.e(TAG, "parse json error");

        }
    }
    /**
     * 显示消息
     *
     * @param viewHolder 界面样式
     * @param context    显示消息的上下文
     */
    public void showMessage2(BaseViewHolder viewHolder, Context context) {
//        clearView(viewHolder);
//        if (checkRevoke(viewHolder)) return;
        boolean hasText = false;
        TextView tv = new TextView(JiuyiMainApplication.getIns());
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv.setTextColor(JiuyiMainApplication.getIns().getResources().getColor(isSelf() ? R.color.white : R.color.black));
        List<TIMElem> elems = new ArrayList<>();
        for (int i = 0; i < message.getElementCount(); ++i){
            elems.add(message.getElement(i));
            if (message.getElement(i).getType() == TIMElemType.Custom){
                hasText = true;
            }
        }
        SpannableStringBuilder stringBuilder = getString(elems, context);
        if (!hasText){
            stringBuilder.insert(0," ");
        }
        tv.setText(stringBuilder);
    }



    /**
     * 获取消息摘要
     */
    @Override
    public String getSummary() {
        String str = getRevokeSummary();
        if (str != null) return str;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<message.getElementCount(); ++i){
            switch (message.getElement(i).getType()){
                case Face:
                    TIMFaceElem faceElem = (TIMFaceElem) message.getElement(i);
                    byte[] data = faceElem.getData();
                    if (data != null){
                        result.append(new String(data, Charset.forName("UTF-8")));
                    }
                    break;
                case Text:
                    TIMTextElem textElem = (TIMTextElem) message.getElement(i);
                    result.append(textElem.getText());
                    break;
                case Custom:
                    TIMCustomElem customElem = (TIMCustomElem) message.getElement(i);
                    byte[] customdata = customElem.getData();
                    if(customdata!=null){
                        result.append(new String(customdata, Charset.forName("UTF-8")));
                    }
                    break;
            }

        }
        String sSummary="";
        sSummary=result.toString();
        if(!Func.IsStringEmpty(sSummary)){
            try{
                final JSONObject jsonObj = new JSONObject(sSummary);
                int action = jsonObj.getInt("userAction");
                switch (action){
                    case TYPE_CLIENT:
                        type = Type.CLIENT;
                        this.titleId=jsonObj.getLong("titleId");
                        this.data = jsonObj.getString("titleValue");
                        this.typeValue= jsonObj.getString("typeValue");
                        return this.data;
                    case TYPE_CONTACT:
                        type = Type.CONTACT;
                        this.titleId=jsonObj.getLong("titleId");
                        this.data = jsonObj.getString("titleValue");
                        this.typeValue= jsonObj.getString("typeValue");
                        return this.data;
                    case TYPE_ORDER:
                        type = Type.ORDER;
                        this.titleId=jsonObj.getLong("titleId");
                        this.data = jsonObj.getString("titleValue");
                        this.typeValue= jsonObj.getString("typeValue");
                        return this.data;
                    case TYPE_TASK:
                        type = Type.TASK;
                        this.titleId=jsonObj.getLong("titleId");
                        this.data = jsonObj.getString("titleValue");
                        this.typeValue= jsonObj.getString("typeValue");
                        return this.data;
                    default:return result.toString();
                }

            }catch (JSONException e){
                Log.e(TAG, "parse json error");

            }

        }
        return result.toString();
    }
    public  String getsubtitle(String messageSummary){
        JsonObject jsonObject = new JsonParser().parse(messageSummary).getAsJsonObject();
        if(jsonObject!=null){
            String summary=jsonObject.get("Summary").toString();
            if(!Func.IsStringEmpty(summary)){
                JsonObject josummary=new JsonParser().parse(summary).getAsJsonObject();
                JsonElement jsonObjectsummary=josummary.get("subTitle");
                if(jsonObjectsummary!=null){
                    return  josummary.get("subTitle").toString().replace("\"","");
                }else{
                    return  "";
                }
            }else{
                return  "";
            }
        }else{
            return  "";
        }
    }

    public static SpannableStringBuilder getString(List<TIMElem> elems, Context context){
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        for (int i = 0; i<elems.size(); ++i){
            switch (elems.get(i).getType()){
                case Face:
                    TIMFaceElem faceElem = (TIMFaceElem) elems.get(i);
                    int startIndex = stringBuilder.length();
                    try{
                        AssetManager am = context.getAssets();
                        InputStream is = am.open(String.format("emoticon/%d.gif", faceElem.getIndex()));
                        if (is == null) continue;
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        Matrix matrix = new Matrix();
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        matrix.postScale(2, 2);
                        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                                width, height, matrix, true);
                        ImageSpan span = new ImageSpan(context, resizedBitmap, ImageSpan.ALIGN_BASELINE);
                        stringBuilder.append(String.valueOf(faceElem.getIndex()));
                        stringBuilder.setSpan(span, startIndex, startIndex + getNumLength(faceElem.getIndex()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        is.close();
                    }catch (IOException e){

                    }
                    break;
                case Text:
                    TIMTextElem textElem = (TIMTextElem) elems.get(i);
                    stringBuilder.append(textElem.getText());
                    break;
                case Custom:
                    TIMCustomElem customElem = (TIMCustomElem) elems.get(i);
                    byte[] customdata = customElem.getData();
                    if(customdata!=null){
                        stringBuilder.append(new String(customdata, Charset.forName("UTF-8")));
                    }
                    break;
            }

        }
        return stringBuilder;
    }
    private static int getNumLength(int n){
        return String.valueOf(n).length();
    }


    /**
     * 保存消息或消息文件
     */
    @Override
    public void save() {

    }

    public enum Type{
        TYPING,
        INVALID,
        CLIENT,
        CONTACT,
        ORDER,
        TASK
    }
}
