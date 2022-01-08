package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.NameService;
import by.vitalylobatsevich.courser.database.entity.Name;

import by.vitalylobatsevich.courser.http.dto.NameDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/names")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @PostMapping
    public Name save(@RequestBody @Valid final NameDTO nameDTO) {
        return nameService.save(nameDTO, SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
