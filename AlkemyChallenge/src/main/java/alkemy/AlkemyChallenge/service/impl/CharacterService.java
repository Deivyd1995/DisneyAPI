package alkemy.AlkemyChallenge.service.impl;


import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.entity.Movie;
import alkemy.AlkemyChallenge.persistance.entity.CharacterDisney;
import alkemy.AlkemyChallenge.persistance.dto.CharacterDto;
import alkemy.AlkemyChallenge.persistance.repository.IMovieRepository;
import alkemy.AlkemyChallenge.persistance.repository.ICharacterRepository;
import alkemy.AlkemyChallenge.service.ICharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterService implements ICharacterService {



    @Autowired
    ICharacterRepository iCharacterRepository;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    IMovieRepository iMovieRepository;

    @Override
    public CharacterDto register(CharacterDto characterDto) {
        CharacterDisney characterDisney = mapper.convertValue(characterDto, CharacterDisney.class);
        return mapper.convertValue(iCharacterRepository.save(characterDisney), CharacterDto.class);
    }

    @Override
    public CharacterDto findById(Long id) throws ResourceNotFoundException {
        Optional<CharacterDisney> personaje = iCharacterRepository.findById(id);
        if (personaje.isPresent()){
            return mapper.convertValue(personaje, CharacterDto.class);
        }
        throw new ResourceNotFoundException("The character with ID "+id +"  does not exists.");
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<CharacterDisney> personaje = iCharacterRepository.findById(id);
        if (personaje.isPresent()){
            iCharacterRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("The character with ID "+id +" that you want to delete does not exists.");
    }

    @Override
    public List<CharacterDto> findAll() {
        List<CharacterDto> characterDtos = new ArrayList<>();
        List<CharacterDisney> personajes = iCharacterRepository.findAll();
        for (CharacterDisney personaje : personajes) {
            CharacterDto characterDto = mapper.convertValue(personaje, CharacterDto.class);
            characterDtos.add(characterDto);
        }
        return characterDtos;
    }

    @Override
    public CharacterDto update(CharacterDto characterDto) throws ResourceNotFoundException {
        CharacterDisney personaje= mapper.convertValue(characterDto, CharacterDisney.class);
        if (iCharacterRepository.existsById(personaje.getId())){
            return mapper.convertValue(iCharacterRepository.save(personaje), CharacterDto.class);
        }
        throw new ResourceNotFoundException("The character with ID "+ personaje.getId() +" that you want update does not exists.");
    }

    @Override
    public List<CharacterDto> findCharacterByName(String nombre) throws ResourceNotFoundException {
        List<CharacterDto> characterDtos = new ArrayList<>();
        List<CharacterDisney> personajeList = iCharacterRepository.findCharacterByName(nombre);
        if (!personajeList.isEmpty()){
            for (CharacterDisney personaje : personajeList) {
                CharacterDto characterDto = mapper.convertValue(personaje, CharacterDto.class);
                characterDtos.add(characterDto);
            }
            return characterDtos;
        }
        throw new ResourceNotFoundException("The character with name "+nombre +" that you want to find does not exists.");
    }

    @Override
    public List<CharacterDto> findCharacterByAge(Integer edad) throws ResourceNotFoundException{
        List<CharacterDto> characterDtos = new ArrayList<>();
        List<CharacterDisney> personajeList = iCharacterRepository.findCharacterByAge(edad);
        if (!personajeList.isEmpty()){
            for (CharacterDisney personaje : personajeList) {
                CharacterDto characterDto = mapper.convertValue(personaje, CharacterDto.class);
                characterDtos.add(characterDto);
            }
            return characterDtos;
        }
        throw new ResourceNotFoundException("The characters with ages "+edad +" that you want to find does not exists.");
    }

    @Override
    public List<CharacterDto> findCharacterByMovie(Long idMovie) throws ResourceNotFoundException {
        List<CharacterDto> characterDtos = new ArrayList<>();
        if (iMovieRepository.existsById(idMovie)) {
            Movie movie = mapper.convertValue(iMovieRepository.findById(idMovie), Movie.class);
            Set<CharacterDisney> personajeList = movie.getCharacters();
            for (CharacterDisney personaje : personajeList) {
                characterDtos.add(mapper.convertValue(personaje, CharacterDto.class));
            }
            return characterDtos;
        }
        throw new ResourceNotFoundException("The movie with ID "+ idMovie+" that you try to find does not exists.");
    }
}

