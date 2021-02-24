package customer.Utils;

import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.wanglicrm.android.R;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.LubanOptions;
import org.devio.takephoto.model.TakePhotoOptions;

import java.io.File;


/**
 * - 支持通过相机拍照获取图片
 * - 支持从相册选择图片
 * - 支持从文件选择图片
 * - 支持多图选择
 * - 支持批量图片裁切
 * - 支持批量图片压缩
 * - 支持对图片进行压缩
 * - 支持对图片进行裁剪
 * - 支持对裁剪及压缩参数自定义
 * - 提供自带裁剪工具(可选)
 * - 支持智能选取及裁剪异常处理
 * - 支持因拍照Activity被回收后的自动恢复
 * Author: crazycodeboy
 * Date: 2016/9/21 0007 20:10
 * Version:4.0.0
 * 技术博文：http://www.devio.org
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class CustomHelper {
    private View rootView;
    private RadioGroup rgCrop, rgCompress, rgFrom, rgCropSize, rgCropTool, rgShowProgressBar, rgPickTool, rgCompressTool, rgCorrectTool,
        rgRawFile;
    private EditText etCropHeight, etCropWidth, etLimit, etSize, etHeightPx, etWidthPx;

    public static CustomHelper of(View rootView) {
        return new CustomHelper(rootView);
    }

    private CustomHelper(View rootView) {
        this.rootView = rootView;
        init();
    }

    private void init() {
        rgCrop = (RadioGroup) rootView.findViewById(R.id.rgCrop);
        rgCompress = (RadioGroup) rootView.findViewById(R.id.rgCompress);
        rgCompressTool = (RadioGroup) rootView.findViewById(R.id.rgCompressTool);
        rgCropSize = (RadioGroup) rootView.findViewById(R.id.rgCropSize);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgFrom);
        rgPickTool = (RadioGroup) rootView.findViewById(R.id.rgPickTool);
        rgRawFile = (RadioGroup) rootView.findViewById(R.id.rgRawFile);
        rgCorrectTool = (RadioGroup) rootView.findViewById(R.id.rgCorrectTool);
        rgShowProgressBar = (RadioGroup) rootView.findViewById(R.id.rgShowProgressBar);
        rgCropTool = (RadioGroup) rootView.findViewById(R.id.rgCropTool);
        etCropHeight = (EditText) rootView.findViewById(R.id.etCropHeight);
        etCropWidth = (EditText) rootView.findViewById(R.id.etCropWidth);
        etLimit = (EditText) rootView.findViewById(R.id.etLimit);
        etSize = (EditText) rootView.findViewById(R.id.etSize);
        etHeightPx = (EditText) rootView.findViewById(R.id.etHeightPx);
        etWidthPx = (EditText) rootView.findViewById(R.id.etWidthPx);



    }

    public void onClick(View view, TakePhoto takePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        switch (view.getId()) {
            case R.id.btnPickBySelect:
                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
//                int limit = Integer.parseInt(etLimit.getText().toString());
//                if (limit > 1) {
//                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
//                        takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
//                    } else {
//                        takePhoto.onPickMultiple(limit);
//                    }
//                    return;
//                }
//                if (rgFrom.getCheckedRadioButtonId() == R.id.rbFile) {
//                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
//                        takePhoto.onPickFromDocumentsWithCrop(imageUri, getCropOptions());
//                    } else {
//                        takePhoto.onPickFromDocuments();
//                    }
//                    return;
//                } else {
//                    if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
//                        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
//                    } else {
//                        takePhoto.onPickFromGallery();
//                    }
//                }
                break;
            case R.id.btnPickByTake:
                if (rgCrop.getCheckedRadioButtonId() == R.id.rbCropYes) {
                    takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                } else {
                    takePhoto.onPickFromCapture(imageUri);
                }
                break;
            default:
                break;
        }
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {
        int maxSize = 10240;
        int width = 800;
        int height = 800;
        boolean showProgressBar = false;
        boolean enableRawFile = true;
        CompressConfig config;
        config = new CompressConfig.Builder().setMaxSize(maxSize)
                .setMaxPixel(width >= height ? width : height)
                .enableReserveRaw(enableRawFile)
                .create();
        takePhoto.onEnableCompress(config, showProgressBar);
    }

    private CropOptions getCropOptions() {
        int height = 800;
        int width = 800;
        boolean withWonCrop =false;
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }

}
