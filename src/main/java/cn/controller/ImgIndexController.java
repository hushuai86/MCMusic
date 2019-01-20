package cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mapper.ImagePoMapper;
import cn.pojo.ImagePo;

@Controller
public class ImgIndexController {
	@Autowired
	private ImagePoMapper mapper;
	//得到所有
	@RequestMapping("/getAllImgIndex")
	@ResponseBody
	public List<ImagePo> getAllImg(){
		return mapper.getAll();
	}
}
