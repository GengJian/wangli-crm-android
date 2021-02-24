package com.control.utils.xmlnote;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * 
 * Xml瑙ｆ��
 * 
 */
public class JiuyiXmlNode {
	private DocumentBuilderFactory docBuilderFactory_ = null;
	private DocumentBuilder docBuilder_ = null;
	private Document document_ = null;
	public Element root_ = null;

	public JiuyiXmlNode() {
	}

	public JiuyiXmlNode(Element el) {
		root_ = el;
	}

	public String getTagName() {
		return root_.getTagName();
	}

	public String getText() {
		return getText(root_);
	}

	/**
	 * ��峰�������瑰��瀹�
	 * 
	 * @param node
	 * @return
	 */
	public static String getText(Node node) {
		NodeList nodeList = node.getChildNodes();
		int nodeListLength = nodeList.getLength();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nodeListLength; i++) {
			Node childNode = nodeList.item(i);
			// ��ヤ�����Element�����圭被������杩����
			if (childNode.getNodeType() == Node.TEXT_NODE) {
				sb.append(childNode.getNodeValue());
			}
		}
		return sb.toString();
	}

	/**
	 * ��峰����硅�����CDATA���
	 * 
	 * @return
	 */
	public String getCDATA() {
		return getCDATA(root_);
	}

	/**
	 * ��峰��������CDATA���
	 * 
	 * @param node
	 * @return
	 */
	public String getCDATA(Node node) {
		NodeList nodeList = node.getChildNodes();
		int nodeListLength = nodeList.getLength();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nodeListLength; i++) {
			Node childNode = nodeList.item(i);
			// ��ヤ�����Element�����圭被������杩����
			if (childNode.getNodeType() == Node.CDATA_SECTION_NODE) {
				sb.append(childNode.getNodeValue());
			}
		}
		return sb.toString();
	}

	/**
	 * ��峰����硅����瑰��
	 * 
	 * @return
	 */
	public String getName() {
		return root_.getNodeName();
	}

	/**
	 * ���杞借����ユ��
	 * 
	 * @param input
	 *            xml杈���ユ��
	 * @return
	 */
	public boolean loadInputStream(InputStream input) {
		try {
			docBuilderFactory_ = DocumentBuilderFactory.newInstance();
			docBuilderFactory_.setIgnoringElementContentWhitespace(true);
			docBuilder_ = docBuilderFactory_.newDocumentBuilder();
			document_ = docBuilder_.parse(input);
			root_ = document_.getDocumentElement();
		} catch (SAXException e) {

			return false;
		} catch (ParserConfigurationException e) {

			return false;
		} catch (IOException e) {

			return false;
		} finally {
			if (document_ == null || root_ == null) {
				document_ = null;
				return false;
			}
			document_ = null;
			docBuilder_ = null;
			docBuilderFactory_ = null;
		}

		return true;
	}
	public boolean loadInputSource(InputSource input) {
		try {
			docBuilderFactory_ = DocumentBuilderFactory.newInstance();
			docBuilderFactory_.setIgnoringElementContentWhitespace(true);
			docBuilder_ = docBuilderFactory_.newDocumentBuilder();
			document_ = docBuilder_.parse(input);
			root_ = document_.getDocumentElement();
		} catch (SAXException e) {

			return false;
		} catch (ParserConfigurationException e) {
			return false;
		} catch (IOException e) {

			return false;
		} finally {
			if (document_ == null || root_ == null) {
				document_ = null;
				return false;
			}
			document_ = null;
			docBuilder_ = null;
			docBuilderFactory_ = null;
		}

		return true;
	}
	/**
	 * ��峰�������瑰��琛�
	 * 
	 * @param nodename
	 *            �����瑰��
	 * @return
	 */
	public NodeList getNodeList(String nodename) {
		NodeList nodeList = root_.getElementsByTagName(nodename);

		return nodeList;
	}

	/**
	 * ��峰�������瑰��琛�
	 * 
	 * @param node
	 * @param nodename
	 * @return
	 */
	public NodeList getNodeList(Node node, String nodename) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			NodeList nodeList = ((Element) node).getElementsByTagName(nodename);
			return nodeList;
		}

		return node.getChildNodes();
	}

	/**
	 * ��峰�������瑰����у��
	 * 
	 * @param node
	 *            ������
	 * @param attr
	 *            灞���у��
	 * @return
	 */
	public String getAttribute(Node node, String attr) {
		try {
			NamedNodeMap nodeMap = node.getAttributes();

			if (nodeMap == null) {
				return "";
			}

			Node n = nodeMap.getNamedItem(attr);

			if (n == null) {
				return "";
			}

			String str = n.getNodeValue();

			return str;
		} catch (Exception e) {

			return "";
		}
	}

	/**
	 * 璁剧疆�����瑰����у��
	 * 
	 * @param node
	 *            xml������
	 * @param name
	 *            �����瑰����у��
	 * @param value
	 *            �����瑰����у��
	 * @return ������璁剧疆������
	 */
	public boolean setAttribute(Node node, String name, String value) {
		try {
			NamedNodeMap nodeMap = node.getAttributes();

			if (nodeMap == null) {
				return false;
			}

			Node n = nodeMap.getNamedItem(name);

			if (n == null) {
				return false;
			}

			n.setNodeValue(value);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * 璁剧疆灞���у��
	 * 
	 * @param name
	 *            灞���у��
	 * @param value
	 *            灞���у��
	 * @return ������璁剧疆������
	 */
	public boolean setAttribute(String name, String value) {
		try {
			Element el = root_;

			if (el == null) {
				return false;
			}
			if (el.hasAttribute(name)) {
				el.setAttribute(name, value);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * ��ユ�炬��瀹������瑰��瀵瑰�����������
	 * 
	 * @param nodename
	 *            �����瑰��
	 * @return
	 */
	public Node selectSingleNodeByName(String nodename) {
		NodeList nodeList = root_.getElementsByTagName(nodename);

		if (nodeList.getLength() < 1) {
			// Log.i("JiuyiXmlNode:SelectSingleNode failed");

			return null;
		}
		return nodeList.item(0);
	}

	/**
	 * ��规�������瑰��������涓�������
	 * 
	 * @param node
	 * @param nodename
	 * @return
	 */
	public static Node selectSingleNodeByName(Node node, String nodename) {
		NodeList nodes = node.getChildNodes();
		if (nodes.getLength() <= 0) {
			return null;
		}

		for (int i = 0; i < nodes.getLength(); i++) {
			Node tnode = nodes.item(i);
			if (nodename.equals(tnode.getNodeName())) {
				return tnode;
			}
		}
		return null;
	}

	/**
	 * ��规�������瑰��寰����涓������规�����
	 * 
	 * @param node
	 * @param nodename
	 * @return
	 */
	public static String SelectSingleNodeText(Node node, String nodename) {
		Node result = selectSingleNodeByName(node, nodename);
		if (result == null) {
			return "";
		}
		String text = getText(result);
		return null == text ? "" : text;
	}

	/**
	 * ��ユ�捐����瑰��瀵瑰�����琛�
	 * 
	 * @param nodename
	 * @return
	 */
	public JiuyiXmlNode selectSingleNode(String nodename) {
		NodeList nodeList = root_.getElementsByTagName(nodename);

		if (nodeList.getLength() < 1) {
			// Log.i("JiuyiXmlNode:SelectSingleNode failed");

			return null;
		}

		JiuyiXmlNode xNode = new JiuyiXmlNode();
		xNode.root_ = (Element) nodeList.item(0);

		return xNode;
	}

	/**
	 * ��ユ�捐����瑰��瀹癸�����selectSingleNodeText2(String)
	 * 
	 * @param nodename
	 *            �����瑰��
	 * @return �����瑰��瀹�
	 */
	public String selectSingleNodeText(String nodename) {
		JiuyiXmlNode node = selectSingleNode(nodename);
		if (null == node) {
			return "";
		}
		return node.getText();
	}

	/**
	 * ���杞�xml
	 * 
	 * @param xml
	 * @return
	 */
	public boolean loadXml(String xml) {

		if ((xml == null) || xml.length() <= 0) {
			return false;
		}

		InputStream inputstream = new ByteArrayInputStream(xml.getBytes());

		return loadInputStream(inputstream);
	}

	/**
	 * ��峰����硅����瑰��琛�
	 * 
	 * @return
	 */
	public NodeList getChildNodeList() {
		return root_.getChildNodes();
	}

	/**
	 * ��峰��瀵瑰�������圭�������瑰��琛�
	 * 
	 * @param node
	 * @return
	 */
	public NodeList getChildNodeList(Node node) {
		return node.getChildNodes();
	}

	/**
	 * ��峰����硅����瑰����у��
	 * 
	 * @param name
	 *            灞���у��
	 * @return
	 */
	public String getAttribute(String name) {
		String attributeName = root_.getAttribute(name);
		if (attributeName == null)
			attributeName = "";
		return attributeName;
	}

	/**
	 * ��ユ�捐����瑰��瀹癸�����selectSingleNodeText(String)
	 * 
	 * @param name
	 *            �����瑰��
	 * @return �����瑰��瀹�
	 */
	public String selectSingleNodeText2(String name) {
		Node node = selectSingleNodeByName(name);
		if (node == null)
			return "";
		return getText(node);
	}

	/**
	 * ��峰�������瑰�瑰��������灞���� key-value瀵�
	 * 
	 * @param node
	 * @return key-value瀵�
	 */
	public Hashtable<String, String> getAllAttributes(Node node) {
		Hashtable<String, String> hs = new Hashtable<String, String>(0);
		try {
			NamedNodeMap nodeMap = node.getAttributes();

			if (nodeMap == null) {
				return hs;
			}
			int cn = nodeMap.getLength();
			for (int i = 0; i < cn; i++) {

				Node n = nodeMap.item(i);

				if (n == null) {
					return hs;
				}
				String key = n.getNodeName();
				String value = n.getNodeValue();
				hs.put(key, value);
			}

        } catch (Exception e) {

			return hs;
		}
		return hs;
	}

	/**
	 * ��峰��瀛������规�����
	 * 
	 * @return
	 */
	public jiuyiXmlNodeList selectChildNodes() {
		Element root = root_;
		jiuyiXmlNodeList list = new jiuyiXmlNodeList();

		if (root != null) {
			NodeList nodeList = root.getChildNodes();
			int len = nodeList.getLength();
			for (int i = 0; i < len; i++) {
				Node node = nodeList.item(i);
				switch (node.getNodeType()) {
				case Node.ELEMENT_NODE:
					if (!node.getNodeName().equalsIgnoreCase("")) {
						try {
							list.add(new JiuyiXmlNode((Element) node));
						} catch (Exception e) {

						}
					}
					break;

				default:
					break;
				}
			}
		}
		return list;
	}

	public jiuyiXmlNodeList selectChildNodes(String eleName) {
		Element root = root_;
		jiuyiXmlNodeList list = new jiuyiXmlNodeList();
		if (root != null) {
			NodeList nodeList = root.getChildNodes();
			int len = nodeList.getLength();
			for (int i = 0; i < len; i++) {
				Node child = nodeList.item(i);
				if (child == null)
					continue;
				if (child.getNodeName().equalsIgnoreCase(eleName)) {
					try {
						list.add(new JiuyiXmlNode(((Element) child)));
					} catch (Exception e) {

					}
				}
			}
		}
		return list;
	}
}
