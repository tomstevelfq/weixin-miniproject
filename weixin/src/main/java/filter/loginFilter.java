package filter;

import beans.adminUser;
import beans.user;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        req.setCharacterEncoding("utf-8");
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        resp.setCharacterEncoding("utf-8");
        String url=req.getRequestURI();
        if(url.contains("/getIndexViews")||url.contains("/weixinLogin")||url.contains("/login.jsp")||url.contains("/loginServlet")||url.contains("/js/")||url.contains("/css/")||url.contains("/images/")||url.contains("/videos/"))
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            adminUser user= (adminUser) req.getSession().getAttribute("user");
            beans.user _user= (beans.user) req.getSession().getAttribute("weixinUser");
            if(user!=null||_user!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                resp.sendRedirect("/pages/login.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
