package cnb.express.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by tom on 16-12-30.
 */
@Controller
@RequestMapping("game")
public class GamesServiceImpl extends BaseServiceImpl{

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "addGame", method = RequestMethod.POST)
    public String addGame(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest req) {
        String tempDir = req.getSession().getServletContext().getRealPath("/") + "fileUpload/temp/";
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isEmpty()) {
                MultipartFile file = files[i];
                String fileName = file.getOriginalFilename();
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                try {
                    file.transferTo(new File(tempDir + (i + 1) + fileSuffix));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/game";
    }

}
