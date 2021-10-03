package com.foxconn.controller;

import com.foxconn.dto.AccessTokenDTO;
import com.foxconn.dto.GithubUser;
import com.foxconn.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id("83a37a4c68a738adf829");
        accessTokenDTO.setClient_secret("6ba0b1c54472ddf3395b88d90ed43ec8c23ebdba");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
