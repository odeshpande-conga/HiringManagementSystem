package com.hiringms.controller;

import com.hiringms.dto.ApiResponse;
import com.hiringms.dto.ProfileUpdateRequest;
import com.hiringms.model.User;
import com.hiringms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        User profile = userService.getProfile(user);
        return ResponseEntity.ok(new ApiResponse(true, "Profile retrieved", profile));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse> updateProfile(Authentication authentication,
                                                      @RequestBody ProfileUpdateRequest request) {
        User user = (User) authentication.getPrincipal();
        User updated = userService.updateProfile(user, request);
        return ResponseEntity.ok(new ApiResponse(true, "Profile updated", updated));
    }
}
