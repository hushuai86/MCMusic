package cn.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.AdminException;
import cn.exception.SongIdNotExistException;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;
import cn.service.impl.CDServiceImpl;
import cn.service.impl.admin.AdminCDServiceImpl;
import cn.service.impl.admin.AdminSingerServiceImpl;
import cn.service.impl.admin.AdminSongServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminCDSongController {
	@Autowired
	 private AdminCDServiceImpl adminCDServiceImpl;
	@Autowired
	private AdminSongServiceImpl adminSongServiceImpl;
	@Autowired
	private AdminSingerServiceImpl adminSingerServiceImpl;

	//专辑歌曲管理页面
    @RequestMapping("toAdminUpdateCDSong")
    public String toAdminUpdateCDSong(int cdId,HttpServletRequest request,Integer pageIndex,Model model) throws AdminException{
    cdId =Integer.parseInt(request.getParameter("cdId"));
	String search = request.getParameter("search");
	PageBasePo<SongVo> pageBasePo = adminSongServiceImpl.filterByCDIdAndSongName(cdId,search,pageIndex,5);
	CDPo cdPo=adminCDServiceImpl.getShowCDPoById(cdId);
	model.addAttribute("pageBasePo",pageBasePo);
	model.addAttribute("search",search);
	model.addAttribute("cdPo", cdPo);
	return "admin/CDSong";
	
}
//专辑歌曲分页ajax
	@RequestMapping("/adminPageCDSong")
	@ResponseBody
	public PageBasePo<SongVo> getSingerPo1(Integer pageIndex,String search,int cdId){
		PageBasePo<SongVo> pageBasePo = adminSongServiceImpl.filterByCDIdAndSongName(cdId,search,pageIndex,5);
		return pageBasePo;
	}
	
//专辑删除歌曲
	@RequestMapping("/deleteCDSong")
	public String deleteCDSong(Integer cdId,int songId) throws AdminException, SongIdNotExistException{
	  adminSongServiceImpl.CDDeleteSong(cdId, songId);
	  return "forward:toAdminUpdateCDSong";
	}
//跳转专辑添加歌曲页面
	@RequestMapping("/toAdminCDAddSong")
    public String toAdminCDAddSong( Integer cdId,Model model) throws AdminException{
		CDPo cdpo = adminCDServiceImpl.getShowCDPoById(cdId);
		Integer singerId = cdpo.getSingerid();
    	PageBasePo<SongVo> pageBasePo = adminSongServiceImpl.filterNotInCDBySingId(singerId, 1, 5);
    	model.addAttribute("pageBasePo", pageBasePo);
    	model.addAttribute("cdPo", cdpo);
    	return "admin/CDAddSong";
    	
    }
	//专辑添加歌曲
	@RequestMapping("/adminCDAddSong")
    public String adminCDAddSong( Integer cdId,int songId) throws AdminException{
	    adminSongServiceImpl.CDAddSong(cdId, songId);
    	return "forward:toAdminCDAddSong";
    	
    }

	
}
