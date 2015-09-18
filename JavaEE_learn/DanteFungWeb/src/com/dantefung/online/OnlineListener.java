package com.dantefung.online;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class OnlineListener implements HttpSessionAttributeListener{

	
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
		HttpSession session = se.getSession();
		String userName = (String)session.getAttribute("loginInfo");
	    String ip = (String)session.getAttribute("ip");
	    String loginTime = sdf.format(new Date());
	    String lastTime = sdf.format(session.getLastAccessedTime());
	    String jSessionId = session.getId();
	    OnlineBean bean = new OnlineBean();
	    bean.setUserName(userName);
	    bean.setRemoteHost(ip);
	    bean.setLoginTime(loginTime);
	    bean.setLastTime(lastTime);
	    bean.setSession(session);
	    Map<String, OnlineBean> map = (Map<String, OnlineBean>)session.getAttribute("onLine");
	    if(map == null)
	    {
	    	map = new LinkedHashMap<String,OnlineBean>();
	    }
	    map.put(jSessionId,bean);
	    session.getServletContext().setAttribute("onLine", map);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}
