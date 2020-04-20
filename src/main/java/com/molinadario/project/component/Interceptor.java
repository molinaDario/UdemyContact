package com.molinadario.project.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("interceptor")
public class Interceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(Interceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		request.setAttribute("InitTime", System.currentTimeMillis());

		LOG.info("METHOD preHandle value:" + request.getAttribute("InitTime") + " mls");
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		long initTime = (long) request.getAttribute("InitTime");
		long resultTime = System.currentTimeMillis() - initTime;

		LOG.info("METHOD resultTime: " + resultTime + " mls");
	}

}
