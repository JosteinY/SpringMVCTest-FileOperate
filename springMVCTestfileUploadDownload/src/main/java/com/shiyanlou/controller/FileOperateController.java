package com.shiyanlou.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shiyanlou.entity.FileInfo;


@Controller
public class FileOperateController {

    @RequestMapping(value="/upload")
    public String upload(HttpServletRequest request,@ModelAttribute FileInfo fileinfo,Model model) throws Exception{ // 上传文件会自动绑定到 MultipartFile 中

        // 如果文件为空，跳转至失败页面
        if(fileinfo.getFile().isEmpty()){
            return "failure";
        }else {
            // 上传路径
            String path = request.getServletContext().getRealPath("/images/");
            // 上传文件名
            String filename = fileinfo.getFile().getOriginalFilename();
            // 判断路径是否存在，不存在就创建一个
            File filepath = new File(path,filename);
            if(!filepath.getParentFile().exists())
                filepath.getParentFile().mkdirs();
            // 打印文件上传后的完整路径
            System.out.println("文件路径："+path+File.separator+filename);
            // 将文件保存到一个目标文件中
            fileinfo.getFile().transferTo(new File(path+File.separator+filename));
            // 将上传的文件信息添加进 model
            model.addAttribute("fileinfo",fileinfo);
            // 跳转至下载页面
            return "download";
        }

    }
    // 文件下载
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename) throws Exception{
        // 下载路径
        String path = request.getServletContext().getRealPath("/images/");
        File file = new File(path+File.separator+filename);
        HttpHeaders headers = new HttpHeaders();
        // 解决中文乱码
        String downloadfile =  new String(filename.getBytes("UTF-8"),"iso-8859-1");
        // 以下载方式打开文件
        headers.setContentDispositionFormData("attachment", downloadfile);
        // 二进制流
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);

    }
}