package alkemy.AlkemyChallenge.service.impl;

import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.entity.Genre;
import alkemy.AlkemyChallenge.persistance.dto.GenreDto;
import alkemy.AlkemyChallenge.persistance.repository.IGenreRepository;
import alkemy.AlkemyChallenge.service.IGenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IGenreService {

    @Autowired
   private IGenreRepository iGenreRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public GenreDto register(GenreDto genreDto) {
        Genre genre = mapper.convertValue(genreDto, Genre.class);
        return mapper.convertValue(iGenreRepository.save(genre), GenreDto.class);
    }

    @Override
    public GenreDto findById(Long id) throws ResourceNotFoundException {
        Optional<Genre> genero = iGenreRepository.findById(id);
        if (genero.isPresent()){
            return mapper.convertValue(genero, GenreDto.class);
        }
        throw new ResourceNotFoundException("The genre with ID "+ id + " does not exists");
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Genre> genero = iGenreRepository.findById(id);
        if (genero.isPresent()){
            iGenreRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("The genre with ID "+id +" that you want to delete does not exists.");
    }

    @Override
    public List<GenreDto> findAll() {
        List<GenreDto> genreDtos = new ArrayList<>();
        List<Genre> genres = iGenreRepository.findAll();
        for (Genre genre : genres) {
            GenreDto genreDto = mapper.convertValue(genre, GenreDto.class);
            genreDtos.add(genreDto);
        }
        return genreDtos;
    }

    @Override
    public GenreDto update(GenreDto genreDto) throws ResourceNotFoundException{
        Genre genre = mapper.convertValue(genreDto, Genre.class);
        if (iGenreRepository.existsById(genre.getId())){
            return mapper.convertValue(iGenreRepository.save(genre), GenreDto.class);
        }
        throw new ResourceNotFoundException("The genre with ID "+ genre.getId() +" that you want update does not exist.");
    }


}
