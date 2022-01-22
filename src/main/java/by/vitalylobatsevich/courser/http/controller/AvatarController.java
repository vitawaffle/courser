package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.AvatarService;
import by.vitalylobatsevich.courser.database.entity.Avatar;

import io.vavr.collection.Seq;

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

    @GetMapping("/me")
    public Seq<Avatar> getAll() {
        return avatarService.getAllForCurrentUser();
    }

    @GetMapping("/{id}")
    public Resource getById(@PathVariable final Long id) {
        return avatarService.loadById(id).getOrNull();
    }

    @PostMapping("/me")
    public void store(@RequestParam("file") final MultipartFile file) {
        avatarService.setForCurrentUser(file);
    }

    @DeleteMapping("/me/current")
    public void deleteCurrent() {
        avatarService.deleteCurrent();
    }

    @DeleteMapping("/me/{id}")
    public void deleteById(@PathVariable final Long id) {
        avatarService.deleteByIdForCurrentUser(id);
    }

}
