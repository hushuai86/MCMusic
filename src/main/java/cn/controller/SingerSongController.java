package cn.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.exception.SingerIdNotExistException;
import cn.pojo.SongPo;
import cn.service.impl.SingerServiceImpl;
import cn.service.impl.SongServiceImpl;

@Controller
public class SingerSongController {
	@Autowired
	private SongServiceImpl songServiceImpl;
	@Autowired
	private SingerServiceImpl singerServiceImpl;
	// 单曲 分页
	@RequestMapping("/getSongBySingerId")
	@ResponseBody
	public List<SongPo> getSongBySingerId(int singerId, int pageIndex)
			throws SingerIdNotExistException {	 
		return songServiceImpl.getSongPoBySingerId(singerId,10,pageIndex);
	}
	//歌手单曲数量
	@RequestMapping("/getSongBySingerIdConut")
	@ResponseBody
	public int getSongBySingerIdConut(int singerId) throws SingerIdNotExistException{	 
		return songServiceImpl.getSongCountBySingerId(singerId);
	}
	
	
}
