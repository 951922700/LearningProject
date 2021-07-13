package com.lyl.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileUploadController {
    /**
     * 传统文件上传
     * @return
     */
    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        //使用fileupload组件上传文件
        //上传位置
        //F:\TomCat\apache-tomcat-9.0.22-windows-x64\apache-tomcat-9.0.22\webapps\springMVC_war\
        System.out.println(request.getSession().getServletContext().getRealPath("/"));
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File file=new File(path);
        if (!file.exists()){
            //创建文件夹
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = upload.parseRequest(request);
        //遍历
        for (FileItem item:fileItems) {
            //进行判断，当前item对象是否是上传文件项
            if(item.isFormField()){
                //普通表单项
            }else{
                //上传文件项
                String fileName = item.getName();
                //把文件的名称设置唯一值，uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName=uuid+"_"+fileName;
                //完成文件上传,将item这个文件输出到某个指定文件中
                item.write(new File(path,fileName));
                //删除临时文件
                item.delete();
            }
        }
        return "success";
    }

    /**
     * springMVC文件上传,还有跨服务器传输文件
     * @return
     */
    @RequestMapping("/fileUpload2")
    public String fileUpload1(HttpServletRequest request, MultipartFile upload) throws Exception {

        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File file=new File(path);
        if (!file.exists()){
            //创建文件夹
            file.mkdirs();
        }

        //获取文件名字,注意接口名字
        String fileName=upload.getOriginalFilename();
        //把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName=uuid+"_"+fileName;
        //完成文件上传,将item这个文件输出到某个指定文件中
        upload.transferTo(new File(path,fileName));
        return "success";
    }
}
