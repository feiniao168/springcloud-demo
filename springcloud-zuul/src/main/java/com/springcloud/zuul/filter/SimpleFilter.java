/**
 * Copyright 2019 Welab, Inc. All rights reserved.
 * WELAB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.springcloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author <a href="mailto:ken.wu@welab-inc.com">ken.wu</a>
 * @date 2019-11-08
 */
public class SimpleFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

    @Override
    public String filterType() {
      return "pre";
    }

    @Override
    public int filterOrder() {
      return 1;
    }

    @Override
    public boolean shouldFilter() {
      return true;
    }

    @Override
    public Object run() {
      RequestContext ctx = RequestContext.getCurrentContext();
      HttpServletRequest request = ctx.getRequest();
      log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
      String sign = request.getParameter("sign");
      if(sign == null) {
          ctx.setSendZuulResponse(false);
          ctx.setResponseStatusCode(401);
          try {
              ctx.getResponse().getWriter().write("sign is not exist!");
          }catch (Exception e){
              
          }
          return null;
      }


      return null;
    }

}
