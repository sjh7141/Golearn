package com.golearn.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class JwtPreFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtPreFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (!username.equals("anonymousUser")) {
			String[] data = username.split(",");
			RequestContext context = RequestContext.getCurrentContext();
			context.addZuulRequestHeader("X-USERNAME", data[0]);
			context.addZuulRequestHeader("X-USERNO", data[1]);
			
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
