package cn.service.impl.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.AdminException;
import cn.mapper.ImagePoMapper;
import cn.pojo.ImagePo;
import cn.pojo.PageBasePo;
import cn.service.admin.AdminImageService;

@Service
public class AdminImageServiceImpl  implements AdminImageService{

	
	@Autowired
	ImagePoMapper imagePoMapper;
	
	@Override
	public boolean addImage(ImagePo imagePo) throws AdminException {
		
		int result=imagePoMapper.insertSelective(imagePo);
		
		if(result<=0){
			throw new AdminException("图片添加失败");
		}
		return true;
	}

	@Override
	public boolean deleteImage(int imageId) throws AdminException {
		int result=imagePoMapper.deleteByPrimaryKey(imageId);
		
		if(result<=0){
			throw new AdminException("图片删除失败");
		}
		return true;
	}

	@Override
	public boolean deleteImageBatch(List<Integer> list) throws AdminException {
		
		for (Integer integer : list) {
			int result=imagePoMapper.deleteByPrimaryKey(integer);
			if(result<=0){
				throw new AdminException("图片删除失败");
			}	
		}
		return true;
	}

	@Override
	public boolean UpdateImage(ImagePo imagePo) throws AdminException {
		int result=imagePoMapper.updateByPrimaryKeySelective(imagePo);
		if(result<=0){
			throw new AdminException("图片修改失败");
		}
		return true;
	}

	@Override
	public ImagePo selectImageById(int imageId) throws AdminException {
		// TODO Auto-generated method stub
		ImagePo imagePo=imagePoMapper.selectByPrimaryKey(imageId);
		if(imagePo==null){
			throw new AdminException("图片查询失败");
		}
		return imagePo;
	}
	
	@Override
	public PageBasePo<ImagePo> selectImagePosByName(String imagename,int pageIndex,int pageSize) throws AdminException {
		
		
		
		PageBasePo<ImagePo> pageBasePo=new PageBasePo<ImagePo>();
		
		List<ImagePo> list=new ArrayList<ImagePo>();
		
		Map<String,Object> map=new HashMap<String, Object>();
	
		
		if(imagename!=null){
			imagename="%"+imagename+"%";
		}
		map.put("imagename", imagename);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		map.put("pageSize", pageSize);
		list=imagePoMapper.getImagePosByName(map);
		
		int allNum=imagePoMapper.getImagePosByNameCount(map);
		//给分页对象赋值
		pageBasePo.setList(list);
		
		pageBasePo.setPageIndex(pageIndex);
		
		pageBasePo.setPageSize(pageSize);
			
		pageBasePo.setAllNum(allNum);
		
		pageBasePo.setPageCount((int)Math.ceil((double)allNum/pageSize));
		
		return pageBasePo;
	}

	@Override
	public PageBasePo<ImagePo> filter(int pageIndex, int pageSize) throws AdminException {

		PageBasePo<ImagePo> pageBasePo=new PageBasePo<ImagePo>();
		List<ImagePo> list=new ArrayList<ImagePo>();
		
		Map<String,Object> map=new HashMap<String, Object>();
		
		
		
		map.put("pageIndex", (pageIndex-1)*pageSize);
		map.put("pageSize", pageSize);
		
		list=imagePoMapper.getImagePos(map);
		
		//给分页对象赋值
		pageBasePo.setList(list);
		
		pageBasePo.setPageIndex(pageIndex);
		
		pageBasePo.setPageSize(pageSize);
		
		int allNum=imagePoMapper.getImagePosCount(map);
		
		pageBasePo.setAllNum(allNum);
		
		pageBasePo.setPageCount((int)Math.ceil((double)allNum/pageSize));
	
		return pageBasePo;
	}



}
