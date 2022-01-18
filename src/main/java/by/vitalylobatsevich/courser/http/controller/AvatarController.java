package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.AvatarService;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatars")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AvatarController extends AppRestController {

    private final AvatarService avatarService;

    @GetMapping("/me/current")
    public Resource getCurrent() {
        return avatarService.loadCurrentForCurrentUser().getOrNull();
    }

    @PostMapping("/me")
    public void store(@RequestParam("file") final MultipartFile file) {
        avatarService.setForCurrentUser(file);
    }

}
