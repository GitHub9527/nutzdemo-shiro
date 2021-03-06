package com.dolplay.nutzshiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.nutz.lang.Strings;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutFilter implements ActionFilter {
	private static Logger logger = LoggerFactory.getLogger(LogoutFilter.class);

	public static final String DEFAULT_LOGOUT_URL = "/logout";
	private String logoutUrl = DEFAULT_LOGOUT_URL;

	@Override
	public View match(ActionContext actionContext) {

		String path = actionContext.getPath();
		if (!Strings.isEmpty(path) && path.equals(logoutUrl)) {

			Subject currentUser = SecurityUtils.getSubject();
			try {
				currentUser.logout();
			} catch (SessionException ise) {
				logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
				return null;
			}catch(Exception e){
				logger.debug("登出发生错误",e);
				return null;
			}
		}

		return null;
	}
}
