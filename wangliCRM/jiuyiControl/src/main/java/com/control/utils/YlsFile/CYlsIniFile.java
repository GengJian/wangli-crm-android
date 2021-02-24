package com.control.utils.YlsFile;

import com.control.utils.Func;
import com.control.utils.JiuyiLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class CYlsIniFile {

    byte m_Data[] = new byte[]{};
    String m_strFile = "";
    String m_strEncoding = "UTF-8";
    public HashMap<String, HashMap<String, String>> m_pMapValue = null;
    public ArrayList<String> m_ayAllList = null;
    public ArrayList<String> m_aySection = null;

    public CYlsIniFile(byte[] pInitData) {
        m_Data = new byte[pInitData.length];
        System.arraycopy(pInitData, 0, m_Data, 0, pInitData.length);
        m_aySection = new ArrayList<String>();
        m_pMapValue = new HashMap<String, HashMap<String, String>>();
        ReadIniFile(m_ayAllList, m_aySection, m_pMapValue);
    }

    public CYlsIniFile(byte[] pInitData, int nBegin, int nEnd, String strEncoding) {
        if (strEncoding.length() > 0) {
            m_strEncoding = strEncoding;
        }
        int nLen = 0;
        if (nEnd > pInitData.length) {
            nEnd = pInitData.length;
        }
        nLen = nEnd - nBegin;
        if (nLen <= 0) {
            return;
        }
        m_Data = new byte[nLen];
        System.arraycopy(pInitData, nBegin, m_Data, 0, nLen);
        m_aySection = new ArrayList<String>();
        m_pMapValue = new HashMap<String, HashMap<String, String>>();
        ReadIniFile(m_ayAllList, m_aySection, m_pMapValue);
    }

    public CYlsIniFile(int nNameTpye) {
        m_strFile = CYlsFileBase.GetFileName(nNameTpye);
        CYlsFileBase file = new CYlsFileBase(m_strFile, false);
        try {
            m_Data = file.readBytes();
            m_aySection = new ArrayList<String>();
            m_pMapValue = new HashMap<String, HashMap<String, String>>();
            ReadIniFile(m_ayAllList, m_aySection, m_pMapValue);
            file.close();
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }

    public CYlsIniFile(String strFile) {
        m_strFile = strFile;
        CYlsFileBase file = new CYlsFileBase(m_strFile, false);
        try {
            m_Data = file.readBytes();
            m_aySection = new ArrayList<String>();
            m_pMapValue = new HashMap<String, HashMap<String, String>>();
            ReadIniFile(m_ayAllList, m_aySection, m_pMapValue);
            file.close();
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }

    private void ReadIniFile(ArrayList<String> ayList, ArrayList<String> ayRet, HashMap<String, HashMap<String, String>> ayMap) {
        if (ayRet == null) {
            ayRet = new ArrayList<String>();
        }
        ayRet.clear();
        if (ayMap == null) {
            ayMap = new HashMap<String, HashMap<String, String>>();
        }
        ayMap.clear();
        if (m_Data == null || m_Data.length <= 0) {
            return;
        }
        String strpcData = "";
        if (m_strEncoding.length() > 0) {
            strpcData = BytesClass.BytesToString(m_Data, 0, m_Data.length, m_strEncoding);
        } else {
            strpcData = BytesClass.BytesToString(m_Data, 0, m_Data.length);
        }
        strpcData.replaceAll("\r\n", "\n");
        strpcData.replaceAll("\r", "\n");
        if (ayList == null) {
            ayList = new ArrayList<String>();
        }
        HashMap<String, String> mapValue = null;
        Func.split(strpcData, "\n", ayList);
        for (int i = 0; i < ayList.size(); i++) {
            String strValue = ayList.get(i);
            strValue = strValue.trim();
            if (strValue.length() <= 0) {
                continue;
            }
            if (strValue.charAt(0) == ';') {
                continue;
            }
            if (strValue.charAt(0) == '[') {
                int iIndex = strValue.indexOf(']');
                if (iIndex > 0) //新的[]
                {
                    if (ayRet.size() > 0 && mapValue != null)//赋值
                    {
                        ayMap.put(ayRet.get(ayRet.size() - 1), mapValue);
                    }
                    strValue = strValue.substring(1, iIndex);
                    strValue = strValue.toUpperCase();
                    if (ayRet.indexOf(strValue) < 0) {
                        ayRet.add(strValue);
                    }
                    mapValue = new HashMap<String, String>();
                    continue;
                }
            }
            int iIndex = strValue.indexOf("=");
            String strKey = strValue;
            if (iIndex > 0) {
                strKey = strValue.substring(0, iIndex);
                if (iIndex < strValue.length() - 1) {
                    strValue = strValue.substring(iIndex + 1);
                } else {
                    strValue = "";
                }
            }
            //strValue = i+"|"+strValue;//第几行+value
            if (mapValue != null) {
                mapValue.put(strKey.toUpperCase(), strValue);
            }
        }
        if (ayRet.size() > 0 && mapValue != null)//赋值
        {
            ayMap.put(ayRet.get(ayRet.size() - 1), mapValue);
        }
        return;
    }

    public void ReadIniFile(String strSection, HashMap<String, String> ayMap) {
        if (ayMap == null) {
            ayMap = new HashMap<String, String>();
        }
        ayMap.clear();
        if (m_pMapValue != null && m_pMapValue.containsKey(strSection.toUpperCase())) {
            ayMap = m_pMapValue.get(strSection.toUpperCase());
        }
    }

    public HashMap<String, String> GetValueMap(String strSection) {
        if (m_pMapValue != null && m_pMapValue.containsKey(strSection.toUpperCase())) {
            return m_pMapValue.get(strSection.toUpperCase());
        }
        return new HashMap<String, String>();
    }

    public String ReadIniFile(String strSection, String strKey, String strDefault) {
        HashMap<String, String> ayMap = new HashMap<String, String>();
        ReadIniFile(strSection, ayMap);
        if (ayMap.size() > 0 && ayMap.containsKey(strKey.toUpperCase())) {
            return ayMap.get(strKey.toUpperCase());
        }
        return strDefault;
    }

    public void WriteIniFile(String strSection, String strKey, String strValue) {
        if (m_pMapValue != null && m_pMapValue.containsKey(strSection.toUpperCase())) {
            HashMap<String, String> ayMap = m_pMapValue.get(strSection.toUpperCase());
            if (ayMap != null) {
                ayMap.put(strKey.toUpperCase(), strValue);
                m_pMapValue.put(strSection.toUpperCase(), ayMap);
            }
        }
    }

    public void WriteIniFile(String strSection, HashMap<String, String> ayMap) {
        int iIndex = m_aySection.indexOf(strSection.toUpperCase());
        if (iIndex < 0) {
            m_aySection.add(strSection.toUpperCase());
        }
        if (m_pMapValue != null) {
            m_pMapValue.put(strSection.toUpperCase(), ayMap);
        }
        WriteIniFile();
    }

    public void WriteIniFile() {
        if (m_strFile.length() <= 0) {
            return;
        }
        String strRet = "";
        for (int i = 0; i < m_aySection.size(); i++) {
            String strSection = m_aySection.get(i);
            strRet = strRet + "[" + strSection + "]" + "\r\n";
            if (m_pMapValue != null) {
                HashMap<String, String> ayMap = m_pMapValue.get(strSection);
                if (ayMap != null) {
                    Iterator<Entry<String, String>> iter = ayMap.entrySet().iterator();
                    while (iter.hasNext()) {
                        Entry<String, String> entry = iter.next();
                        String strkey = entry.getKey();
                        String strval = entry.getValue();
                        strRet = strRet + strkey + "=" + strval + "\r\n";
                    }
                }
            }
        }
        CYlsFileBase Inifile = new CYlsFileBase(m_strFile, true);
        try {
            if (Inifile != null) {
                try {
                    Inifile.wrireBytes(strRet.getBytes("UTF-8"));
                    Inifile.fileOut.flush();
                } finally {
                    Inifile.close();
                }
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }
}
