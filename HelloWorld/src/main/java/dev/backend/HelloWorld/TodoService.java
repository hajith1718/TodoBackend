package dev.backend.HelloWorld;

import org.springframework.stereotype.Service;

import dev.backend.HelloWorld.models.Todo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    public Todo create(Todo todo) {
        return repo.save(todo);
    }

    public Optional<Todo> getById(Long id) {
        return repo.findById(id);
    }

    public List<Todo> get() {
        return repo.findAll();
    }

    public Boolean delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Todo> getAllTodoPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable);
    }
}
// public Page<Todo> getAllTodoPage(int page, int size) {
// Pageable pageable = PageRequest.of(page, size);
// return todoRepository.findAll(pageable);
// }
