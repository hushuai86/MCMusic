package cn.service;

import cn.exception.StateBlockException;

/**
 * 状态连表阻塞服务接口
 * @author taz
 *
 */

public interface StateBlockService {
	
	/**
	 * 歌手状态阻塞
	 * 歌手的歌曲状态变为全部阻塞
	 * @param singerId
	 * @return true 阻塞成功
	 */
	public boolean singerWithSongStateBlock(int singerId) throws StateBlockException;
	
	
	/**
	 * 歌手的CD状态全部变为阻塞
	 * @param singerId
	 * @return true 阻塞成功
	 */
	public boolean singerWithCdStateBlock(int singerId) throws StateBlockException;
	
	/**
	 * CD状态变为阻塞
	 * CD对应的song变为阻塞
	 * @param CDid
	 * @return true 阻塞成功
	 */
	public boolean CdWithSongStateBlock(int CDId) throws StateBlockException;
	
	
	
}
