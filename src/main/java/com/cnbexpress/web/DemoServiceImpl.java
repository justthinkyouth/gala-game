package com.cnbexpress.web;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by tom on 16-12-29.
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoServiceImpl {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String welcome(){
        return "test Web";
    }

    @RequestMapping(value = "redJsp",method = RequestMethod.GET)
    public String redJsp (){
        return "tom";
    }

    @RequestMapping(value = "imgUpload",method = RequestMethod.POST)
    public String imgUpload(@RequestParam("file")MultipartFile file, HttpServletRequest req){
        if(!file.isEmpty()){
            try {
                String filePath = req.getSession().getServletContext().getRealPath("/") + "fileUpload/temp/"
                        + file.getOriginalFilename();
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
