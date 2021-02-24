package com.control.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ****************************************************************
 * 文件名称:JiuyiCopyDirectory.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:App文件夹和手机文件夹之间复制文件
 * ****************************************************************
 */
public class JiuyiCopyDirectory {
    /** 执行结果的回调接口 */
	private tztCallBack mCallBack;
    /** 源文件夹 */
	private String mFromUrl = "";
    /** 目标文件夹 */
	private String mToUrl = "";

    /**
     * App文件夹和手机文件夹之间复制文件
     * @param pCallBack
     * @param url1
     * @param url2
     */
	public JiuyiCopyDirectory(tztCallBack pCallBack, String url1, String url2){
		this.mCallBack = pCallBack;
		this.mFromUrl = url1;
		this.mToUrl = url2;
		
		try {
			doCopyDirectory();
			
			if(pCallBack != null){
				pCallBack.startDialog(DialogID.DialogDoNothing, "", "复制完成", 0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		}
	}

    /**
     * 具体执行App文件夹和手机文件夹之间复制文件
     * @throws IOException
     */
	private void doCopyDirectory() throws IOException {
		// 创建目标文件夹
		if(!new File(mToUrl).exists())
			(new File(mToUrl)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(mFromUrl)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 复制文件
				copyFile(file[i], new File(mToUrl + file[i].getName()));
			}
			if (file[i].isDirectory()) {
				// 复制目录
				String sourceDir = mFromUrl + File.separator + file[i].getName();
				String targetDir = mToUrl + File.separator + file[i].getName();
				copyDirectiory(sourceDir, targetDir);
			}
		}
	}

    /**
     * 复制文件
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException
     */
	private void copyFile(File sourceFile, File targetFile) throws IOException {
		if(targetFile.exists())
			targetFile.delete();
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();

		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

    /**
     * 复制文件夹
     * @param sourceDir 原文件夹
     * @param targetDir 目标文件夹
     * @throws IOException
     */
	private void copyDirectiory(String sourceDir, String targetDir) throws IOException {
		// 新建目标目录
		if(!(new File(targetDir)).exists())
			(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

    /**
     * 执行结果的回调接口
     */
	public interface tztCallBack {
		void startDialog(int nAction, String sTitle, String sContent, int nBtnType);
	}
}