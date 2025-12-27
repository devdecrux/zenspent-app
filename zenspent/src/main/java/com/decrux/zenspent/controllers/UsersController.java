package com.decrux.zenspent.controllers;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.entities.dtos.RegisterUserDto;
import com.decrux.zenspent.entities.dtos.ZSUserDto;
import com.decrux.zenspent.exceptions.UserNotFoundException;
import com.decrux.zenspent.repositories.UserRepository;
import com.decrux.zenspent.services.user_avatar.UserAvatarService;
import com.decrux.zenspent.services.user_registration.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UsersController {

    private final RegisterUser registerUser;
    private final UserRepository userRepository;
    private final UserAvatarService userAvatarService;

    @Value("${zenspent.avatar.storage-dir}")
    private String avatarStorageDir;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterUserDto registerUserDTO) {
        this.registerUser.registerUser(registerUserDTO);
    }

    @GetMapping
    public ZSUserDto retrieveUserData(@AuthenticationPrincipal ZSUser user) {
        return ZSUserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(resolveAvatarDataUrl(user))
                .build();
    }

    @PostMapping("/avatar")
    public void uploadAvatar(@RequestParam("avatar") MultipartFile avatar,
                             @AuthenticationPrincipal ZSUser user) {
        user = this.userRepository.findById(user.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        String storedPath = this.userAvatarService.storeAvatar(user, avatar);
        user.setAvatarPath(storedPath);
        this.userRepository.save(user);
    }

    private String resolveAvatarDataUrl(ZSUser user) {
        String storedPath = user.getAvatarPath();
        if (storedPath == null || storedPath.isBlank()) {
            return null;
        }
        Path basePath = Paths.get(avatarStorageDir).toAbsolutePath().normalize();
        Path avatarPath = Paths.get(storedPath);
        if (!avatarPath.isAbsolute()) {
            avatarPath = basePath.resolve(avatarPath);
        }
        avatarPath = avatarPath.normalize();
        if (!avatarPath.startsWith(basePath) || !Files.exists(avatarPath)) {
            return null;
        }
        try {
            byte[] bytes = Files.readAllBytes(avatarPath);
            String contentType = Files.probeContentType(avatarPath);
            if (contentType == null || contentType.isBlank()) {
                contentType = "application/octet-stream";
            }
            return "data:" + contentType + ";base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException ignored) {
            return null;
        }
    }

}
