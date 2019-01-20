package cn.service.impl.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.service.FileService;
import cn.service.admin.AdminFileService;
@Service
public class AdminFileServiceImpl implements AdminFileService{

	@Override
	public String FileUp(MultipartFile file, String url,HttpServletRequest request)
			throws FileUpUrlException, FileUpSizeOverException {	
		String path = request.getSession().getServletContext().getRealPath(url);  
        String fileName =UUID.randomUUID().toString()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        fileName = url+"/"+fileName;
		return fileName;
	}

	
	@Override
	public long FileUp(MultipartFile[] files, String url,HttpServletRequest request)
			throws FileUpUrlException, FileUpSizeOverException {
		long startTime = System.currentTimeMillis();
		
		String path = request.getSession().getServletContext().getRealPath(url);  
		for(MultipartFile file :files ){
			//文件名称 = uuid+后缀
			String fileName = UUID.randomUUID().toString()+"."+
					file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);;  
	        
	        //System.out.println(path);  
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        
	        try {
				file.transferTo(targetFile);
			}catch (Exception e) {
				e.printStackTrace();
			}  
		}
        
        long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}

	@Override
	public ResponseEntity<byte[]> FileDown(List<String> list,HttpServletRequest request) throws IOException {
		File file ;//最后下载路径
		String downloadFielName;
		
		if(list.size()>1){
			String uuid = UUID.randomUUID().toString();
			String tmpFileName = uuid+".zip"; //压缩文件临时名称  
			//压缩文件存储路径
			String strZipPath=request.getSession().getServletContext().getRealPath("/")+"/mc/"+tmpFileName; 
			//创建压缩文件
	        File tmpZipFile = new File(strZipPath);  
	        if (!tmpZipFile.exists())  
	            tmpZipFile.createNewFile();  
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
	        //文件组创建
	        File[] file1 =new File[list.size()] ;  
	        for(int i=0;i<list.size();i++){  
	        	String fileUrl = request.getSession().getServletContext().getRealPath("/")+list.get(i);
	        	//System.out.println(fileUrl);
	            file1[i]=new File(fileUrl);  
	        }  
	        
	        //存储容器
	        byte[] buffer = new byte[1024]; 
	        for (int i = 0; i < file1.length; i++) {    
	            FileInputStream fis = new FileInputStream(file1[i]);    
	            out.putNextEntry(new ZipEntry(UUID.randomUUID().toString()+file1[i].getName()));    
	            //设置压缩文件内的字符编码，不然会变成乱码    
	            //out.setEncoding("UTF-8");    
	            int len;    
	            // 读入需要下载的文件的内容，打包到zip文件    
	            while ((len = fis.read(buffer)) > 0) {    
	                out.write(buffer, 0, len);    
	            }    
	            out.closeEntry();    
	            fis.close();    
	        }    
	        out.close();  
	        file = new File(request.getSession().getServletContext().getRealPath("/")+"/mc/"+tmpFileName);
	        //下载显示的文件名，解决中文名称乱码问题  
			downloadFielName = new String(tmpFileName.getBytes("UTF-8"),"iso-8859-1");
		}
		else{
			file = new File(request.getSession().getServletContext().getRealPath("/")+list.get(0));
			//下载显示的文件名，解决中文名称乱码问题  
			downloadFielName = new String(list.get(0).getBytes("UTF-8"),"iso-8859-1");
		}
		
		HttpHeaders headers = new HttpHeaders();  
		//通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFielName); 
		//application/octet-stream ： 二进制流数据（最常见的文件下载）。
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
		
	}


	@Override
	public boolean deletUserImg(String url, HttpServletRequest request) throws FileUpUrlException{
		String path = request.getSession().getServletContext().getRealPath("");
		File file = new File(path,url);
        if (file.exists()) {
            file.delete();
            return true;
        }else{
        	throw new FileUpUrlException("路径错误");
        }
		
	}

}
