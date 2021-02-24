package com.jiuyi.tools;

import java.util.Comparator;

import commonlyused.bean.LinkmanBean;

public class NormalPinyinComparator implements Comparator<LinkmanBean> {

	public int compare(LinkmanBean o1, LinkmanBean o2) {
		if (o1.getLetters().equals("@")
				|| o2.getLetters().equals("#")) {
			return -1;
		} else if (o1.getLetters().equals("#")
				|| o2.getLetters().equals("@")) {
			return 1;
		} else {
			return o1.getLetters().compareTo(o2.getLetters());
		}
	}

}
