package cn.service.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;

/**
 * 文件上传统一服务管理接口
 * @author liuqiao
 *
 */
public interface AdminFileService {

	/**
	 * 删除用户头像
	 * @param url 图片名称+后缀
	 * @param request
	 * @return
	 * @throws FileUpUrlException
	 */
	public boolean deletUserImg(String url,HttpServletRequest request)throws FileUpUrlException;
	/**
	 * 单个文件上传
	 * @param file 上传的文件
	 * @param url 存储的地址块 ，例如“/upload”
	 * @throws FileUploadException 文件路径异常
	 * @throws FileUpSizeOverException 文件大小超过限制异常
	 * @return 返回该次文件上传消耗时间
	 */
	public String FileUp(MultipartFile file,String url,HttpServletRequest request)throws FileUpUrlException,FileUpSizeOverException;
	
	/**
	 * 多个文件上传
	 * @param file 上传的文件集合
	 * @param url 存储的地址块 ，例如“/upload”
	 * @throws FileUploadException 文件路径异常
	 * @throws FileUpSizeOverException 文件大小超过限制异常
	 * @return 返回该次文件上传消耗时间 
	 */
	public long FileUp(MultipartFile[] file,String url,HttpServletRequest request)throws FileUpUrlException,FileUpSizeOverException;
	
	/**
	 * 文件下载
	 * @param url 下载路径
	 * @return  响应实体对象
	 * 例如：
	 * 下载文件路径
	 * String path = request.getServletContext().getRealPath("/images/");
	 * File file = new File(path + File.separator + filename);
	 * HttpHeaders headers = new HttpHeaders();  
	 * 下载显示的文件名，解决中文名称乱码问题  
	 * String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
	 * 通知浏览器以attachment（下载方式）打开图片
	 * headers.setContentDispositionFormData("attachment", downloadFielName); 
	 * application/octet-stream ： 二进制流数据（最常见的文件下载）。
	 * headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	 * return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);  
	 */
	public ResponseEntity<byte[]> FileDown(List<String> url,HttpServletRequest request)throws IOException;
}
