package com.cndym.util.export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;

/**
 * @author ��»Ԫ
 * @date 2016-4-8 ����Text
 */

public class MakeTxt {

	private static Logger logger = Logger.getLogger(MakeTxt.class);

	public static void getFileExists(String fPath) {
		File file = new File(fPath);
		// ����ļ��в������򴴽�
		if (!file.exists() && !file.isDirectory()) {
			logger.debug("�ļ�Ŀ¼�����ڣ�������" + fPath);
			file.mkdir();
		} else {
			logger.debug(fPath + "   --->�ļ�Ŀ¼����");
		}
	}

	public static void contentToTxt(String filePath, String content, boolean b) {
		String str = new String(); // ԭ��txt����
		String s1 = new String();// ���ݸ���

		try {
			File f = new File(filePath);
			if (f.exists()) {
				logger.debug(filePath + "   --->�ļ�Ŀ¼����");
			} else {
				f.createNewFile();// �������򴴽�
				logger.debug("�ļ�Ŀ¼�����ڣ�������" + filePath);
			}
			if (false == b) {
				BufferedReader input = new BufferedReader(new FileReader(f));
				while ((str = input.readLine()) != null) {
					s1 += str + "\n";
				}
				input.close();
			}
			s1 += content;

			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("����TXT����csv�ļ���MakeTxt 56��",e);
			
		}
	}

}
