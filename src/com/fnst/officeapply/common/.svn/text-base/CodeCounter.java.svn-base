package com.fnst.officeapply.common;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class CodeCounter {
	
	static long codeLines = 0;
	static long commtLines = 0;
	static long whiteLines = 0;
	
	public static void main(String[] args) throws URISyntaxException {
		String path = CodeCounter.class.getResource("/").getFile()+"/../../";
		System.out.println(path);
		File file = new File(path);
		handle(file);
		System.out.println("代码量:" + codeLines);
		System.out.println("注释行:" + commtLines);
		System.out.println("空白行:" + whiteLines);
		
	}

	private static void handle(File file){
		
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isFile()) {
					if (f.getName().matches(".*\\.java$")) {
						parse(f);
					}
				} else if (f.isDirectory()) {
					handle(f);
				}
			}
		}
	}
	private static void parse(File f) {
		BufferedReader br = null;
		boolean iscomm = false;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = "";
			while((line = br.readLine()) != null) {
				line = line.trim();
				if (true == iscomm) {
					commtLines++;
					if(line.endsWith("*/")){
						iscomm =false;
					}
				} else if (line.equals("")) {
					whiteLines ++;
				} else if (line.startsWith("/*") && !line.endsWith("*/")) {
					commtLines ++;
					iscomm = true;	
				} else if (line.startsWith("/*") && line.endsWith("*/")) {
					commtLines ++;
				} else if (line.startsWith("//")) {
					commtLines ++;
				} else {
					codeLines ++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
