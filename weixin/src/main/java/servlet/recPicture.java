package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/recPicture")
public class recPicture extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获得磁盘文件条目工厂。
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件上传需要保存的路径，upload文件夹需存在。
        String path = req.getSession().getServletContext().getRealPath("/images");
        //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。
        factory.setRepository(new File(path));
        //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。
        factory.setSizeThreshold(10240*1024);
        //上传处理工具类（高水平API上传处理？）
        ServletFileUpload upload = new ServletFileUpload(factory);

        try{
            //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。
            List<FileItem> list = (List<FileItem>)upload.parseRequest(req);
            for(FileItem item:list){
                //获取表单属性名字。
                String name = item.getFieldName();
                String gname=item.getName();
                System.out.println(gname);
                //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
                if(item.isFormField()){
                    //获取用户具体输入的字符串，
                    String value = item.getString();
                    req.setAttribute(name, value);
                }
                //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
                else{
                    //获取路径名
                    String value = item.getName();
                    System.out.println("value"+value);
                    Long startTs = System.currentTimeMillis();
                    //取到最后一个反斜杠。
                    int start = value.lastIndexOf("\\");
                    //截取上传文件的 字符串名字。+1是去掉反斜杠。
                    String filename =startTs+ value.substring(start+1);
                    req.setAttribute(name, filename);
                    System.out.println("filename"+filename);


                    //第三方提供的方法直接写到文件中。
                    item.write(new File(path,filename));
                    PrintWriter out=resp.getWriter();
                    out.write("http://127.0.0.1:8080/images/"+filename);
                    out.close();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
