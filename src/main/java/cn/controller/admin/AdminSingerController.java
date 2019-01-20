package cn.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import cn.exception.AdminException;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.service.impl.admin.AdminFileServiceImpl;
import cn.service.impl.admin.AdminSingerServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminSingerController {
	@Autowired
	private AdminSingerServiceImpl adminSingerServiceImpl;
	@Autowired
	private AdminFileServiceImpl adminFileServiceImpl;

	// 歌手列表 根据歌手名 模糊查询
	@RequestMapping("/getSingerPo")
	public String getSingerPo(HttpServletRequest request, Integer pageIndex,
			Model model) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		String singerName = request.getParameter("singerName");

		SingerPo singerPo = new SingerPo();
		if (singerName != null) {
			singerPo.setSingername(singerName);
		}
		singerPo.setAccesscount(1);
		PageBasePo<SingerPo> pageBasePo = adminSingerServiceImpl.filter(
				singerPo, 5, pageIndex, null);
		model.addAttribute("pageBasePo", pageBasePo);
		model.addAttribute("singerName", singerName);
		return "admin/singer";
	}

	@RequestMapping("/getSingerPoByPag")
	@ResponseBody
	public PageBasePo<SingerPo> getSingerPoByPag(HttpServletRequest request,
			Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		String singerName = request.getParameter("singerName");
		SingerPo singerPo = new SingerPo();
		if (singerName != null) {
			singerPo.setSingername(singerName);
		}
		singerPo.setAccesscount(1);
		PageBasePo<SingerPo> pageBasePo = adminSingerServiceImpl.filter(
				singerPo, 5, pageIndex, null);
		return pageBasePo;
	}

	// 删除
	@RequestMapping("/deleteSingerPo")
	public String deleteSingerPo(int singerId) {
		try {

			adminSingerServiceImpl.deleteSinger(singerId);
			return "redirect:getSingerPo";
		} catch (Exception e) {
			return "redirect:getSingerPo";
		}
	}

	@RequestMapping("/upSinger")
	@ResponseBody
	public Map<String, String> upSinger(HttpServletRequest request,
			@RequestParam(value = "file") MultipartFile file) {
		// 图片上传

		String fileName = "";
		try {
			fileName = adminFileServiceImpl.FileUp(file, "singerImgs", request);
		} catch (FileUpUrlException e1) {

			e1.printStackTrace();
		} catch (FileUpSizeOverException e1) {

			e1.printStackTrace();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("token", fileName);

		return map;
	}

	@RequestMapping("/addSinger")
	public String addSinger(SingerPo singerPo, HttpServletRequest request) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		try {
			Date date1 = sdf.parse(request.getParameter("date1"));
			Date date2 = sdf.parse(request.getParameter("date2"));
			// 日期封装
			singerPo.setBirthday(date1);
			singerPo.setDebutdate(date2);

			// url封装
			singerPo.setPhotourl(request.getParameter("url"));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		try {
			adminSingerServiceImpl.addSinger(singerPo);
		} catch (AdminException e) {

		}
		return "redirect:getSingerPo";
	}

	@RequestMapping("/selectSingerById")
	public ModelAndView selectSingerByName(int singerId) throws AdminException {
		SingerPo singerPo = new SingerPo();
		singerPo = adminSingerServiceImpl.getShowSingerPoById(singerId);

		return new ModelAndView("admin/editsinger", "singerPo", singerPo);

	}

	// 修改
	@RequestMapping("/updateSingerReturn")
	public String updateSingerReturn(SingerPo singerPo,
			HttpServletRequest request) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			if (request.getParameter("date1") != "") {

				Date date1 = sdf.parse(request.getParameter("date1"));
				singerPo.setBirthday(date1);
			}
			if (request.getParameter("date2") != "") {

				Date date2 = sdf.parse(request.getParameter("date2"));
				// 日期封装
				singerPo.setDebutdate(date2);
			}
			// url封装
			if (request.getParameter("url") != "") {
				singerPo.setPhotourl(request.getParameter("url"));
			}

			adminSingerServiceImpl.updateSinger(singerPo);
			return "redirect:getSingerPo";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:getSingerPo";
	}

	// 已锁定歌手
	@RequestMapping("/banSinger")
	public String banSinger(HttpServletRequest request, Integer pageIndex,
			Model model) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		String singerName = request.getParameter("singerName");

		SingerPo singerPo = new SingerPo();
		if (singerName != null) {
			singerPo.setSingername(singerName);
		}
		singerPo.setAccesscount(1);
		PageBasePo<SingerPo> pageBasePo = adminSingerServiceImpl.filter1(
				singerPo, 5, pageIndex, null);
		model.addAttribute("pageBasePo", pageBasePo);
		model.addAttribute("singerName", singerName);
		return "admin/singerStatus";
	}

	@RequestMapping("/banSingerByPag")
	@ResponseBody
	public PageBasePo<SingerPo> banSingerByPag(HttpServletRequest request,
			Integer pageIndex, Model model) {
		if (pageIndex == null) {
			pageIndex = 1;
		}

		SingerPo singerPo = new SingerPo();
		singerPo.setAccesscount(1);
		PageBasePo<SingerPo> pageBasePo = adminSingerServiceImpl.filter1(
				singerPo, 5, pageIndex, null);
		model.addAttribute("pageBasePo", pageBasePo);
		return pageBasePo;
	}

	// 恢复歌手
	@RequestMapping("/restoreSingerState")
	public String restoreSingerState(int singerId) throws AdminException {
		SingerPo singerPo = adminSingerServiceImpl
				.getShowSingerPoById1(singerId);
		singerPo.setSingerstateid(0);
		adminSingerServiceImpl.updateSinger(singerPo);
		return "redirect:banSinger";
	}
}
