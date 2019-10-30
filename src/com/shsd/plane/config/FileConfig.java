package com.shsd.plane.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileConfig {


	// 创建文件
	public static boolean createFile(String path) {
		// TODO Auto-generated method stub
		boolean re = false;
		File f = new File(path);
		if (!(f.exists())) {
			try {
				re = f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;

	}

	// 创建文件夹
	public static boolean createDirectory(String path) {
		// TODO Auto-generated method stub
		boolean re = false;
		File f = new File(path);
		if (!(f.exists())) {
			re = f.mkdir();
		}
		return re;

	}

	// 删除文件或文件夹
	public boolean deletFiles(String path) {
		// TODO Auto-generated method stub
		boolean re = false;
		File f = new File(path);
		if (!f.exists()) {
			return true;
		}
		if (f.isDirectory()) {
			File[] s = f.listFiles();
			for (File file : s) {
				if (file.isDirectory()) {
					deletFiles(path + "/" + file.getName());
				}
			}
			re = f.delete();
		} else if (f.isFile()) {
			re = f.delete();
		}
		return re;
	}

	// 以字节形式写入文件
	public static void writeByByte(String path, String content) {
		// TODO Auto-generated method stub
		File f = new File(path);
		synchronized (f) {

			try {
				FileWriter fileWriter = new FileWriter(f, true);
				fileWriter.append(content + "\n");
				fileWriter.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 以字符流的形式行读取文件数据
	public static List<String> readByReader(String path) throws Exception {
		// TODO Auto-generated method stub
		List<String> data = new ArrayList<String>();
		FileInputStream fop = new FileInputStream(new File(path));
		InputStreamReader isr = new InputStreamReader(fop, "GBK");
		BufferedReader br = new BufferedReader(isr);
		String str = "";
		while ((str = br.readLine()) != null) {
			data.add(str);
		}
		br.close();
		isr.close();
		fop.close();
		return data;
	}
}
