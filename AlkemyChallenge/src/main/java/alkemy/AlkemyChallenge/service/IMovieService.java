package alkemy.AlkemyChallenge.service;

import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.dto.MovieDto;

import java.util.List;

public interface IMovieService extends ICrudService<MovieDto> {


    public List<MovieDto> findPeliculasByTitle(String titulo) throws ResourceNotFoundException;

    public List<MovieDto> findPeliculasByGenre(Long genero_id) throws ResourceNotFoundException;

    public List<MovieDto> findPeliculasByCreationDate();

    List<MovieDto> findPeliculasByCreationDateOrderDescent();


}
