package dev.backend.HelloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.backend.HelloWorld.models.Todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService service;

    @GetMapping("/get")
    ResponseEntity<List<Todo>> get() {
        return ResponseEntity.ok(service.get());
    }

    @GetMapping("/getpage")
    ResponseEntity<Page<Todo>> getpage(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAllTodoPage(page, size));

    }

    @GetMapping("/get/{id}")
    ResponseEntity<Todo> getById(@PathVariable Long id) {
        return service.getById(id).map(todo -> ResponseEntity.ok(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return ResponseEntity.ok(service.create(todo));
    }

    @PutMapping("/update")
    ResponseEntity<Todo> update(@RequestParam Long id, @RequestBody Todo updT) {
        return service.getById(id)
                .map(todo -> {
                    todo.setTitle(updT.getTitle());
                    todo.setDescription(updT.getDescription());
                    todo.setIsCompleted(updT.getIsCompleted());
                    todo.setEmail(updT.getEmail());
                    return ResponseEntity.ok(service.create(todo));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id) == true) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
