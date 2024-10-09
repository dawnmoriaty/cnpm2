package com.example.demo2.controllers;

import com.example.demo2.models.entity.Roles;
import com.example.demo2.models.entity.Users;
import com.example.demo2.repositories.IRoleRepository;
import com.example.demo2.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private PasswordEncoder passwordEncoder;
    private IRoleRepository roleRepository;
    private IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String handleLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showFormRegister(Model model) {
        model.addAttribute("userRegister", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("userRegister") Users users) {
        Roles role = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("role not found"));
        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setStatus(true);
        users.setRoles(roles);
        System.out.println(users);
        userRepository.save(users);
        return "login";
    }
    @PostMapping("/login")
    public String handleLoginPost(@RequestParam("userName") String username,
                                  @RequestParam("password") String password,
                                  Model model) {
        try {
            // Thực hiện xác thực thông tin đăng nhập
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Nếu đăng nhập thành công, chuyển hướng về trang chính
            return "redirect:/home";
        } catch (BadCredentialsException e) {
            // Nếu thông tin không hợp lệ, trả về trang login với thông báo lỗi
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }


}