package cn.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.exception.AdminException;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.exception.SingerIdNotExistException;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;
import cn.service.impl.admin.AdminCDServiceImpl;
import cn.service.impl.admin.AdminFileServiceImpl;
import cn.service.impl.admin.AdminSingerServiceImpl;
import cn.service.impl.admin.AdminSongServiceImpl;
@Controller
@RequestMapping("/admin")
public class AdminSongController {
	 @Autowired
	 private AdminSongServiceImpl adminSongServiceImpl;
	 @Autowired
	 private AdminSingerServiceImpl adminSingerServiceImpl;
	 @Autowired
	 private AdminFileServiceImpl adminFileServiceImpl;
	 @Autowired
	 private AdminCDServiceImpl adminCDServiceImpl;
	// 歌手列表 根据歌手名 模糊查询
	@RequestMapping("/getSongPo")
	public String getSingerPo(HttpServletRequest request,Model model,Integer pageIndex) {
		if(pageIndex == null ){
			pageIndex = 1;
		}
		SongVo songVo = new SongVo();
		SongPo songPo = new SongPo();
	    String songName = request.getParameter("songName");
		if(songName!=null){
			songPo.setSongname(songName);			
		}
		songPo.setPlaycount(1);
		songVo.setSongPo(songPo); 
		PageBasePo<SongVo> pageBasePo =  adminSongServiceImpl.filter(songVo, 5, pageIndex);
		model.addAttribute("pageBasePo",pageBasePo);
		model.addAttribute("songName", songName);
		return "admin/song";
	}
	
	@RequestMapping("/getSongPoByPag")
	@ResponseBody
	public PageBasePo<SongVo> getSingerPo1(HttpServletRequest request,Model model,Integer pageIndex) {
		if(pageIndex == null ){
			pageIndex = 1;
		}
		SongVo songVo = new SongVo();
		SongPo songPo = new SongPo();
	    String songName = request.getParameter("songName");
		if(songName!=null){
			songPo.setSongname(songName);			
		}
		songVo.setSongPo(songPo); 
		PageBasePo<SongVo> pageBasePo =  adminSongServiceImpl.filter(songVo, 5, pageIndex);
		return pageBasePo;
	}
	
	//删除
	@RequestMapping("/deleteSongPo")
	public String deleteSongPo(int songId){		 
		try {
			
			adminSongServiceImpl.deleteSong(songId);
			
			return  "redirect:getSongPo";
		} catch (AdminException e) {
			return "redirect:getSongPo";
		}
	}
	
