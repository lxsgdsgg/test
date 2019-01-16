package com.bashiju.sso.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bashiju.api.RedisServiceApi;
import com.bashiju.api.SsoQueryServiceApi;
import com.bashiju.utils.cookie.CookieUtils;
import com.bashiju.utils.exception.BusinessException;
import com.bashiju.utils.exception.ErrorCodeEnum;
import com.bashiju.utils.global.SYSGlobal;
import com.bashiju.utils.log.ExecutionResult;
import com.bashiju.utils.threadlocal.UserPermissionThreadLocal;
import com.bashiju.utils.threadlocal.UserThreadLocal;

@Service
public class SsoHandlerInterceptor implements HandlerInterceptor {

	@Autowired
	private SsoQueryServiceApi ssoQueryServiceApi;
	@Autowired
	private RedisServiceApi redisServiceApi;
//	@Autowired
//	private PermissionServiceApi permissionService;
	Logger logger=LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = CookieUtils.getCookieValue(request, SYSGlobal.USER_LOGIN_TOKEN);
		logger.warn("SSO登陆获取到的token={}",token);
		String clientIp = CookieUtils.getCookieValue(request, SYSGlobal.USER_IP_CLIENT_TOKEN);
		logger.warn("SSO登陆获取到的clientIp={}",clientIp);
		Map<String,Object> user = ssoQueryServiceApi.queryUserByToken(SYSGlobal.USER_LOGIN_PC+ token);
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(clientIp)||user == null || user.size() < 1) {
			// 登录超时
			logger.warn("SSO登陆超时");
			response.sendRedirect(SYSGlobal.LOGIN_URL);
			return false;
		}
		UserThreadLocal.set(user); // 将user对象放置到本地线程中，方便在Controller和Service中获取
		// 分机用户登录IP检查
//		Object userType = user.get("userType");
//		if (!"1".equals(userType.toString())) {
//			String companyId = user.get("companyId").toString();
//			String parentIp = redisServiceApi.getHash(SYSGlobal.USER_LOGIN_PC+companyId,SYSGlobal.USER_IP_ONLINE_TOKEN);
//			System.out.println("cookite:" + clientIp);
//			System.out.println("redis:" + parentIp);
//			if (!parentIp.contains(clientIp)) {
//				// 登录超时
//				response.sendRedirect(SYSGlobal.LOGIN_URL);
//				return false;
//			}
//		}
		// 登录成功
		response.setHeader("Access-Control-Allow-Origin", "*");
		UserThreadLocal.set(user); // 将user对象放置到本地线程中，方便在Controller和Service中获取
//		String menuId = request.getParameter("menu_id");
//		Map<String, Object> user = UserThreadLocal.get();
		if (user == null || user.isEmpty()) {
			throw new BusinessException(ErrorCodeEnum.NULL_OBJ);
		}
//		if (!StringUtils.isEmpty(menuId))
//			loadPermissionInfos(menuId, user.get("id").toString());
		return true;

	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
			UserThreadLocal.remove();
			ExecutionResult.remove();
			UserPermissionThreadLocal.remove();
	}

}
