package cn.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import cn.exception.AdminException;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.exception.StateBlockException;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.pojo.vo.CDVo;
import cn.pojo.vo.SongVo;
import cn.service.impl.SingerServiceImpl;
import cn.service.impl.StateBlockServiceImpl;
import cn.service.impl.admin.AdminCDServiceImpl;
import cn.service.impl.admin.AdminFileServiceImpl;
import cn.service.impl.admin.AdminSingerServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminCDController {
	@Autowired
	private AdminCDServiceImpl adminCDServiceImpl;
	@Autowired
	private AdminSingerServiceImpl adminSingerServiceImpl;
	@Autowired
	private AdminFileServiceImpl adminFileServiceImpl;
	@Autowired
    private StateBlockServiceImpl stateBlockServiceImpl;

	// 跳转专辑信息修改页面
	@RequestMapping("/toAdminUpdateCD")
	public String toAdminUpdateCD(int cdId, Model model) throws AdminException {
		CDPo cdPo = adminCDServiceImpl.getShowCDPoById(cdId);
		SingerPo singerPo = adminSingerServiceImpl.getShowSingerPoById(cdPo
				.getSingerid());
		model.addAttribute("cdPo", cdPo);
		model.addAttribute("singername", singerPo.getSingername());
		return "admin/editCD";
	}

	// 修改专辑信息
	@RequestMapping("/adminUpdateCD")
	public String adminUpdateCD(CDPo cdPo, HttpServletRequest request)
			throws AdminException {
		String singerName = request.getParameter("singername");
		String url = request.getParameter("url");
		// 通过歌手名查歌曲
		SingerPo singerPo = adminSingerServiceImpl
				.getShowSingerPoByName(singerName);
		// 封装歌手Id
		cdPo.setSingerid(singerPo.getSingerid());

		// 日期格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(request.getParameter("date"));
			// 日期封装
			cdPo.setPublishdate(date);
			// url封装
			if (url != null && url != "") {
				cdPo.setCoverurl(url);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adminCDServiceImpl.updateCD(cdPo);
		return "forward:adminListCD";

	}

	// 删除专辑
	@RequestMapping("/adminDeleteCD")
	public String adminDeleteCD(int id) throws AdminException, StateBlockException {
		stateBlockServiceImpl.CdWithSongStateBlock(id);
		return "forward:adminListCD";
	}

	// 专辑列表
	@RequestMapping("/adminListCD")
	public String adminListCD(HttpServletRequest request, Integer pageIndex,
			Model model) throws AdminException {
		String search = request.getParameter("search");
		CDPo po = new CDPo();
		if (search != null) {
			po.setCdname(search);
		}
		CDVo vo = new CDVo();
		vo.setCDPo(po);
		PageBasePo<CDVo> pageBasePo = adminCDServiceImpl.filter(vo, 5,
				pageIndex);
		model.addAttribute("pageBasePo", pageBasePo);
		model.addAttribute("search", search);
		return "admin/CD";
	}

	// 专辑分页ajax
	@RequestMapping("/adminPageCD")
	@ResponseBody
	public PageBasePo<CDVo> adminPageCD(String search, Integer pageIndex) {
		CDPo po = new CDPo();
		if (search != null) {
			po.setCdname(search);
		}
		CDVo vo = new CDVo();
		vo.setCDPo(po);
		PageBasePo<CDVo> pageBasePo = adminCDServiceImpl.filter(vo, 5,
				pageIndex);
		return pageBasePo;
	}

	// 文件上传
	@RequestMapping("/upCDImage")
	@ResponseBody
	public Map<String, String> upFile(HttpServletRequest request,
			@RequestParam(value = "file") MultipartFile file) {
		// 图片上传
		String fileName = "";
		try {
			fileName = adminFileServiceImpl.FileUp(file, "CDImg", request);
		} catch (FileUpUrlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileUpSizeOverException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", fileName);
		// 返回url给页面
		return map;
	}

	// 添加专辑
	@RequestMapping("/adminAddCD")
	public String adminAddCD(CDPo cdPo, HttpServletRequest request)
			throws AdminException {
		String singerName = request.getParameter("singername");
		String url = request.getParameter("url");
		// 通过歌手名查歌曲
		SingerPo singerPo = adminSingerServiceImpl
				.getShowSingerPoByName(singerName);
		// 封装歌手Id
		cdPo.setSingerid(singerPo.getSingerid());

		// 日期格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(request.getParameter("date"));
			// 日期封装
			cdPo.setPublishdate(date);
			// url封装
			if (url != null && url != "") {
				cdPo.setCoverurl(request.getParameter("url"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 数据插入
		List<CDPo> list = new ArrayList<CDPo>();
		list.add(cdPo);
		try {
			adminCDServiceImpl.addCDs(list);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
		}
		return "forward:adminListCD";
	}

	// 歌手名模糊查询
	@RequestMapping("/searchSinger")
	@ResponseBody
	public PageBasePo<SingerPo> searchSinger(String search) {
		SingerPo singerPo = new SingerPo();
		singerPo.setAccesscount(1);
		singerPo.setSingername(search);
		PageBasePo<SingerPo> pageBasePo = adminSingerServiceImpl.filter(
				singerPo, 6, 1, null);
		return pageBasePo;
	}

	// 跳转已删除专辑页面
	@RequestMapping("/toAdminDeletedCD")
	public String toAdminDeletedCD(HttpServletRequest request,
			Integer pageIndex, Model model) throws AdminException {
		String search = request.getParameter("search");
		PageBasePo<CDVo> pageBasePo = adminCDServiceImpl.filterNotShowCDPos(
				search, 5, pageIndex);
		model.addAttribute("pageBasePo", pageBasePo);
		model.addAttribute("search", search);
		return "admin/deletedCD";
	}

	// 已删除专辑分页
	@RequestMapping("/adminDeletedCDPage")
	@ResponseBody
	public PageBasePo<CDVo> adminDeletedCDPage(Integer pageIndex, String search) {
		PageBasePo<CDVo> pageBasePo = adminCDServiceImpl.filterNotShowCDPos(
				search, 5, pageIndex);
		return pageBasePo;
	}

	//恢复删除专辑
	@RequestMapping("/adminRecoverCD")
	public String adminRecoverCD(int cdId) throws AdminException {
		CDPo cdPo = adminCDServiceImpl.getCDById(cdId);
		cdPo.setCdstateid(0);
		adminCDServiceImpl.updateCD(cdPo);
		return "forward:toAdminDeletedCD";
	}
	//删除整页
	@RequestMapping("/deletePageCD")
	@ResponseBody
	public String deletePageCD(String search, Integer pageIndex) throws StateBlockException{
		CDPo po = new CDPo();
		if (search != null) {
			po.setCdname(search);
		}
		CDVo vo = new CDVo();
		vo.setCDPo(po);
		PageBasePo<CDVo> pageBasePo = adminCDServiceImpl.filter(vo, 5,
				pageIndex);
		List<CDVo> list = pageBasePo.getList();
		for (CDVo cdVo : list) {
			stateBlockServiceImpl.CdWithSongStateBlock(cdVo.getCDPo().getCdid());
		}
		return "adminListCD";
	}
	//恢复整页数据
			@RequestMapping("/recoverPageCD")
			@ResponseBody
		    public String recoverPageCD(String search,Integer pageIndex) throws AdminException{
				PageBasePo<CDVo> pageBasePo = adminCDServiceImpl.filterNotShowCDPos(
						search, 5, pageIndex);
				List<CDVo> list = pageBasePo.getList();
				CDPo cdPo = new CDPo();
				for (CDVo cdVo : list) {
					cdPo = cdVo.getCDPo();
					cdPo.setCdstateid(0);
					adminCDServiceImpl.updateCD(cdPo);
				}
		    	return "toAdminDeletedCD";	
		    }
}
