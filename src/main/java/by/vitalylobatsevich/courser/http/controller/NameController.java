package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.NameService;
import by.vitalylobatsevich.courser.http.dto.NameDTO;

import io.vavr.collection.Seq;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/names")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @GetMapping("/me")
    public Seq<NameDTO> getMe() {
        return nameService.getAllForCurrentUser();
    }

    @PostMapping("/me")
    public void saveMe(@RequestBody @Valid final NameDTO nameDTO) {
        nameService.saveForCurrentUser(nameDTO);
    }

    @DeleteMapping("/me/{languageId}")
    public void deleteMe(@PathVariable final Long languageId) {
        nameService.deleteByLanguageIdForCurrentUser(languageId);
    }

}
