package cn.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.pojo.SingerPo;
import cn.service.impl.AreaServiceImpl;
import cn.service.impl.RankListServiceImpl;
import cn.service.impl.SingerServiceImpl;

@Controller
public class SingerRankController {
	@Autowired
	private SingerServiceImpl singerServiceImpl;
	@Autowired
	private RankListServiceImpl rankListServiceImpl;
	@Autowired
	private AreaServiceImpl areaServiceImpl;
	// 全球榜  默认20个
	@RequestMapping("/getListOfWorld")
	@ResponseBody
	public List<SingerPo> getListOfWorld() {		
		return rankListServiceImpl.getHotSingerRank(20,1).getList();
	}

	// 内陆榜 默认20个
	@RequestMapping("/getListOfNeri")
	@ResponseBody
	public List<SingerPo> getListOfNeri() {		
		return rankListServiceImpl.getHotSingerRank(17, 20, 1).getList();
	}

	// 国外榜 默认20个
	@RequestMapping("/getListOfForeign")
	@ResponseBody
	public List<SingerPo> getListOfForeign() {
		  return singerServiceImpl.getSingerPoForForeign(20, 1);
	}

	// 收藏榜 默认20个
	@RequestMapping("/getListOfCollect")
	@ResponseBody
	public List<SingerPo> getListOfCollect() {
		return rankListServiceImpl.getCollectionSingerRank(20,1).getList();
	}
}
