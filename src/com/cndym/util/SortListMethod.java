package com.cndym.util;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

public class SortListMethod {
	public static String getAlphabet(String str) {
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		// ���ƴ��ȫ��Сд
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		// ��������
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String pinyin = null;
		try {
			pinyin = (String) PinyinHelper.toHanyuPinyinStringArray(str.charAt(0), defaultFormat)[0];
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return pinyin.substring(0, 1);
	}

	public static ComparatorChain sortList(String str, String id) {

		ArrayList<Object> sortFields = new ArrayList<Object>();
		
		Comparator<Object> comparator = Collator.getInstance(java.util.Locale.CHINA);
		sortFields.add(new BeanComparator(str, comparator)); // ���� (��)

		Comparator mycmp = ComparableComparator.getInstance();
		mycmp = ComparatorUtils.nullLowComparator(mycmp); // ����null
		mycmp = ComparatorUtils.reversedComparator(mycmp); // ����
		sortFields.add(new BeanComparator(id, mycmp)); // id���� (��)

		ComparatorChain multiSort = new ComparatorChain(sortFields);

		return multiSort;

	}
}
