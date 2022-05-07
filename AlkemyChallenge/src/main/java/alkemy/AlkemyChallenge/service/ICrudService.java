package alkemy.AlkemyChallenge.service;

import alkemy.AlkemyChallenge.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICrudService<T>{

    T register(T t);
    T findById(Long id) throws ResourceNotFoundException;
    void delete(Long id) throws ResourceNotFoundException;
    List<T> findAll();
    T update(T t) throws ResourceNotFoundException;

}
