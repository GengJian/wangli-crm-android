package com.jiuyi.tools;

import java.util.Comparator;

import customer.entity.LinkmanSortModel;

public class PinyinComparator implements Comparator<LinkmanSortModel> {

	public int compare(LinkmanSortModel o1, LinkmanSortModel o2) {
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
