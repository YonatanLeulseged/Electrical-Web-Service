package edu.csus.csc131.restDemo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

  private final UserRepository repository;
  private final UserModelAssembler assembler;

  UserController(UserRepository repository, UserModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }
  
  // Aggregate root

  @GetMapping("/users")
  CollectionModel<EntityModel<User>> all() {

		List<EntityModel<User>> users = repository.findAll().stream().map(
				assembler::toModel)
		      .collect(Collectors.toList());

    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }

  @PostMapping("/users")
  ResponseEntity<?> newUser(@RequestBody User newUser) {

    EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

	return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
			.toUri())
        .body(entityModel);
  }

  // Single item
  @GetMapping("/users/{id}")
  EntityModel<User> one(@PathVariable Long id) {

		User user = repository.findById(
				id)
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(user);
  }

  @PutMapping("/users/{id}")
  ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {

		User updatedUser = repository.findById(
				id)
        .map(user -> {
          user.setFirstName(newUser.getFirstName());
          user.setLastName(newUser.getLastName());
          user.setState(newUser.getState());
          user.setCity(newUser.getCity());
          user.setStreetAddress(newUser.getStreetAddress());
          user.setZip(newUser.getZip());
          return repository.save(user);
        }) //
        .orElseGet(() -> {
          newUser.setId(id);
          return repository.save(newUser);
        });

    EntityModel<User> entityModel = assembler.toModel(updatedUser);

	return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/users/{id}")
  ResponseEntity<?> deleteUser(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
