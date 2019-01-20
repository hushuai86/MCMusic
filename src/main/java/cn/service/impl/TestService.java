package cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mapper.TypePoMapper;
import cn.pojo.TypePo;

@Service
@Transactional
public class TestService {
	@Autowired
	private TypePoMapper mapper;
	public void test(){
		TypePo po = new TypePo();
		po.setTypename("haha");
		mapper.insertSelective(po);
	}
}
