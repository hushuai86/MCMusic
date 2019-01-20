package cn.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.mapper.SingerPoMapper;
import cn.mapper.UserPoMapper;
import cn.pojo.CDPo;
import cn.service.FileService;
import cn.service.impl.FileServiceImpl;

@Controller
public class test {
	
	
	@Autowired
	private UserPoMapper mapper;
	@Autowired
	private FileServiceImpl fileService;
	
	@RequestMapping("/test1")
	public String aa(){
		System.out.println(mapper.selectByPrimaryKey(226));
		return "index";
	}
	
	
	@RequestMapping("/addCD")
	public void addCD(HttpServletRequest request, @RequestParam(value="file") MultipartFile file)
	{
	   //图片上传
		try {
			fileService.FileUp(file, "upload", request);
		} catch (FileUpUrlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileUpSizeOverException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			fileService.deletUserImg("upload/"+"c42c76d9-029b-4597-bb7d-0f6fbf5325ae.png", request);
		} catch (FileUpUrlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 public static InputStream getInputStreamByGet(String url) {
	        try {
	            HttpURLConnection conn = (HttpURLConnection) new URL(url)
	                    .openConnection();
	            conn.setReadTimeout(5000);
	            conn.setConnectTimeout(5000);
	            conn.setRequestMethod("GET");

	            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                InputStream inputStream = conn.getInputStream();
	                return inputStream;
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 public static void saveData(InputStream is, File file) {
	        try (BufferedInputStream bis = new BufferedInputStream(is);
	                BufferedOutputStream bos = new BufferedOutputStream(
	                        new FileOutputStream(file));) {
	            byte[] buffer = new byte[1024];
	            int len = -1;
	            while ((len = bis.read(buffer)) != -1) {
	                bos.write(buffer, 0, len);
	                bos.flush();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void main(String[] args) {
		 
		 String url = "http://star.kuwo.cn/star/starheads/180/10/94/745334819.jpg";
		 String[] split = url.split("\\/");
		 String fileName = split[split.length - 1];
		 File file = new File("d:/", fileName);
		 InputStream inputStream = getInputStreamByGet(url);
		 saveData(inputStream, file);
	}
	*/
}
