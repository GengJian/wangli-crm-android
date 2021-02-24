package com.control.utils.xmlnote;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.widget.Toast;

import com.control.utils.JiuyiLog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class JiuyiXmlDom {
	public String getXmlFromUrl(String path) {
		StringBuffer str = new StringBuffer();
		try {
			URL url = new URL(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				str.append(line);
			}
			in.close();
		} catch (Exception e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e) + "\r\n" + path);
		}
		return str.toString();
	}

	public String readXMLFromFile(Context activity, String xmlFile, boolean useConfigDir) {
		InputStream is = null;
		File file = null;
		File sdCard = null;
		Writer writer = new StringWriter();
		boolean exists = false;

		if (useConfigDir) {
			sdCard = Environment.getExternalStorageDirectory();
			file = new File(sdCard.getAbsolutePath() + "/kjv/config/" + xmlFile);

			if (!(file == null)) {
				if (exists = file.exists())
					try {
						is = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
					}
			}
		}

		if (!exists) {
			AssetManager assetManager = activity.getAssets();
			try {
				is = assetManager.open(xmlFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
		}

		if (is != null) {

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
				is.close();
			} catch (IOException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//Log.e("Error: ", e.getMessage());
				return null;
			}
		}

		return writer.toString();

	}

	public void writeXMLToFile(Context context, String xmlFile, String xmlData) {

		FileOutputStream fOut = null;

		OutputStreamWriter osw = null;

		try {

			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File(sdCard.getAbsolutePath() + "/kjv/config");

			if (!dir.exists()) {
				if (!(dir.mkdirs()))
					JiuyiLog.e("mkdirs", "Failed to create SDCARD mounted directory!!!");
			}

			fOut = new FileOutputStream(new File(dir, xmlFile));

			osw = new OutputStreamWriter(fOut);

			osw.write(xmlData);

			osw.flush();

			Toast.makeText(context, "Settings saved", Toast.LENGTH_SHORT).show();

		}

		catch (Exception e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			Toast.makeText(context, "Settings not saved", Toast.LENGTH_SHORT).show();
		}

		finally {
			try {
				osw.close();
				fOut.close();
			} catch (IOException e) {
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
		}
	}

	public Document getDomElement(String xml) {
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is);

		} catch (ParserConfigurationException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//Log.e("Error: ", e.getMessage());
			return null;
		} catch (SAXException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//Log.e("Error: ", e.getMessage());
			return null;
		} catch (IOException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//Log.e("Error: ", e.getMessage());
			return null;
		}

		return doc;
	}

	/**
	 * 根据Node获取内容
	 */
	public final String getElementValue(Node elem) {
		Node child;
		if (elem != null) {
			if (elem.hasChildNodes()) {
				for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
					if (child.getNodeType() == Node.TEXT_NODE) {
						return child.getNodeValue();
					}
				}
			}
		}
		return "";
	}

	public String getValue(Element item, String str) {
		NodeList n = item.getElementsByTagName(str);
		return this.getElementValue(n.item(0));
	}

	public void setValue(Element elem, String str) {
		Node child;
		if (elem != null) {
			if (elem.hasChildNodes()) {
				for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
					if (child.getNodeType() == Node.TEXT_NODE) {
						child.setNodeValue(str);
					}
				}
			}
		}
	}

	public String GetElementAttribute(Element item, String attribName) {
		return item.getAttribute(attribName);
	}
}