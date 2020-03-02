package servlet;

import beans.adminUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account=req.getParameter("account");
        String passwd=req.getParameter("passwd");
        PrintWriter out=resp.getWriter();
        if(account.equals("admin") && passwd.equals("123")){
            adminUser user=new adminUser(account,passwd);
            req.getSession().setAttribute("user",user);
            out.write("success");
            out.close();
        }else{
            out.write("wrong");
            out.close();
        }
    }
}
