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
import cn.pojo.ImagePo;
import cn.pojo.PageBasePo;
import cn.service.impl.admin.AdminFileServiceImpl;
import cn.service.impl.admin.AdminImageServiceImpl;
import cn.service.impl.admin.AdminSongListServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminCarouselController {

	@Autowired
	private AdminImageServiceImpl adminImageServiceImpl;
	@Autowired
	private AdminFileServiceImpl adminFileServiceImpl;
	@Autowired 
    private AdminSongListServiceImpl adminSongListServiceImpl;
	
	@RequestMapping("/getAllCarousel")
	public String getAllCarousel(HttpServletRequest request, Model model,
			Integer pageIndex) {
		String imagename = request.getParameter("search");

		PageBasePo<ImagePo> pageBasePo = new PageBasePo<ImagePo>();
		if (pageIndex == null) {
			pageIndex = 1;
		}
		try {
			pageBasePo = adminImageServiceImpl.selectImagePosByName(imagename,
					pageIndex, 5);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("pageBasePo", pageBasePo);
		model.addAttribute("search", imagename);
		return "admin/carousel";
	}

	// 轮播图片查询，通过名称
	@RequestMapping("/pageCarousel")
	@ResponseBody
	public PageBasePo<ImagePo> searchCarousel(String search, Integer pageIndex) {

		PageBasePo<ImagePo> pageBasePo = new PageBasePo<ImagePo>();
		if (search.equals("")) {
			search = null;
		}

		try {
			pageBasePo = adminImageServiceImpl.selectImagePosByName(search,
					pageIndex, 5);
		} catch (AdminException e) {

		}
		return pageBasePo;
	}

	@RequestMapping("/deleteCarousel")
	public String deleteCarousel(int imageId) {

		try {
			adminImageServiceImpl.deleteImage(imageId);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:getAllCarousel";
	}

	@RequestMapping("/getImageById")
	public String getImageById(int imageId, Model model) {
		// 修改页面数据回显
		ImagePo imagePo = new ImagePo();
		try {
			imagePo = adminImageServiceImpl.selectImageById(imageId);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("imagePo", imagePo);

		return "admin/editcarousel";
	}

	@RequestMapping("/updateImageReturn")
	public String updateImageReturn(HttpServletRequest request, ImagePo imagePo) {
		String url = request.getParameter("url");

		if (url != null && url != "") {
			imagePo.setImageurl(url);
		}

		try {
			adminImageServiceImpl.UpdateImage(imagePo);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:getAllCarousel";
	}

	@RequestMapping("/upCarousel")
	@ResponseBody
	public Map<String, String> upFile(HttpServletRequest request,
			@RequestParam(value = "file") MultipartFile file) {
		// 图片上传
		String fileName = "";
		try {
			fileName = adminFileServiceImpl.FileUp(file, "img", request);
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

	@RequestMapping("/addCarousel")
	public String addCarousel(ImagePo imagePo, HttpServletRequest request) {
		try {
			imagePo.setImageurl(request.getParameter("url"));
			adminImageServiceImpl.addImage(imagePo);
		} catch (AdminException e) {
			// TODO Auto-generated catch block

		}
		return "forward:getAllCarousel";
	}
		 

}
