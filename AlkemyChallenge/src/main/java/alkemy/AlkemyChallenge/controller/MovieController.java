package alkemy.AlkemyChallenge.controller;

import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.dto.MovieDto;
import alkemy.AlkemyChallenge.persistance.dto.MovieReduxDto;
import alkemy.AlkemyChallenge.service.impl.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService peliculaService;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<?> search(@Valid @RequestBody MovieDto movieDto){
        MovieDto movieDto1 = peliculaService.register(movieDto);
        return new ResponseEntity<MovieDto>(movieDto1, HttpStatus.CREATED);
    }

    @GetMapping("/id/{movieID}")
    public ResponseEntity<?> search(@PathVariable Long movieID) throws ResourceNotFoundException {
        MovieDto movieDto = peliculaService.findById(movieID);
        return new ResponseEntity<MovieDto>(movieDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{movieID}")
    public ResponseEntity<?> delete(@PathVariable Long movieID) throws ResourceNotFoundException{
        peliculaService.delete(movieID);
        return new  ResponseEntity<>("Movie delete complete", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody MovieDto movieDto)throws ResourceNotFoundException{
        return new ResponseEntity<MovieDto>(peliculaService.update(movieDto), HttpStatus.OK);
    }

    @GetMapping
    public List<?> getAllMovies(@RequestParam(value = "order", required = false) String order, @RequestParam(value = "name", required = false) String name , @RequestParam(value="genre", required = false) Long genero_id) throws ResourceNotFoundException{
        List<MovieDto> movieDtos = new ArrayList<>();
        List<MovieReduxDto> movieReduxDtoList = new ArrayList<>();
        if(name != null){
            movieDtos = peliculaService.findPeliculasByTitle(name);
        }else if (genero_id != null){
            movieDtos = peliculaService.findPeliculasByGenre(genero_id);
        }else if((order != null)&&(order.equals("asc"))){
            movieDtos = peliculaService.findPeliculasByCreationDate();
        }else if((order != null)&&(order.equals("desc"))) {
            movieDtos = peliculaService.findPeliculasByCreationDateOrderDescent();
        }else {
            movieDtos = peliculaService.findAll();
        }
        for (MovieDto movieDto : movieDtos) {
            MovieReduxDto movieReduxDto = mapper.convertValue(movieDto, MovieReduxDto.class);
            movieReduxDtoList.add(movieReduxDto);
       }return movieReduxDtoList;
    }


}
