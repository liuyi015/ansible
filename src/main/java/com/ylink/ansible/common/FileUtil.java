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
		
		if(parameter==null|| parameter.size()<=0) {
			return false;
		}
		int size = parameter.size();   //参数个数
		int i=0;  //参数指针
		
		File file1=new File(srcFile); //读取文件
		if(!file1.isFile()) {
			System.out.println(srcFile+"不是文件");
			return false;
		}
		File file2=new File(newFile);//新生文件
		
		FileReader fr =new FileReader(file1);
		BufferedReader br=new BufferedReader(fr);
		
		FileWriter fw =null;
		BufferedWriter bw=null;
 
		try {
			// 创建字符串构建器
			StringBuilder builder = new StringBuilder();
			//创建缓存字符串数组
			String temp=null;
			//读取数据
			while((temp=br.readLine())!=null){ 
				temp=temp.replace(parameter.get(i).getParameter_name(), parameter.get(i).getParameter_value());
				System.out.println(temp);
				builder.append(temp);
				builder.append("\r\n");
				if(i<size) {
					i++;
				}
			}
			br.close();
			// 从构建器中生成字符串，并替换搜索文本
			String str = builder.toString();
			
			/*//会替换不该替换的字符串中的一部分
			 * for(int i=0;i<size;i++) {
				System.out.print(parameter.get(i).getParameter_name()+":");
				System.out.println(parameter.get(i).getParameter_value());
				str=str.replace(parameter.get(i).getParameter_name(), parameter.get(i).getParameter_value());
			}*/
			
			//写入新文件
			fw =new FileWriter(file2);
			bw=new BufferedWriter(fw);
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
				String paramValue = split[1].trim();
				//判断是否是“vars”格式，是---》去除“”
				if(paramValue.contains("\"")) {
					String substring = paramValue.substring(1, paramValue.length()-1);
					parameter.setParameter_name(substring);
				}else {
					parameter.setParameter_name(split[1].trim());
				}
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
