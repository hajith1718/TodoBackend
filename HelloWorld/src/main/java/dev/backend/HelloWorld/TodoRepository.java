package dev.backend.HelloWorld;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.backend.HelloWorld.models.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
