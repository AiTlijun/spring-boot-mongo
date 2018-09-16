package com.example.springbootmongo.controller;

import com.example.springbootmongo.entity.User;
import com.example.springbootmongo.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Controller
@Slf4j
public class UploadFileController {

    @RequestMapping(value = "upload")
    public void upload(HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                System.out.println("文件为空");
            }
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = req.getSession().getServletContext().getRealPath( File.separator) + "file"+ File.separator; // 上传后的路径
            //String filePath = "E://test//";
           // String filePath = "src/main/resources/static/file/";

            log.info("filePath { }" + filePath);
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String filename = "/temp-rainy/" + fileName;
            WebUtil.writeStrToClient(resp, "200");
        } catch (Exception e) {
            WebUtil.writeStrToClient(resp, "-1");
        }
        return;
    }
}
