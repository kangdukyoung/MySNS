package com.cos.mysns.web;

import java.util.List;

import com.cos.mysns.Service.UserService;
import com.cos.mysns.config.auth.PrincipalDetail;
import com.cos.mysns.domain.user.User;
import com.cos.mysns.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SearchController {

    private final UserRepository userRepository;

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "/search/friend";
    }

}
