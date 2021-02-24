package customer.Utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.google.zxing.common.StringUtils;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.os.Environment.MEDIA_MOUNTED;

public class FileUtils {
	private static final String JPEG_FILE_PREFIX = "IMG_";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/PhotoUpload/";

	public static void saveBitmap(Bitmap bm, String picName) {
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			Log.i("Tag","width =" + bm.getWidth() + " height =" + bm.getHeight());
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}
	
	public static void delFile(String fileName){
		File file = new File(SDPATH + fileName);
		if(file.isFile()){
			file.delete();
        }
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;
		
		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); 
			else if (file.isDirectory())
				deleteDir(); 
		}
		dir.delete();
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	/**
	 * 初始化图片路径
	 *
	 * @return
	 */
	public static String iniFilePath(Activity act) {
		String filepath = null;
		String path = null;
		File fileSD = null;

		// 准备存储位置
		boolean sdExist = Environment.getExternalStorageState()
				.equals(android.os.Environment.MEDIA_MOUNTED);
		if (!sdExist) {
			Toast.makeText(act,"没有找到SD存储卡", Toast.LENGTH_SHORT).show();
			return null;

		} else {
			//TODO 内容提示完善
			path = Environment.getExternalStorageDirectory().getPath() + "/yourneed/Camera";
			fileSD = new File(path);
			if (fileSD.exists()) {
				filepath = path + "/" + System.currentTimeMillis() + ".jpg";
			} else {
				fileSD.mkdir();
				filepath = fileSD.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
			}
			return filepath;
		}
	}
	public static File createTmpFile(Context context) throws IOException {
		File dir = null;
		if (TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
			dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
			if (!dir.exists()) {
				dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera");
				if (!dir.exists()) {
					dir = getCacheDirectory(context, true);
				}
			}
		} else {
			dir = getCacheDirectory(context, true);
		}
		return File.createTempFile(JPEG_FILE_PREFIX, JPEG_FILE_SUFFIX, dir);
	}


	private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

	/**
	 * Returns application cache directory. Cache directory will be created on SD card
	 * <i>("/Android/data/[app_package_name]/cache")</i> if card is mounted and app has appropriate permission. Else -
	 * Android defines cache directory on device's file system.
	 *
	 * @param context Application context
	 * @return Cache {@link File directory}.<br />
	 * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
	 * {@link Context#getCacheDir() Context.getCacheDir()} returns null).
	 */
	public static File getCacheDirectory(Context context) {
		return getCacheDirectory(context, true);
	}

	public static String getRealPathFromURI(Context context,Uri contentURI) {
		String result;
		Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
		if (cursor == null) { // Source is Dropbox or other similar local file path
			result = contentURI.getPath();
		} else {
			cursor.moveToFirst();
			int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
			result = cursor.getString(idx);
			cursor.close();
		}
		return result;
	}

	/**
	 * Returns application cache directory. Cache directory will be created on SD card
	 * <i>("/Android/data/[app_package_name]/cache")</i> (if card is mounted and app has appropriate permission) or
	 * on device's file system depending incoming parameters.
	 *
	 * @param context        Application context
	 * @param preferExternal Whether prefer external location for cache
	 * @return Cache {@link File directory}.<br />
	 * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
	 * {@link Context#getCacheDir() Context.getCacheDir()} returns null).
	 */
	public static File getCacheDirectory(Context context, boolean preferExternal) {
		File appCacheDir = null;
		String externalStorageState;
		try {
			externalStorageState = Environment.getExternalStorageState();
		} catch (NullPointerException e) { // (sh)it happens (Issue #660)
			externalStorageState = "";
		} catch (IncompatibleClassChangeError e) { // (sh)it happens too (Issue #989)
			externalStorageState = "";
		}
		if (preferExternal && MEDIA_MOUNTED.equals(externalStorageState) && hasExternalStoragePermission(context)) {
			appCacheDir = getExternalCacheDir(context);
		}
		if (appCacheDir == null) {
			appCacheDir = context.getCacheDir();
		}
		if (appCacheDir == null) {
			String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
			appCacheDir = new File(cacheDirPath);
		}
		return appCacheDir;
	}

	/**
	 * Returns individual application cache directory (for only image caching from ImageLoader). Cache directory will be
	 * created on SD card <i>("/Android/data/[app_package_name]/cache/uil-images")</i> if card is mounted and app has
	 * appropriate permission. Else - Android defines cache directory on device's file system.
	 *
	 * @param context  Application context
	 * @param cacheDir Cache directory path (e.g.: "AppCacheDir", "AppDir/cache/images")
	 * @return Cache {@link File directory}
	 */
	public static File getIndividualCacheDirectory(Context context, String cacheDir) {
		File appCacheDir = getCacheDirectory(context);
		File individualCacheDir = new File(appCacheDir, cacheDir);
		if (!individualCacheDir.exists()) {
			if (!individualCacheDir.mkdir()) {
				individualCacheDir = appCacheDir;
			}
		}
		return individualCacheDir;
	}

	private static File getExternalCacheDir(Context context) {
		File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
		File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
		if (!appCacheDir.exists()) {
			if (!appCacheDir.mkdirs()) {
				return null;
			}
			try {
				new File(appCacheDir, ".nomedia").createNewFile();
			} catch (IOException e) {
			}
		}
		return appCacheDir;
	}

	private static final long MB = 1024 * 1024;

	public String getSizeByUnit(double size) {

		if (size == 0) {
			return "0K";
		}
		if (size >= MB) {
			double sizeInM = size / MB;
			return String.format(Locale.getDefault(), "%.1f", sizeInM) + "M";
		}
		double sizeInK = size / 1024;
		return String.format(Locale.getDefault(), "%.1f", sizeInK) + "K";
	}

	private static boolean hasExternalStoragePermission(Context context) {
		int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
		return perm == PackageManager.PERMISSION_GRANTED;
	}

	public static String fileSize(long size) {
		if (size <= 0) return "0";
		final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}


	/**
	 * To find out the extension of required object in given uri
	 * Solution by http://stackoverflow.com/a/36514823/1171484
	 */
	public static String getMimeType(Context context, Uri uri) {
		String extension;
		//Check uri format to avoid null
		if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
			//If scheme is a content
			extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
			if (TextUtils.isEmpty(extension)) {
				extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
			}
		} else {
			//If scheme is a File
			//This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file
			// name with spaces and special characters.
			extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
			if (TextUtils.isEmpty(extension)) {
				extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
			}
		}
		return extension;
	}

	public static String getMimeTypeByFileName(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	}

	public static int getDuration(File file) {

		int length = 0;
		try {
			MP3File mp3File = (MP3File) AudioFileIO.read(file);
			MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();

			// 单位为秒
			length = audioHeader.getTrackLength();

			return length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return length;
	}

	/**
	 * 得到mp3的时长
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static long getAmrDuration(File file) throws IOException {
		long duration = -1;
		int[] packedSize = { 12, 13, 15, 17, 19, 20, 26, 31, 5, 0, 0, 0, 0, 0, 0, 0 };
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			long length = file.length();//文件的长度
			int pos = 6;//设置初始位置
			int frameCount = 0;//初始帧数
			int packedPos = -1;
			/////////////////////////////////////////////////////
			byte[] datas = new byte[1];//初始数据值
			while (pos <= length) {
				randomAccessFile.seek(pos);
				if (randomAccessFile.read(datas, 0, 1) != 1) {
					duration = length > 0 ? ((length - 6) / 650) : 0;
					break;
				}
				packedPos = (datas[0] >> 3) & 0x0F;
				pos += packedSize[packedPos] + 1;
				frameCount++;
			}
			/////////////////////////////////////////////////////
			duration += frameCount * 20;//帧数*20
		} finally {
			if (randomAccessFile != null) {
				randomAccessFile.close();
			}
		}
		return duration;
	}

	public static String getAudioDuration(long nDuration0) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		String wrongSize = "0ms";
		if (nDuration0 == 0) {
			return wrongSize;
		}
		if (nDuration0 < 1) {
			fileSizeString = df.format((double) nDuration0) + "毫秒";
		} else if (nDuration0 < 60) {
			fileSizeString = df.format((double) nDuration0 ) + "秒";
		} else if (nDuration0 < 3600) {
			fileSizeString = df.format((double) nDuration0 / 60) + "分钟";
		} else {
			fileSizeString = df.format((double) nDuration0 / 3600) + "小时";
		}
		return fileSizeString;
	}
	public static String durationFormat(long duration) {
		String result;
		final StringBuilder builder = new StringBuilder();
		final Formatter formatter = new Formatter(builder, Locale.getDefault());


		int totalSec = (int) duration / 1000;


		int secs = totalSec % 60;
		int minutes = (totalSec / 60) % 60;
		int hours = (totalSec / 3600);
		builder.setLength(0);
		if (hours > 0) {
			result = formatter.format("%02d:%02d:%02d", hours, minutes, secs)
					.toString();
			formatter.close();
			return result;
		} else {
			result = formatter.format("%02d:%02d:%02d", hours, minutes, secs)
					.toString();
			formatter.close();
			return result;
		}
	}


	public static JSONObject jsonObject(Object obj, String[] fields) {
		JSONObject jsonObject = new JSONObject();
		Class clazz = obj.getClass();
		for (String field : fields) {
			try {
				Field declaredField = clazz.getDeclaredField(field);
				String name = declaredField.getName();
				declaredField.setAccessible(true);
				Object value = declaredField.get(obj);
				jsonObject.put(name, value);

			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
	public static Map<String,Object> jsonObject2(Object obj, String[] fields)  {
//		JSONObject jsonObject = new JSONObject();
		Map<String,Object> map= new HashMap<>();
		Class clazz = obj.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			String name = declaredField.getName();
			if(!isExist(fields,name)){
				declaredField.setAccessible(true);
				Object value = null;
				try {
					value = declaredField.get(obj);
					map.put(name, value);
//					jsonObject.put(name, value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}

		}

		return map;
	}

	private static boolean isExist(String[] fields, String name) {
		for (String field : fields) {
			if(name != null && name.equals(field)){
				return  true;
			}
		}
		return false;
	}


	public static final Bitmap grey(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Bitmap faceIconGreyBitmap = Bitmap
				.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(faceIconGreyBitmap);
		Paint paint = new Paint();
		ColorMatrix colorMatrix = new ColorMatrix();
		colorMatrix.setSaturation(0);
		ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
				colorMatrix);
		paint.setColorFilter(colorMatrixFilter);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return faceIconGreyBitmap;
	}

	/**
	 * 获取缓存目录
	 * @param context
	 * @return
	 */
	public static String getCachePath(Context context ){
		String cachePath ;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			//外部存储可用
			cachePath = context.getExternalCacheDir().getPath() ;

		}else {
			//外部存储不可用
			cachePath = context.getCacheDir().getPath() ;
		}
		return cachePath ;
	}



}
