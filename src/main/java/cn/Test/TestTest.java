package cn.Test;

import org.springframework.beans.factory.annotation.Autowired;

import cn.pojo.ImagePo;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.vo.SongListVo;
import cn.service.impl.admin.AdminImageServiceImpl;
import cn.service.impl.admin.AdminSongListServiceImpl;
import cn.service.impl.admin.AdminSongServiceImpl;


public class TestTest extends MyTest{
	@Autowired 
	private AdminSongServiceImpl adminSongServiceImpl;
	@org.junit.Test
	//@Rollback(false)   //不希望回滚使用该注解
	public void Test(){

	}
	}
