package alkemy.AlkemyChallenge.service.impl;

import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.entity.Movie;
import alkemy.AlkemyChallenge.persistance.dto.MovieDto;
import alkemy.AlkemyChallenge.persistance.repository.IMovieRepository;
import alkemy.AlkemyChallenge.service.IMovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieRepository iMovieRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public MovieDto register(MovieDto movieDto) {
        Movie movie = mapper.convertValue(movieDto, Movie.class);
        return mapper.convertValue(iMovieRepository.save(movie), MovieDto.class);
    }

    @Override
    public MovieDto findById(Long id) throws ResourceNotFoundException {
        Optional<Movie> pelicula = iMovieRepository.findById(id);
        if (pelicula.isPresent()){
            return mapper.convertValue(pelicula, MovieDto.class);
        }
        throw  new ResourceNotFoundException("The movie with ID "+ id + " does not exists");
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Movie> pelicula = iMovieRepository.findById(id);
        if (pelicula.isPresent()){
            iMovieRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("The movie with ID "+id +" that you want to delete does not exists.");
    }

    @Override
    public List<MovieDto> findAll() {
        List<MovieDto> movieDtos = new ArrayList<>();
        List<Movie> movies = iMovieRepository.findAll();
        for (Movie movie : movies) {
            MovieDto movieDto = mapper.convertValue(movie, MovieDto.class);
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }


    @Override
    public MovieDto update(MovieDto movieDto) throws ResourceNotFoundException{
        Movie movie = mapper.convertValue(movieDto, Movie.class);
        if (iMovieRepository.existsById(movie.getId())){
            return mapper.convertValue(iMovieRepository.saveAndFlush(movie), MovieDto.class);
        }
        throw new ResourceNotFoundException("The movie with ID "+movie.getId() +" that you want to update does not exist.");
    }

    @Override
    public List<MovieDto> findPeliculasByTitle(String titulo) throws ResourceNotFoundException {
        List<MovieDto> movieDtos = new ArrayList<>();
        MovieDto movieDto = new MovieDto();
        List<Movie> movieList = iMovieRepository.findMoviesByTitle(titulo);
        if (!movieList.isEmpty()){
            for (Movie movie : movieList) {
                movieDto = mapper.convertValue(movie, MovieDto.class);
                movieDtos.add(movieDto);
            }
            return movieDtos;
        }
        throw new ResourceNotFoundException("The movie with title "+titulo +" that you want to find does not exists.");
    }

    @Override
    public List<MovieDto> findPeliculasByGenre(Long genero_id) throws ResourceNotFoundException {
        List<MovieDto> movieDtos = new ArrayList<>();
        List<Movie> movieList = iMovieRepository.findMoviesByGender(genero_id);
        if (movieList.isEmpty()){
            throw new ResourceNotFoundException("The movies with genre ID "+ genero_id +" that you want to find does not exists.");
        }
        for (Movie movie : movieList) {
            MovieDto movieDto = mapper.convertValue(movie, MovieDto.class);
            movieDtos.add(movieDto);
        }
        return movieDtos;

    }

    @Override
    public List<MovieDto> findPeliculasByCreationDate() {
        List<MovieDto> movieDtos = new ArrayList<>();
        List<Movie> movieList = iMovieRepository.findMoviesByCreationDate();
        for (Movie movie : movieList) {
            MovieDto movieDto = mapper.convertValue(movie, MovieDto.class);
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }

    @Override
    public List<MovieDto> findPeliculasByCreationDateOrderDescent() {
        List<MovieDto> movieDtos = new ArrayList<>();
        List<Movie> movieList = iMovieRepository.findMoviesByCreationDateInOrderDescent();
        for (Movie movie : movieList) {
            MovieDto movieDto = mapper.convertValue(movie, MovieDto.class);
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }
}
