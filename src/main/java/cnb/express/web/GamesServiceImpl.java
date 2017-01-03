package cnb.express.web;

import cnb.express.beans.GameBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 16-12-30.
 */
@Controller
@RequestMapping("game")
public class GamesServiceImpl extends BaseServiceImpl{

    private int fileNum = 1;
    private Map<String,GameBean> dataMap = new HashMap<String, GameBean>();

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "addGame", method = RequestMethod.POST)
    @ResponseBody
    public String addGame(@RequestParam String name) {
        GameBean game = new GameBean();
        game.setGameName(name);
        game.setStatus(0);
        String key = "";
        dataMap.put(key,game);
        JSONObject res = buildResponse("code", "1");
        return res.toJSONString();
    }

    @RequestMapping(value = "files", method = RequestMethod.POST, produces = "application/json;charset=utf8")
    @ResponseBody
    public String uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files, HttpServletRequest req) throws IOException {
        String tempDir = req.getSession().getServletContext().getRealPath("/") + "temp/";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                try {
                    file.transferTo(new File(tempDir + fileNum + fileSuffix));
                    fileNum = fileNum+1;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject result = buildResponse("result", "ok");
        return result.toJSONString();
    }

    @RequestMapping(value = "setStatus/{key}/status", method = RequestMethod.GET)
    public String setGameStatus(@PathVariable String key, @PathVariable int status){
        GameBean game = dataMap.get(key);
        game.setStatus(status);
        dataMap.put(key, game);
        JSONObject res = buildResponse("code",1);
        return res.toJSONString();
    }

}
