package org.example.dndcombatapi.controller;

import jakarta.validation.Valid;
import org.example.dndcombatapi.dto.UserCharacterDTO;
import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/check")
    public ResponseEntity<CharacterModel> checkCharacter(@RequestBody @Valid UserCharacterDTO userCharacterDTO) {
        CharacterModel character = characterService.transformUserToCharacter(userCharacterDTO);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/example")
    public ResponseEntity<CharacterModel> exampleCharacter() {
        return ResponseEntity.ok(characterService.exampleCharacter());
    }
}
