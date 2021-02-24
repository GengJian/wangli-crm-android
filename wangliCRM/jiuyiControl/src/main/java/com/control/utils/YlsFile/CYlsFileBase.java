package com.control.utils.YlsFile;

import android.content.Context;


import com.control.utils.JiuyiLog;
import com.control.utils.Rc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ****************************************************************
 * 文件名称:CYlsFileBase.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:文件操作的类
 * ****************************************************************
 */
public class CYlsFileBase {

    public File file;
	public FileInputStream fileIn;
    FileOutputStream fileOut;
    public static final String FundComClassFile = "Fund_Com_Class.ini";
    public static final int SysConfigPath_ = 1;
    public static final int SysSettingPath_ = 2;
    public static final int UserL2WelcomeBmp = 23;//L2的欢迎界面图片 (旧版)
	public static final int TztWelcomeBmp = 24;//欢迎界面图片（新版）

    private final String mStrJiaMi = "2F9FEDC8056F9FE6A657043D71009BE5";
    public CYlsFileBase(String strFile, int nPathType, boolean bNewCreate) {
        String strPath = CYlsFileBase.GetFilePath(nPathType);
        CreateFileBase(strPath, strFile, bNewCreate);
    }

    public CYlsFileBase(int nNameTpye, boolean bNewCreate) {
        String strPath = CYlsFileBase.GetFilePath(nNameTpye);//data/data/cn.com.gjzq.yjb.jy/TztData/Config
        String strFile = CYlsFileBase.GetFileName(nNameTpye);//Systerm.cfg
        CreateFileBase(strPath, strFile, bNewCreate);
    }

	/**
	 *
	 * @param nNameTpye
	 * @param strFile		自定义文件名
	 * @param bNewCreate
	 */
	public CYlsFileBase(int nNameTpye, String strFile, boolean bNewCreate) {
		String strPath = CYlsFileBase.GetFilePath(nNameTpye);//data/data/cn.com.gjzq.yjb.jy/TztData/Config
		CreateFileBase(strPath, strFile, bNewCreate);
	}
    
    public CYlsFileBase(String strFileName, boolean bNewCreate)
    {
    	String strPath = CYlsFileBase.GetFilePath(SysSettingPath_);
        CreateFileBase(strPath, strFileName, bNewCreate);
    }
    
    public CYlsFileBase(String strPath, String strFileName, boolean bNewCreate)
    {
    	CreateFileBase(strPath, strFileName, bNewCreate);
    }

