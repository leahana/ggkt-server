package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.interfacle.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.OnClose;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LeahAna
 * @Date: 2022/7/11 10:13
 * @Desc: 用户登录
 */

@Api("用户登录")
@RestController
@RequestMapping("/admin/vod/user")
//@CrossOrigin // 跨域
public class UserLoginController {

    /**
     * 用户登录
     * @return 登陆结果
     */
    @PostMapping("login")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);
    }
    /**
     * 获取用户信息
     * @return 用户信息
     */
    @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://leahana-tanhua.oss-cn-hangzhou.aliyuncs.com/2022/04/09/1a559369-b0e2-49ea-98d9-8478ca9f04ff.png\n");
        return Result.ok(map);
    }
    /**
     * 退出
     * @return 退出登录结果
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }


}
