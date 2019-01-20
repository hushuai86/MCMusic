package cn.service.admin;

import java.util.List;

import cn.exception.AdminException;
import cn.pojo.ImagePo;
import cn.pojo.PageBasePo;

/**
 * 图片轮播服务接口
 * 
 * @author taz
 *
 */
public interface AdminImageService {
	/**
	 * 添加图片
	 * @param imagePo
	 * @return
	 * @throws AdminException
	 */
	public boolean addImage(ImagePo imagePo) throws AdminException;
	
	/**
	 * 删除图片
	 * @param imageId
	 * @return
	 * @throws AdminException
	 */
	public boolean deleteImage(int imageId) throws AdminException;

	
	/**
	 * 批量删除
	 * @param list
	 * @return
	 * @throws AdminException
	 */
	public boolean deleteImageBatch(List<Integer> list)throws AdminException;
	
	
	/**
	 * 修改图片
	 * @param imagePo
	 * @return
	 * @throws AdminException
	 */
	public boolean UpdateImage(ImagePo imagePo)throws AdminException;
	/**
	 * 查询
	 * @param imageId
	 * @return
	 * @throws AdminException
	 */
	public ImagePo selectImageById(int imageId) throws AdminException;
	
	/**
	 * 
	 * @param imageName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws AdminException
	 */
	public PageBasePo<ImagePo> selectImagePosByName(String imagename,int pageIndex,int pageSize) throws AdminException;
	/**
	 * 查询图片进行分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws AdminException
	 */
	public PageBasePo<ImagePo> filter(int pageIndex,int pageSize)throws AdminException;
	
}
