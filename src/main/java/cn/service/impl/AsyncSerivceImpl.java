package cn.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.service.AsyncSerivce;
@Service
public class AsyncSerivceImpl implements AsyncSerivce{

	@Override
	@Async
	public void asyncRemoveToken(long time,HttpServletRequest request)
			throws InterruptedException {
            //让程序暂停100秒，相当于执行一个很耗时的任务
		System.out.println(time);
          Thread.sleep(time);
          //删除邮箱验证码
          request.getSession().removeAttribute("token");
	}

}
