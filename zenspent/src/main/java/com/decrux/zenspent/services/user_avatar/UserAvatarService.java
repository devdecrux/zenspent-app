package com.decrux.zenspent.services.user_avatar;

import com.decrux.zenspent.entities.db.auth.ZSUser;
import com.decrux.zenspent.services.storage.StorageInitializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserAvatarService {

    private final String avatarStorageDir;
    private final StorageInitializer storageInitializer;
    private Path avatarStoragePath;

    public UserAvatarService(@Value("${zenspent.avatar.storage-dir}") String avatarStorageDir,
                             StorageInitializer storageInitializer) {
        this.avatarStorageDir = avatarStorageDir;
        this.storageInitializer = storageInitializer;
    }

    @PostConstruct
    public void init() {
        this.avatarStoragePath = storageInitializer.ensureWritableDirectory(Paths.get(avatarStorageDir));
    }

    public String storeAvatar(ZSUser user, MultipartFile avatar) {
        if (avatar == null || avatar.isEmpty()) {
            throw new IllegalArgumentException("Avatar file is required");
        }
        String filename = buildFilename(user.getUserId(), avatar.getOriginalFilename());
        Path targetPath = this.avatarStoragePath.resolve(filename).normalize();
        if (!targetPath.startsWith(this.avatarStoragePath)) {
            throw new IllegalArgumentException("Invalid avatar path");
        }
        try {
            Files.copy(avatar.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            deleteAvatarIfExists(user.getAvatarPath());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to store avatar", e);
        }
        return filename;
    }

    private void deleteAvatarIfExists(String storedPath) {
        if (storedPath == null || storedPath.isBlank()) {
            return;
        }
        Path resolvedPath = this.avatarStoragePath.resolve(storedPath).normalize();
        if (!resolvedPath.startsWith(this.avatarStoragePath)) {
            return;
        }
        try {
            Files.deleteIfExists(resolvedPath);
        } catch (IOException e) {
            // Ignore delete errors to avoid failing a successful upload.
        }
    }

    private static String buildFilename(Long userId, String originalFilename) {
        String extension = "";
        if (originalFilename != null) {
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
                extension = originalFilename.substring(dotIndex).toLowerCase(Locale.ROOT);
            }
        }
        return "user-" + userId + "-" + UUID.randomUUID() + extension;
    }
}
