package cn.service.impl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.AdminException;
import cn.exception.UserIdNotExistException;
import cn.mapper.UserPoMapper;
import cn.mapper.UserWithCDPoMapper;
import cn.mapper.UserWithSingerPoMapper;
import cn.mapper.UserWithSongListPoMapper;
import cn.mapper.UserWithSongPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.pojo.UserWithCDPo;
import cn.pojo.UserWithSingerPo;
import cn.pojo.vo.UserCollectionCDVo;
import cn.pojo.vo.UserCollectionSingerVo;
import cn.pojo.vo.UserCollectionSongListVo;
import cn.pojo.vo.UserCollectionSongVo;
import cn.service.admin.AdminUserService;
@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	UserPoMapper userPoMapper;
	@Autowired
	UserWithCDPoMapper userWithCDPoMapper;
	@Autowired
	UserWithSingerPoMapper userWithSingerPoMapper;
	@Autowired
	UserWithSongListPoMapper userWithSongListPoMapper;
	@Autowired
	UserWithSongPoMapper userWithSongPoMapper;

	@Override
	public boolean addUser(UserPo userPo) throws AdminException {
		// 添加用户
		int result = userPoMapper.insert(userPo);
		if (result < 0) {
			throw new AdminException("添加不成功");
		}
		return true;

	}

	@Override
	public boolean updateUser(UserPo userPo) throws AdminException {
		// 修改用户
		int result = userPoMapper.updateByPrimaryKey(userPo);
		if (result < 0) {
			throw new AdminException("修改不成功");
		}
		return true;

	}

	@Override
	public boolean deleteUser(Integer userId) throws AdminException {
		UserPo userPo = userPoMapper.selectByPrimaryKey(userId);
		if (userPo == null) {
			throw new AdminException("用户不存在");
		} else {
			userPo.setUserstateid(1);
			userPoMapper.updateByPrimaryKey(userPo);
			return true;
		}
	}

	@Override
	public boolean deleteUser(List<Integer> list) throws AdminException {
		for (int i = 0; i < list.size(); i++) {
			UserPo userPo = userPoMapper.selectByPrimaryKey(list.get(i));
			if (userPo == null) {
				throw new AdminException("用户不存在");
			} else {
				userPo.setUserstateid(1);
				userPoMapper.updateByPrimaryKey(userPo);
			}
		}
		return true;
	}

	@Override
	public List<UserPo> getAllUserPo() {
		// 获取所用的用户集合
		List<UserPo> po = userPoMapper.selectUsers();
		return po;
	}

	@Override
	public UserPo getUserPoById(int userId) throws AdminException {
		// 根据ID查询用户
		UserPo po = userPoMapper.selectByPrimaryKey(userId);
		if (po == null) {
			throw new AdminException("userId异常");
		}
		return po;
	}

	@Override
	public UserPo getUserPoByName(String userName) {
		// 根据用户名查询
		return userPoMapper.selectByUserName(userName);
	}

	@Override
	public List<UserCollectionCDVo> getAllUserCollectionCDVo() {
		// 获取所有的用户有关的CDVO
		List<UserCollectionCDVo> vos = userWithCDPoMapper.getAllUserWithCDVos();
		return vos;
	}

	@Override
	public List<UserCollectionSingerVo> getAllUserCollectionSingerServiceVo() {
		// 获取所有的用户有关的singerVo
		List<UserCollectionSingerVo> vos = userWithSingerPoMapper
				.getAllSingerUserWithSingerVos();
		return vos;
	}

	@Override
	public List<UserCollectionSongListVo> getAllUserCollectionSongListVo(
			int uSongListId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserCollectionSongVo> getAllUserCollectionSongVo(int uSongId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWithCDPo getUserCollectionCDVoById(int key)
			throws AdminException {
		// 根据主键查询返回关联对象
		UserWithCDPo po = userWithCDPoMapper.selectByPrimaryKey(key);
		if (po == null) {
			throw new AdminException("key异常");
		}

		return po;
	}

	@Override
	public UserWithSingerPo getUserCollectionSingerVoById(int key)
			throws AdminException {
		// 根据主键返回关联对象
		UserWithSingerPo po = userWithSingerPoMapper.selectByPrimaryKey(key);
		if (po == null) {
			throw new AdminException("key异常");
		}
		return po;
	}

	@Override
	public List<UserCollectionSongListVo> getUserCollectionSongListVoById(
			int userId) throws AdminException {
		UserPo userPo = userPoMapper.selectByPrimaryKey(userId);
		if (userPo == null) {
			// 如果id不存在 抛错
			throw new AdminException("用户id不存在");
		}
		List<UserCollectionSongListVo> list = userWithSongListPoMapper
				.selectUserCollectionSongListVoByUserId(userId);

		return list;
	}

	@Override
	public List<UserCollectionSongVo> getUserCollectionSongVoById(int userId)
			throws AdminException {
		// 根据id查询出对应的userPo对象
		UserPo userPo = userPoMapper.selectByPrimaryKey(userId);
		if (userPo == null) {
			// 如果id不存在 抛错
			throw new AdminException("用户id不存在");
		} else {
			// 查询出所有UserCollectionSongVo对象
			List<UserCollectionSongVo> list = userWithSongPoMapper
					.selectUserCollectionSongVoByUserId(userId);
			return list;
		}
	}
	@Override
	//分页检索，关键字获取对应名称的songVo对象，状态码不为阻塞（不显示）状态
	public PageBasePo<UserPo> filter(UserPo userPo, Integer pageSize, Integer pageIndex) {
		//创建map对象存储搜索条件
		if(pageIndex==null){
			pageIndex=1;
		}
		PageBasePo pageBasePo = new PageBasePo();
	    //字符串处理
		if(userPo.getUsername()!=null)
		 userPo.setUsername("%"+userPo.getUsername()+"%");
		if(userPo.getLoginid()!=null)
	     userPo.setLoginid("%"+userPo.getLoginid()+"%");
		if(userPo.getPhone()!=null)
	     userPo.setPhone("%"+userPo.getPhone()+"%");
		if(userPo.getUsersex()!=null)
	     userPo.setUsersex("%"+userPo.getUsersex()+"%");
	     
		pageBasePo.setIndexEntity(userPo); 
		//map传值
		Map map = new HashMap();
		map.put("username", userPo.getUsername());
		map.put("loginid", userPo.getLoginid());
		map.put("phone", userPo.getPhone());
		map.put("usersex", userPo.getUsersex());
		map.put("pageSize", pageSize); 
		map.put("pageIndex", (pageIndex-1)*pageSize);
		//通过搜索条件搜索到所需数据
		List<UserPo> list = userPoMapper.selectUserPoByUserNameForPagination(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		int count = userPoMapper.selectCountUserPoByUserNameForPagination(map);
		pageBasePo.setAllNum(count);  //总数量
		pageBasePo.setPageCount((int) Math.ceil((double)count/pageSize));  //页数量
		
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize); 
		return pageBasePo;
	}

	public int getUserCount() {
		// TODO Auto-generated method stub
		return  userPoMapper.selectUserCount();
	}
	public PageBasePo<UserPo> filterFreeze(UserPo userPo, Integer pageSize, Integer pageIndex) {
		//创建map对象存储搜索条件
		if(pageIndex==null){
			pageIndex=1;
		}
		PageBasePo pageBasePo = new PageBasePo();
	    //字符串处理
		if(userPo.getUsername()!=null)
		 userPo.setUsername("%"+userPo.getUsername()+"%");
		if(userPo.getLoginid()!=null)
	     userPo.setLoginid("%"+userPo.getLoginid()+"%");
		if(userPo.getPhone()!=null)
	     userPo.setPhone("%"+userPo.getPhone()+"%");
		if(userPo.getUsersex()!=null)
	     userPo.setUsersex("%"+userPo.getUsersex()+"%");
	     
		pageBasePo.setIndexEntity(userPo); 
		//map传值
		Map map = new HashMap();
		map.put("username", userPo.getUsername());
		map.put("loginid", userPo.getLoginid());
		map.put("phone", userPo.getPhone());
		map.put("usersex", userPo.getUsersex());
		map.put("pageSize", pageSize); 
		map.put("pageIndex", (pageIndex-1)*pageSize);
		//通过搜索条件搜索到所需数据
		List<UserPo> list = userPoMapper.selectUserPoByUserNameForPaginationFreeze(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		int count = userPoMapper.selectCountUserPoByUserNameForPaginationFreeze(map);
		pageBasePo.setAllNum(count);  //总数量
		pageBasePo.setPageCount((int) Math.ceil((double)count/pageSize));  //页数量
		
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize); 
		return pageBasePo;
	}

	public PageBasePo<UserPo> filterEmail(UserPo userPo, Integer pageSize, Integer pageIndex) {
		//创建map对象存储搜索条件
		if(pageIndex==null){
			pageIndex=1;
		}
		PageBasePo pageBasePo = new PageBasePo();
	    //字符串处理
		if(userPo.getUsername()!=null)
		 userPo.setUsername("%"+userPo.getUsername()+"%");
		if(userPo.getLoginid()!=null)
	     userPo.setLoginid("%"+userPo.getLoginid()+"%");
		if(userPo.getPhone()!=null)
	     userPo.setPhone("%"+userPo.getPhone()+"%");
		if(userPo.getUsersex()!=null)
	     userPo.setUsersex("%"+userPo.getUsersex()+"%");
	     
		pageBasePo.setIndexEntity(userPo); 
		//map传值
		Map map = new HashMap();
		map.put("username", userPo.getUsername());
		map.put("loginid", userPo.getLoginid());
		map.put("phone", userPo.getPhone());
		map.put("usersex", userPo.getUsersex());
		map.put("pageSize", pageSize); 
		map.put("pageIndex", (pageIndex-1)*pageSize);
		//通过搜索条件搜索到所需数据
		List<UserPo> list = userPoMapper.selectUserPoByUserNameForPaginationEmail(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		int count = userPoMapper.selectCountUserPoByUserNameForPaginationEmail(map);
		pageBasePo.setAllNum(count);  //总数量
		pageBasePo.setPageCount((int) Math.ceil((double)count/pageSize));  //页数量
		
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize); 
		return pageBasePo;
	}


}
