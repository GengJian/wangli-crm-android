package customer.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

/**
 * Created by zhengss on 2016/10/14.
 */
public class BitmapUtils {
    public static Bitmap getCompressedBitmap(Activity act, String filepath) {
        Bitmap tempBitmap = null;
        float width = act.getResources().getDisplayMetrics().widthPixels;
        float height = act.getResources().getDisplayMetrics().heightPixels;
        try {
            if (width > 640) {
                tempBitmap = getSuitableBitmap(act, Uri.fromFile(new java.io.File(filepath)), 640, (640 / width) * height);
            } else {
                tempBitmap = getSuitableBitmap(act, Uri.fromFile(new java.io.File(filepath)), (int) width, (int) height);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tempBitmap;
    }

    public static Bitmap getSuitableBitmap(Activity act, Uri uri, float ww, float hh)
            throws FileNotFoundException {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeStream(act.getContentResolver().openInputStream(uri), null, newOpts);

        newOpts.inJustDecodeBounds = false;
        float w = newOpts.outWidth;
        float h = newOpts.outHeight;

        float wwh = 640f;//
        float hhh = (wwh / w) * h;//
        int be = 1;
        if (w > h && w > wwh) {
            be = (int) (newOpts.outWidth / wwh);
        } else if (w < h && h > hhh) {
            be = (int) (newOpts.outHeight / hhh);
            be += 1;
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeStream(act.getContentResolver().openInputStream(uri), null, newOpts);
//      return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        //其实是无效的,大家尽管尝试
        return bitmap;

//        int maxNumOfPixels = width * height;
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inJustDecodeBounds = true;
//        BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri), null, opts);
//        opts.inSampleSize = computeSampleSize(opts, -1, maxNumOfPixels);
//        opts.inJustDecodeBounds = false;
//        try {
//            return BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri), null,
//                    opts);
//        } catch (OutOfMemoryError err) {
//        }
//        return null;
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>400) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

}
