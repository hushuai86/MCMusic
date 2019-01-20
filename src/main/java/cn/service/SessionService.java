package cn.service;

import javax.servlet.http.HttpServletRequest;

import cn.exception.UserNotForSessionException;
import cn.pojo.UserPo;

/**
 * 自定义session服务接口
 * @author taz
 *
 */
public interface SessionService {
	/**
	 * 得到当前用户实体
	 * @return 返回当前用户实体
	 * @throws UserNotForSessionException session中没有用户实体异常
	 */
	public UserPo getCurrentUserPo(HttpServletRequest request)throws UserNotForSessionException;
	
	/**
	 * 将新的用户实体设置到sessoin中
	 * @param user 更新的用户实体对象
	 */
	public void setcurrentUserPo(HttpServletRequest request,UserPo user);
	
	/**
	 * 检索将关键词设置到session中的 检索cd，singer，song，songList实体对象name中
	 * 名称：indexCD，indexSinger，indexSong，indexSongList
	 * @param index 关键词
	 */
	public void setAllIndexWordForEntity(String index,HttpServletRequest request);
	
	/**
	 * 清空所有的检索cd，singer，song，songList实体对象中的关键词，设置为null
	 */
	public void clearIndexWord(HttpServletRequest request);
}
