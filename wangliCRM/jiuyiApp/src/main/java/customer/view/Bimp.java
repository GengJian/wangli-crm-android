package customer.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import customer.entity.ImageBean;
import customer.entity.Media;

/**
 * Created by wcz on 2016/10/14.
 */
public class Bimp {
    public static int max = 0;

    public static ArrayList<ImageBean> getTempSelectBitmap() {
        return tempSelectBitmap;
    }

    public static void setTempSelectBitmap(ArrayList<ImageBean> tempSelectBitmap) {
        Bimp.tempSelectBitmap = tempSelectBitmap;
    }

    public static ArrayList<ImageBean> tempSelectBitmap = new ArrayList<ImageBean>();   //选择的图片的临时列表

    public static ArrayList<ImageBean> getTempViewBitmap() {
        return tempViewBitmap;
    }

    public static void setTempViewBitmap(ArrayList<ImageBean> tempViewBitmap) {
        Bimp.tempViewBitmap = tempViewBitmap;
    }

    public static ArrayList<ImageBean> tempViewBitmap= new ArrayList<ImageBean>();   //选择的图片的临时列表

    public static ArrayList<Media> getTempVideoSelectBitmap() {
        return tempVideoSelectBitmap;
    }

    public static void setTempVideoSelectBitmap(ArrayList<Media> tempVideoSelectBitmap) {
        Bimp.tempVideoSelectBitmap = tempVideoSelectBitmap;
    }

    public static ArrayList<Media> tempVideoSelectBitmap = new ArrayList<Media>();   //选择的图片的临时列表
    public static Bitmap revitionImageSize(String path) throws IOException {
        if (path==null){
            return null;
        }
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 1000)
                    && (options.outHeight >> i <= 1000)) {
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }
}