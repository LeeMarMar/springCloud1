package cn.itcast.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 定义过滤器类型为前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 改过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体要执行的业务逻辑，登录校验逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取zuul提供的上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        //从上下文对象中获取请求对象
        HttpServletRequest request = context.getRequest();
        //从请求对象获取token信息
        String token = request.getParameter("access-token");
        if(StringUtils.isBlank(token)){
            //过滤该请求，不对其进行过滤
            context.setSendZuulResponse(false);
            //设置状态码为401
            context.setResponseStatusCode(org.apache.http.HttpStatus.SC_UNAUTHORIZED);
            //设置响应信息
            context.setResponseBody("{\"status\":\"401\", \"text\":\"request error!\"}");
        }
        // 校验通过，把登陆信息放入上下文信息，继续向后执行
        context.set("token", token);
        return null;
    }
}
