package com.cnbexpress.web;

import com.alibaba.fastjson.JSONObject;
import com.cnbexpress.beans.Avatar;
import com.cnbexpress.beans.Game;
import com.cnbexpress.beans.User;
import com.cnbexpress.weixin.api.WxMpInMemoryConfigStorage;
import com.cnbexpress.weixin.api.WxMpService;
import com.cnbexpress.weixin.api.WxMpServiceImpl;
import com.cnbexpress.weixin.bean.result.WxMpOAuth2AccessToken;
import com.cnbexpress.weixin.bean.result.WxMpUser;
import com.cnbexpress.weixin.common.exception.WxErrorException;
import com.cnbexpress.weixin.util.WechatConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by tom on 16-12-30.
 */
@Controller
@RequestMapping("game")
public class GamesServiceImpl extends BaseServiceImpl{

    private int fileNum = 0;
    public static Game game = new Game();
    public static Map<String, User> userMap = new HashMap<String, User>();
    public WxMpService wxMpService;

    @PostConstruct
    public void setWxMpService(){
        wxMpService = new WxMpServiceImpl();
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(WechatConfig.props.getProperty("wechat.service.appId"));
        config.setSecret(WechatConfig.props.getProperty("wechat.service.appsecret"));
        wxMpService.setWxMpConfigStorage(config);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("game", game);
        return "index";
    }

    @RequestMapping(value = "/joinGame", method = RequestMethod.GET)
    public void callBackWeChaOauth(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String code = req.getParameter("code");
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxMpService.userInfo(wxMpOAuth2AccessToken.getOpenId(), "zh_CN");
            User user = new User(wxMpUser.getOpenId(), wxMpUser.getNickname(), wxMpUser.getHeadImgUrl());
            userMap.put(wxMpUser.getOpenId(), user);
            req.getSession().setAttribute("join_user", user);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        res.sendRedirect(WechatConfig.props.getProperty("redirect.uri"));
    }

    @RequestMapping(value = "/addGame", method = RequestMethod.POST)
    @ResponseBody
    public String addGame(@RequestParam String name,@RequestParam long rollTimes, @RequestParam long gameTimes) {
        game.setGameName(name);
        game.setRollTimes(rollTimes);
        game.setGameTimes(gameTimes);
        game.setStatus(0);
        JSONObject res = buildResponse("code", "1");
        return res.toJSONString();
    }

    @RequestMapping(value = "/uploadWqfacPictures", method = RequestMethod.POST, produces = "application/json;charset=utf8")
    @ResponseBody
    public String uploadWqfacPictures(@RequestParam("file") MultipartFile[] files, HttpServletRequest req) throws IOException {
        String tempDir = req.getSession().getServletContext().getRealPath("/") + "temp/";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                try {
                    int sortNum = getFileSortNum();
                    file.transferTo(new File(tempDir+sortNum+fileSuffix));
                    game.getShortlist().add(new Avatar(sortNum+fileSuffix, 0, sortNum+"", fileSuffix));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject result = buildResponse("result", "ok");
        return result.toJSONString();
    }

    public synchronized int getFileSortNum(){
        return fileNum = fileNum+1;
    }

    @RequestMapping(value = "/uploadWinningPictures", method = RequestMethod.POST, produces = "application/json;charset=utf8")
    @ResponseBody
    public String uploadWinningPictures(@RequestParam("file") MultipartFile[] files, HttpServletRequest req) throws IOException {
        String tempDir = req.getSession().getServletContext().getRealPath("/") + "temp/";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                try {
                    int sortNum = getFileSortNum();
                    file.transferTo(new File(tempDir+sortNum+fileSuffix));
                    Avatar avatar = new Avatar(sortNum+fileSuffix, 0, sortNum+"", fileSuffix);
                    game.setWinAvatar(avatar);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONObject result = buildResponse("result", "ok");
        return result.toJSONString();
    }

    @RequestMapping(value = "/setStatus/{status}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject setGameStatus(@PathVariable int status){
        JSONObject res = buildResponse("code",0);
        if(status == 1){
            if(game.getShortlist().isEmpty() || game.getGameTimes() == 0
                    || game.getRollTimes() == 0 || StringUtils.isEmpty(game.getWinAvatar()) ){
                res.put("message", "游戏数据不全，请检查。");
            } else {
                game.setStatus(status);
                game.setStartTime(new Date());
                res.put("code", 1);
            }
        }
        return res;
    }

    @RequestMapping(value = "/cleanGame", method = RequestMethod.GET)
    @ResponseBody
    public String cleanGame(){
        game = new Game();
        JSONObject res = buildResponse("code",1);
        return res.toJSONString();
    }
    @RequestMapping(value = "/refreshGame", method = RequestMethod.GET)
    @ResponseBody
    public String refreshGame(){
        game.setStartTime(new Date());
        JSONObject res = buildResponse("code","ok");
        return res.toJSONString();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Game get(){
        return game;
    }

    @RequestMapping(value = "/getGameUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public User getGameUserInfo(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("join_user");
        if(user != null){
            if(game.getStatus() == 1){
                long currentTime = System.currentTimeMillis();
                long startTimes = game.getStartTime().getTime();
                long startedTime = currentTime-startTimes;
                user.setRollTimes(game.getRollTimes() - startedTime);
                user.setGameTimes(user.getRollTimes() >= 0 ? game.getGameTimes() : game.getGameTimes() + user.getRollTimes());
            }
            user.setGame(game);
            return user;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateUserResult(@RequestParam String openId, @RequestParam long num){
        User user = userMap.get(openId);
        user.setNum(num);
        userMap.put(openId, user);
        return buildResponse("code", 1);
    }

    @RequestMapping(value = "/getResults", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUserMap(){
        //TODO 判断游戏是否结束，通常会在游戏结束后几秒获取
        List<User> userList = new ArrayList<>();
        for(String key: userMap.keySet()){
            userList.add(userMap.get(key));
        }
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getNum() > o2.getNum()){
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return userList;
    }

}