    //添加歌曲
	@RequestMapping("/addSong")
	public String addSong(SongPo songPo,HttpServletRequest request,Model model) throws SingerIdNotExistException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date	date = sdf.parse(request.getParameter("date"));			 
			//日期封装
			songPo.setPublishdate(date);
			//url封装
			songPo.setCyricurl(request.getParameter("url1"));
			songPo.setSongurl(request.getParameter("url2"));		
		} catch (ParseException e) {	
			e.printStackTrace();
		}
		
 
			
		songPo.setSingerid(adminSingerServiceImpl.getSingerPosBySingerName(request.getParameter("singername")).get(0).getSingerid());
	
	   if(request.getParameter("cdname")!=null &&request.getParameter("cdname")!=""){
		songPo.setCdid(adminCDServiceImpl.selectCdNameBySearch(request.getParameter("cdname")).get(0).getCdid());
	   }
		try {
			adminSongServiceImpl.addSong(songPo);
		} catch (AdminException e) {
			
		}
		   return "redirect:getSongPo";
	}

	@RequestMapping("/upSong")
	@ResponseBody
	//歌曲上传
	public Map<String, String> upSong(HttpServletRequest request,
			@RequestParam(value = "file") MultipartFile file) {
		String fileName = "";
		try {
			fileName = adminFileServiceImpl.FileUp(file, "mc", request);
		} catch (FileUpUrlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileUpSizeOverException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", fileName);

		return map;
	}
	@RequestMapping("/upLyrics")
	@ResponseBody
	//歌词上传
	public Map<String, String> upLyrics(HttpServletRequest request,
			@RequestParam(value = "file") MultipartFile file) {
	
		String fileName = "";
		try {
			fileName = adminFileServiceImpl.FileUp(file, "/cyric/", request);
		} catch (FileUpUrlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileUpSizeOverException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", fileName);
		
		return map;
	}

	@RequestMapping("/selectSongById")
	public  ModelAndView selectSongById(int songId) throws AdminException{
		
		SongPo songPo=new SongPo();
			 songPo=adminSongServiceImpl.getShowSongPoById(songId);
	
		return new ModelAndView("admin/editsong","songPo",songPo);
	}
	@RequestMapping("/updateSongReturn")
	public  String updateSongReturn(SongPo songPo,HttpServletRequest request){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			if(request.getParameter("date")!=""){
				
				Date  date = sdf.parse(request.getParameter("date"));
				songPo.setPublishdate(date);	
			}
		     //url封装
		   if(request.getParameter("url1") != ""){
			   songPo.setSongurl(request.getParameter("url1"));
		   }
		   
		   if(request.getParameter("url2") != ""){
			   songPo.setCyricurl(request.getParameter("url2"));
		   }
		   
		   adminSongServiceImpl.updateSong(songPo);
		   return "redirect:getSongPo";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "redirect:getSongPo";	
	}
	
	@RequestMapping("/selectSingerNameBySearch")
	@ResponseBody
	public  List<SingerPo> selectSingerNameBySearch(String singername){
		
		List<SingerPo> singerPos = adminSingerServiceImpl.getSingerPosBySingerName(singername);
		 
		return singerPos;
	}
	@RequestMapping("/selectCdNameBySearch")
	@ResponseBody
	public  List<CDPo> selectCdNameBySearch(String cdname,String singername) throws AdminException{

		List<CDPo> cdPos = adminCDServiceImpl.selectCdNameBySearchAndSingerName(cdname,singername); 
		return cdPos;
	}
	//    跳转已删除歌曲页面
			@RequestMapping("/toAdminRecoverSong")
		    public String toAdminRecoverCD(HttpServletRequest request,Integer pageIndex,Model model) throws AdminException{
				String search = request.getParameter("search");
		    	PageBasePo<SongVo> pageBasePo = adminSongServiceImpl.filterDeletedSong(search, pageIndex,5);
		    	model.addAttribute("pageBasePo",pageBasePo);
		    	model.addAttribute("search",search);
		    	return "admin/deletedSong";	
		    }
			
	  //已删除歌曲分页
	  @RequestMapping("/adminRecoverCDPage")
	  @ResponseBody
	    public PageBasePo<SongVo> adminRecoverCDPage(String search,Integer pageIndex) throws AdminException{
	    	PageBasePo<SongVo> pageBasePo = adminSongServiceImpl.filterDeletedSong(search, pageIndex,5);
	    	return pageBasePo;	
	    }
	//歌曲恢复页面
		@RequestMapping("/adminRecoverSong")
	    public String adminRecoverSong(Integer songId,String search) throws AdminException{
		    SongPo songPo = new SongPo();
		    songPo.setSongid(songId);
		    songPo.setSongstateid(0);
		    adminSongServiceImpl.updateSong(songPo);
	    	return "forward:toAdminRecoverSong";	
	    }
		//恢复整页歌曲
		
		@RequestMapping("/recoverPageSong")
		@ResponseBody
	    public String recoverPageSong(String search,Integer pageIndex) throws AdminException{
		PageBasePo<SongVo> pageBasePo = adminSongServiceImpl.filterDeletedSong(search, pageIndex,5);
		List<SongVo> list = pageBasePo.getList();
		SongPo songPo = new SongPo();
		songPo.setSongstateid(0);
		for (SongVo songVo : list) {
		
		    songPo.setSongid(songVo.getSongPo().getSongid());
		   
		    adminSongServiceImpl.updateSong(songPo);
		} 
	    	return "toAdminRecoverSong";	
	    }
		//删除整页歌曲
				@RequestMapping("/deletePageSong")
				@ResponseBody
			    public String deletePageSong(HttpServletRequest request,Integer pageIndex) throws AdminException{
					if(pageIndex == null ){
						pageIndex = 1;
					}
					SongVo songVo = new SongVo();
					SongPo songPo = new SongPo();
				    String songName = request.getParameter("songName");
					if(songName!=null){
						songPo.setSongname(songName);			
					}
					songVo.setSongPo(songPo); 
					PageBasePo<SongVo> pageBasePo =  adminSongServiceImpl.filter(songVo, 5, pageIndex);
			
				List<SongVo> list = pageBasePo.getList();
				for (SongVo songVo2 : list) {
					int songId = songVo2.getSongPo().getSongid();

					adminSongServiceImpl.deleteSong(songId);
				} 
			    	return "getSongPo";	
			    }
}
