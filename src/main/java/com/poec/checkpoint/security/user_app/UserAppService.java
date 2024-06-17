package com.poec.checkpoint.security.user_app;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAppService {
    @Autowired
    private UserAppRepository userRepository;


    public UserApp getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("entity not found")
        );
    }
    /*


    public List<T> getAll() {
        return repository.findAll();
    }

    public T add(T entity) {
        return repository.save(entity);
    }

    public abstract T update(Long id, T entity);

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
    * */
}
