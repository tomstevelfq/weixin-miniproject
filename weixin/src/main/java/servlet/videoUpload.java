package servlet;

import beans.video;
import dao.dao;
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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/videoUpload")
public class videoUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        if (!ServletFileUpload.isMultipartContent(req)) {
            // 如果不是则停止
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        String url = null;
        String url1=null;
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(102400*1024);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(102400*1024);

        // 设置最大请求值 (包含文件和表单数据)
        //upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = req.getSession().getServletContext().getRealPath("/videos");
        String uploadPath1 = req.getSession().getServletContext().getRealPath("/images");

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        //这个map 用于存放后端文本内容，key做文件名，value作为文件值
        Map<String,String> map=new HashMap<String,String>();
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    //处理form中非文件内容
                    if (item.isFormField())
                    {
                        String name = item.getFieldName();
                        String value = new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                        map.put(name, value);
                        // String value=map.get("key");
                    }
                    // 处理不在表单中的字段(处理文件内容)
                    else
                    {
                        String fileName = new File(item.getName()).getName();
                        Long startTs = System.currentTimeMillis();
                        String filePath = startTs+ fileName;
                        File storeFile = null;
                        if(fileName.contains(".jpg")||fileName.contains(".png")||fileName.contains(".jpeg")){
                            storeFile = new File(uploadPath1+"/"+filePath);
                            url1="http://127.0.0.1:8080/images/"+filePath;
                        }else{
                            storeFile = new File(uploadPath+"/"+filePath);
                            url="http://127.0.0.1:8080/videos/"+filePath;
                        }

                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }

        String date=map.get("year")+"."+map.get("month")+"."+map.get("day");
        try {
            if(dao.getDate(date)==false){
                dao.addDate(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String title=map.get("title");
        String content =map.get("content");
        String text=map.get("text");

        video v=new video(title,content,url,date,url1);
        System.out.println(map);

        try {
            dao.addVideo(v);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out=resp.getWriter();
        out.write("success");
        out.close();
    }
}
