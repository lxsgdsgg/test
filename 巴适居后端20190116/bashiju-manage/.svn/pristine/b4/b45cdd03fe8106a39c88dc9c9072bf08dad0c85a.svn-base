package com.bashiju.manage.interceptors;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
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

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			String token = CookieUtils.getCookieValue(request, SYSGlobal.USER_LOGIN_TOKEN);
			String clientIp = CookieUtils.getCookieValue(request, SYSGlobal.USER_IP_CLIENT_TOKEN);
			Map<String,Object> user = ssoQueryServiceApi.queryUserByToken(SYSGlobal.USER_LOGIN_PC+ token);
			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(clientIp)||user == null || user.size() < 1) {
				// 登录超时
				response.sendRedirect(SYSGlobal.LOGIN_URL);
				return false;
			}
			UserThreadLocal.set(user); // 将user对象放置到本地线程中，方便在Controller和Service中获取
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
	}

}
