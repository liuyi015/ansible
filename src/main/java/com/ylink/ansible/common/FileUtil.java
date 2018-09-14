package com.ylink.ansible.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ylink.ansible.playbook.pojo.Parameter;

public class FileUtil {

	/**
	 * 替换文件中的字符生成新文件
	 * @param srcFile
	 * @param newFile
	 * @throws IOException
	 */
	public static Boolean replaceFileStr(String srcFile, String newFile,List<Parameter> parameter) throws  IOException{
		long starttime=System.currentTimeMillis();
		
		File file1=new File(srcFile);//读取文件
		if(!file1.isFile()) {
			System.out.println(srcFile+"不是文件");
			return false;
		}
		FileReader fr =new FileReader(file1);
		
		File file2=new File(newFile);//新生文件
		FileWriter fw =new FileWriter(file2);
 
		BufferedReader br=new BufferedReader(fr);
		BufferedWriter bw=new BufferedWriter(fw);
 
		try {
			// 创建字符串构建器
			StringBuilder builder = new StringBuilder();
			//创建缓存字符串数组
			char[] data=new char[1024];
			String temp=null;
			//读取数据
			while((temp=br.readLine())!=null){ 
				System.out.println(temp);
				builder.append(temp);
				builder.append("\r\n");
			}
			br.close();
			// 从构建器中生成字符串，并替换搜索文本
			String str = builder.toString();
			if(parameter==null) {
				return false;
			}
			int size = parameter.size();
			for(int i=0;i<size;i++) {
				/*System.out.println(parameter.get(i).getParameter_name());
				System.out.println(parameter.get(i).getParameter_value());*/
				str=str.replace(parameter.get(i).getParameter_name(), parameter.get(i).getParameter_value());
			}
			bw.write(str.toCharArray());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			br.close();
			bw.close();
			e.printStackTrace();
			return false;
		}
		long endtime=System.currentTimeMillis();
		System.out.println("总耗时："+(endtime-starttime)+"ms");
		return true;
	}

	/**
	 * 读取变量文件（键对值格式：name:parameter_name）
	 * @param srcFile
	 * @return
	 * @throws IOException
	 */
	public static List<Parameter> readFile(String srcFile) throws  IOException{
		long starttime=System.currentTimeMillis();
		
		File file1=new File(srcFile);//读取文件
		if(!file1.isFile()) {
			System.out.println(srcFile+"不是文件");
			return null;
		}
		FileReader fr =new FileReader(file1);
		BufferedReader br=new BufferedReader(fr);
		List<Parameter> list=new ArrayList<>();
		try {
			String temp=null;
			//读取数据
			while((temp=br.readLine())!=null){ 
				System.out.println(temp);
				String[] split = temp.split(":");
				Parameter parameter = new Parameter();
				parameter.setName(split[0]);
				parameter.setParameter_name(split[1].trim());
				list.add(parameter);
			}
			br.close();
		} catch (IOException e) {
			br.close();
			e.printStackTrace();
		}
		long endtime=System.currentTimeMillis();
		System.out.println("总耗时："+(endtime-starttime)+"ms");
		return list;
	}
	
}
