package alkemy.AlkemyChallenge.controller;


import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.dto.CharacterDto;
import alkemy.AlkemyChallenge.persistance.dto.CharacterReduxDto;
import alkemy.AlkemyChallenge.service.impl.CharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<?> search(@Valid @RequestBody CharacterDto characterDto){
        CharacterDto characterDto1 = characterService.register(characterDto);
        return new ResponseEntity<CharacterDto>(characterDto1, HttpStatus.CREATED);
    }

    @GetMapping("/id/{characterId}")
    public ResponseEntity<?> search(@PathVariable Long characterId) throws ResourceNotFoundException {
        CharacterDto characterDto = characterService.findById(characterId);
        return new ResponseEntity<CharacterDto>(characterDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{characterId}")
    public ResponseEntity<?> delete(@PathVariable Long characterId) throws ResourceNotFoundException{
        characterService.delete(characterId);
        return new  ResponseEntity<>("Character delete complete", HttpStatus.OK);
    }

    @GetMapping
    public List<?> getAllCharacter(@RequestParam(value = "name",required = false)String nombre, @RequestParam(value = "age", required = false) Integer edad, @RequestParam(value = "movie", required = false) Long idMovie) throws ResourceNotFoundException{
        List<CharacterDto> personajesDtos = new ArrayList<>();
        List<CharacterReduxDto> characterReduxDtoList = new ArrayList<>();
        if (nombre != null){
            personajesDtos = characterService.findCharacterByName(nombre);
        }else if(edad != null){
            personajesDtos = characterService.findCharacterByAge(edad);
        }else if(idMovie != null){
            personajesDtos = characterService.findCharacterByMovie(idMovie);
        }
        else {
            personajesDtos = characterService.findAll();
        }
        for (CharacterDto characterDto : personajesDtos) {
            CharacterReduxDto characterReduxDto = mapper.convertValue(characterDto, CharacterReduxDto.class);
            characterReduxDtoList.add(characterReduxDto);
        }
        return characterReduxDtoList;
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody CharacterDto characterDto) throws ResourceNotFoundException{
        return new ResponseEntity<CharacterDto>(characterService.update(characterDto), HttpStatus.OK);
    }

}
