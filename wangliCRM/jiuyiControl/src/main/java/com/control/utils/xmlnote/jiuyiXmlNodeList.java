package com.control.utils.xmlnote;

import java.util.ArrayList;

public class jiuyiXmlNodeList
{

	public ArrayList<JiuyiXmlNode> nodes = null;

	/*
	 * public jiuyiXmlNodeList(jiuyiXmlNodeList other) { }
	 */

	public jiuyiXmlNodeList()
	{
		nodes = new ArrayList<JiuyiXmlNode>();
	}


	public void add(JiuyiXmlNode value)
	{
		if (nodes != null)
			nodes.add(value);
	}

	public void clear()
	{
		if (nodes != null)
			nodes.clear();
	}

	public JiuyiXmlNode get(int i)
	{
		if (nodes != null && i >= 0 && i < count())
		{
			return nodes.get(i);
		}
		return null;
	}

	public int count()
	{
		if (nodes != null)
			return nodes.size();
		return 0;
	}
}
