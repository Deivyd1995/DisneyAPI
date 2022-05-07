package alkemy.AlkemyChallenge.service;


import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.dto.CharacterDto;

import java.util.List;

public interface ICharacterService extends ICrudService<CharacterDto>{

  List<CharacterDto> findCharacterByName(String nombre) throws ResourceNotFoundException;

  List<CharacterDto> findCharacterByAge(Integer edad) throws ResourceNotFoundException;

  List<CharacterDto> findCharacterByMovie(Long idMovie) throws ResourceNotFoundException;

}
