package com.eccl.supertrading.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

public class OperationFileUtil {  
	//重命名文件
		private static String rname(String name){
			String fileName = UUIDGenerator.getUUID(); 
			if (name.indexOf(".") != -1) {  
	            fileName += name.substring(name.lastIndexOf("."));  
	        } 
			return fileName;
		}

//		单个文件上传
		public static  String uploadOneFile(HttpServletRequest request){
			MultipartRequest mrequest=(MultipartRequest)request;
			 Iterator iter = mrequest.getFileNames();
			 while(iter.hasNext()){
//				 得到文件
				 MultipartFile file=mrequest.getFile((String) iter.next());
				 if(file!=null){
//					 得到文件的真实名称
					 String originalFileName =file.getOriginalFilename();
					 String realpath  = request.getSession().getServletContext().getRealPath("/");
//					 重命名文件
					 String filename=rname(originalFileName);
					 String filepath=realpath+"upload/"+filename;
					 File localFile=new File(filepath);
//					 路径如果不存在，就创建
					 if (!localFile.exists())  
						 localFile.mkdirs();  
					 System.out.println("服务器路径："+realpath);
					 System.out.println("文件名："+filename);
					 System.out.println("文件存放物理路径："+filepath);
					 try {
						file.transferTo(localFile);
						String storepath=PathConfiger.filepath+filename;
						return storepath;
					} catch (Exception e) {
						// TODO: handle exception
					}
				 }
			 }
			return null;
		}
//		多个文件上传
		public static String uploadManyFile(MultipartFile[] files,HttpServletRequest request){
			if(files!=null&&files.length>0){
					for (MultipartFile file : files) {
						if(file!=null){
//							 得到文件的真实名称
							 String originalFileName =file.getOriginalFilename();
							 String realpath  = request.getSession().getServletContext().getRealPath("/");
//							 重命名文件
							 String filename=rname(originalFileName);
							 String filepath=realpath+"upload/"+filename;
							 File localFile=new File(filepath);
//							 路径如果不存在，就创建
							 if (!localFile.exists())  
								 localFile.mkdirs();  
							 System.out.println("服务器路径："+realpath);
							 System.out.println("文件名："+filename);
							 System.out.println("文件存放物理路径："+filepath);
							 try {
								file.transferTo(localFile);
								String storepath=PathConfiger.filepath+filename;
								return storepath;
							} catch (Exception e) {
								// TODO: handle exception
							}
					}
				}
			}
			return null;
		}
}  