    void CreateFileBase(String strPath, String strFile, boolean bNewCreate) {
        try {
            file = new File(strPath, strFile);
            if (bNewCreate && file.exists()) {
            	fileDeleteAndNewCreate();
            }
            if (!file.exists()) {
                file.createNewFile();//data/data/com.newtzt/TztData/Config/Systerm.cfg
            }

            fileIn = new FileInputStream(file);
            fileOut = new FileOutputStream(file, true);//c.openFileOutput(strFile,c.MODE_PRIVATE);
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }
    
    
	public void fileDeleteAndNewCreate() {
		try {
			file.delete();
			file.createNewFile();
		} catch (Exception e) {
		}
	}

    public FileOutputStream getFileOut() {
		return fileOut;
	}

	public void wrireBytes(byte[] s) throws Exception {
		if(s == null || fileOut == null)
			return;
		
		fileOut.write(mStrJiaMi.getBytes());
		
//		s = tztEncrypt.ajaxEncrypt(s);
				
        fileOut.write(s);
    }

    public void wrireString(String s) throws Exception {
    	if (s == null || fileOut == null)
    	{
    		return;
    	}
    	
    	wrireBytes(s.getBytes());
    }

    public byte[] readBytes() throws Exception {
        int nLen = length();
        if (nLen > 0) {
            return readBytes(0, length());
        }
        return null;
    }

    public byte[] readBytes(int s, int l) 
    {
        byte[] pData = new byte[l];
        try 
        {
        	fileIn.read(pData, s, l);
        } catch (IOException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
            return null;
        }
        
        if (pData != null)
        {
        	if (new String(pData).toString().equals(mStrJiaMi))
        	{
        		return null;
        	}
        	else if (pData.length > mStrJiaMi.getBytes().length)
        	{
        		byte[] pTempData = new byte[mStrJiaMi.getBytes().length];
            	System.arraycopy(pData, 0, pTempData, 0, pTempData.length);
            	
            	byte[] pJiaMiData = new byte[pData.length - mStrJiaMi.getBytes().length];
            	System.arraycopy(pData, pTempData.length, pJiaMiData, 0, pJiaMiData.length);
            	
            	String strRetData = new String(pTempData);
            	if (strRetData!=null && strRetData.equals(mStrJiaMi))
            	{
            		pData = pJiaMiData;
            	}
        	}
        }
        return pData;
    }

    public String readString(int s, int l) throws Exception {
        byte[] pData = new byte[l];
        pData = readBytes(s, l);
        return BytesClass.BytesToString(pData, 0, l);
    }

    public int length() {
        return (int) file.length();
    }

    public void close() throws Exception {
        fileIn.close();
        fileOut.close();
    }

    public static String GetFullFileName(int nNameTpye) {
        String strPath = GetFilePath(nNameTpye);
        String strName = GetFileName(nNameTpye);
        return strPath + "/" + strName;
    }

    public static String GetFileName(int nNameTpye) {
        String strName = "tempdata.dat";
        switch (nNameTpye) {
            case UserL2WelcomeBmp:
            {
                return "l2Welcome.dat";
            }
            default: {
                return strName;
            }
        }
    }

    public static String GetFilePath(int nNameTpye) {
        String strName = "data/data/" + Rc.getApplication().getPackageName()+ "/TztData";

        File file = new File(strName);
        if (!file.exists()) {
            file.mkdirs();
        }
        switch (nNameTpye) {
            default: {
                return strName;
            }
            case SysConfigPath_:{
                strName = strName + "/Config";
            }
            break;
            case SysSettingPath_: {
                strName = strName + "/Setting";
            }
            break;
            case UserL2WelcomeBmp:
            {
                strName = strName +"/Data";
            }
            break;
			case TztWelcomeBmp: {
				strName = strName + "/TztWelcomeBmp";
			}
			break;
		}
        file = new File(strName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return strName;
    }
    
    /**
	 * 
	 * @param strFileName  不带后缀名的文件名称
	 * @param bytes 该文件里要保存的数据
	 * @return
	 */
	public static boolean SaveFileToSys(String strFileName, byte[] bytes)
	{
		try
		{
			if (strFileName == null || strFileName.length() <= 0)
			{
				return false;
			}
			
			int nPointPos = strFileName.indexOf(".");
			if (nPointPos > 0)
			{
				strFileName = strFileName.substring(0, nPointPos);
			}
			strFileName += ".file";
			
			CYlsFileBase initFileBase = new CYlsFileBase(Rc.getApplication().getFilesDir().getPath(), strFileName, true);
			initFileBase.close();
		}
		catch (Exception e)
		{
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String ReadFileToSys(String strFileName)
	{
		try
		{
			if (strFileName == null || strFileName.length() <= 0)
			{
				return null;
			}
			
			int nPointPos = strFileName.indexOf(".");
			if (nPointPos > 0)
			{
				strFileName = strFileName.substring(0, nPointPos);
			}
			strFileName += ".file";
			
			String strRet = null;
			CYlsFileBase initFileBase = new CYlsFileBase(Rc.getApplication().getFilesDir().getPath(), strFileName, false);
			int len = (int) initFileBase.file.length();
			if (len > 0)
			{
				FileInputStream fstream = new FileInputStream(initFileBase.file);
				byte[] buffer = new byte[len];
				fstream.read(buffer);
//				buffer = tztEncrypt.ajaxEncrypt(buffer);// 解密
				strRet = new String(buffer);
				fstream.close();
			}
			initFileBase.close();
			
			return strRet;
		}
		catch (Exception e)
		{
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			return null;
		}
	}
	
    public static byte[] ReadFileToSys(Context context, String strFileName)
	{
    	byte[] strRet = null;
		try
		{
			if (strFileName == null || strFileName.length() <= 0)
			{
				return null;
			}
			//zhengss 一创需要下面一句
			//if (Rc.cfg.QuanShangID != Pub.QSID_FIRSTCAPITALGENERALAPPPHONE) {//fcy 20150828 解决一创拍照上传路径问题
				String strPath = context.getFilesDir().getPath();
				if (strFileName.lastIndexOf("/") > 0)
				{
					String strTmpPath = strFileName.substring(0, strFileName.lastIndexOf("/"));
					if (strFileName.indexOf("/") != 0)
					{
						strPath += "/" + strTmpPath;
					}
					else
					{
						strPath += strTmpPath;
					}
					strFileName = strFileName.substring(strFileName.lastIndexOf("/") + 1);
				}
				
				int nPointPos = strFileName.indexOf(".");
				if (nPointPos < 0)
				{
					strFileName += ".file";
				}
				strFileName = strPath + strFileName;
			//}
			
			CYlsFileBase initFileBase = new CYlsFileBase(Rc.getApplication().getFilesDir().getPath(), strFileName, false);
			int len = (int) initFileBase.file.length();
			if (len > 0)
			{
				FileInputStream fstream = new FileInputStream(initFileBase.file);
				strRet = new byte[len];
				fstream.read(strRet);
//				strRet = tztEncrypt.ajaxEncrypt(buffer);// 解密
//				buffer = tztEncrypt.ajaxEncrypt(buffer);// 解密
//				strRet = new String(buffer);
				fstream.close();
			}
			initFileBase.close();
		}
		catch (Exception e)
		{
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		}
		return strRet;
	}
}
