package alkemy.AlkemyChallenge.controller;

import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;
import alkemy.AlkemyChallenge.persistance.dto.GenreDto;
import alkemy.AlkemyChallenge.service.impl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genders")
public class GenderController {

    @Autowired
    private GenreService generoService;


    //Metodo save original
    @PostMapping("/save")
    public ResponseEntity<?>search(@Valid @RequestBody GenreDto genreDto){
        GenreDto genreDto1 = generoService.register(genreDto);
        return new ResponseEntity<GenreDto>(genreDto1, HttpStatus.CREATED);
    }

//    Inclusion de imagenes en Registro de Generos, esto
//    @PostMapping("/save")
//    public ResponseEntity<?>search(@Valid @RequestBody GeneroDto generoDto, @RequestParam("file")
//            MultipartFile imageFile){
//
//        Path directorioImagenes = Paths.get("src//main//resources//static//images//genderImages");
//        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
//
//        try {
//            byte[] bytes = imageFile.getBytes();
//            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imageFile.getOriginalFilename());
//            Files.write(rutaCompleta, bytes );
//            generoDto.setImagen(imageFile.getOriginalFilename());
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//
//        GeneroDto generoDto1 = generoService.register(generoDto);
//        return new ResponseEntity<GeneroDto>(generoDto1, HttpStatus.CREATED);
//    }

    @GetMapping("/id/{genderId}")
    public ResponseEntity<?> search(@PathVariable Long genderId) throws  ResourceNotFoundException{
        GenreDto genreDto = generoService.findById(genderId);
        return new ResponseEntity<GenreDto>(genreDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{genderId}")
    public ResponseEntity<?> delete(@PathVariable Long genderId) throws ResourceNotFoundException {
        try {
            generoService.delete(genderId);
            return new  ResponseEntity<>("Gender delete complete", HttpStatus.OK);
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping
    public List<GenreDto> getAllGenres(){
        return generoService.findAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody GenreDto genreDto) throws ResourceNotFoundException{
        return new ResponseEntity<GenreDto>(generoService.update(genreDto), HttpStatus.OK);
    }

}
