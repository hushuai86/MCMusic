package cn.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.exception.AdminException;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.vo.SongListVo;
import cn.service.impl.admin.AdminFileServiceImpl;
import cn.service.impl.admin.AdminSongListServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminSongListController {
	@Autowired
	private AdminSongListServiceImpl adminSongListServiceImpl;
	@Autowired
	private AdminFileServiceImpl adminFileServiceImpl;
	
	//查询所有的歌单Po
	@RequestMapping("/getAllSongList")
	public String getAllSongList(HttpServletRequest request,Model model,Integer pageIndex){
		
		String songListName=request.getParameter("search");

		SongListVo songListVo=new SongListVo();
		
		SongListPo songListPo=new SongListPo();
		
		if(songListName!=null)
		{
		songListPo.setSonglistname(songListName);
		}
		
		songListVo.setSongListPo(songListPo);
			
		PageBasePo<SongListVo> pageBasePo=adminSongListServiceImpl.filter1(songListVo, 5, pageIndex);
		
	
        
		model.addAttribute("pageBasePo", pageBasePo);
		model.addAttribute("search",songListName);
		return "admin/songlist";
	}
	
	//删除
	@RequestMapping("/deleteSongListPo")
	
	public String  deleteSongListPo(int songListId){	

		try {
			adminSongListServiceImpl.deleteSongList(songListId);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:getAllSongList";
		}
	
	//修改
	@RequestMapping("/updateSongListPoReturn")
	public String  updateSongListPoReturn(HttpServletRequest request,SongListPo songListPo){
	
		String url=request.getParameter("url");
		if(url!=null&&url!=""){
			songListPo.setImgurl(url);
		}
		try {
			adminSongListServiceImpl.updateSongList(songListPo);

		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "forward:getAllSongList";
	}
	
	//分页
	@RequestMapping("/getPageSongList")
	@ResponseBody
	public PageBasePo<SongListVo> getPageSongList(String search,Integer pageIndex){

		SongListVo songListVo=new SongListVo();
		
		SongListPo songListPo=new SongListPo();
		
		if(search!=null)
		{
		songListPo.setSonglistname(search);
		}

		songListVo.setSongListPo(songListPo);
		
		PageBasePo<SongListVo> pageBasePo=adminSongListServiceImpl.filter1(songListVo, 5, pageIndex);
		
		return pageBasePo;
				
		
	}
	
	@RequestMapping("/getSongListById")
	public String getSongListById(int songListId,Model model){
		
		SongListPo songListPo=new SongListPo();
		try {
			songListPo = adminSongListServiceImpl.getShowSongPoById(songListId);
		
			model.addAttribute("songListPo", songListPo);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/editsonglist";
	}
	    @RequestMapping("/upSongListFile")
		@ResponseBody
			public Map<String, String> upSongListFile(HttpServletRequest request,
					 MultipartFile file) throws FileUpUrlException, FileUpSizeOverException {
				// 图片上传
	    	    
				String fileName = adminFileServiceImpl.FileUp(file,"songListCoverImg",request);;
			

				Map<String, String> map = new HashMap<String, String>();
				map.put("token", fileName);

				return map;
			}
	 
	 //添加歌单
	 @RequestMapping("/addSongList")
	 public String addSongList(SongListPo songListPo,HttpServletRequest request, @RequestParam(value="file") MultipartFile file){
		
		 try {
			 songListPo.setImgurl(request.getParameter("url"));
			adminSongListServiceImpl.addSongList(songListPo);
			
		} catch (AdminException e) {

		}	
		 return "forward:getAllSongList";
	 }
	
}
