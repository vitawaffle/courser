package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.AvatarService;
import by.vitalylobatsevich.courser.http.dto.AvatarDTO;

import io.vavr.collection.Seq;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatars")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @GetMapping("/me/current")
    public Resource getCurrent() {
        return avatarService.loadCurrentForCurrentUser().getOrNull();
    }

    @GetMapping("/me")
    public Seq<AvatarDTO> getAll() {
        return avatarService.getAllForCurrentUser()
                .map(avatar -> new AvatarDTO(avatar));
    }

    @GetMapping("/{id}")
    public Resource getById(@PathVariable final Long id) {
        return avatarService.loadById(id).getOrNull();
    }

    @PostMapping("/me")
    public void store(@RequestParam("file") final MultipartFile file) {
        avatarService.storeAndSetCurrentForCurrentUser(file);
    }

    @DeleteMapping("/me/current")
    public void deleteCurrent() {
        avatarService.deleteCurrentForCurrentUserWithFile();
    }

    @DeleteMapping("/me/{id}")
    public void deleteById(@PathVariable final Long id) {
        avatarService.deleteByIdForCurrentUserWithFile(id);
    }

}
