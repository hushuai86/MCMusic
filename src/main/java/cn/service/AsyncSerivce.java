package cn.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 异步线程服务接口
 * @author liuqiao
 *
 */
public interface AsyncSerivce {
	/**
	 * 邮箱验证码移除
	 * @param time 过期时间
	 * @param request 获取session
	 * @throws InterruptedException 超过时间异常
	 */
	public void asyncRemoveToken(long time,HttpServletRequest request)throws InterruptedException;
}
