package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.AvatarService;
import by.vitalylobatsevich.courser.http.dto.AvatarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/avatars")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AvatarController extends AppRestController {

    private final AvatarService avatarService;

    @PostMapping("/me")
    public void addMe(@RequestBody @Valid final AvatarDTO avatarDTO) {
        avatarService.add(avatarDTO, getUsername());
    }

    @PostMapping("/me/current/{id}")
    public ResponseEntity<?> setCurrent(@PathVariable final Long id) {
        return avatarService.setCurrent(id, getUsername());
    }

}
