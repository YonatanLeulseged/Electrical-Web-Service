package edu.csus.csc131.restDemo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RatePlanController {

  private final RatePlanRepository repository;
  private final RatePlanAssembler assembler;

  RatePlanController(RatePlanRepository repository, RatePlanAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }
  
  // Aggregate root

  @GetMapping("/plans")
  CollectionModel<EntityModel<Plan>> all() {

	  List<EntityModel<Plan>> plan = repository.findAll().stream() //
		      .map(assembler::toModel) //
		      .collect(Collectors.toList());

    return CollectionModel.of(plan, linkTo(methodOn(RatePlanController.class).all()).withSelfRel());
  }

  // Single item
  
  //CollectionModel<EntityModel<Plan>> one() {
	@GetMapping("/plans/{name}")  
	EntityModel<Plan> one(@PathVariable String name) {
		
    Plan plan = repository.findByName(name); //

    return assembler.toModel(plan);
  }

	
	@PutMapping("/plans/{name}")
	  ResponseEntity<?> replacePlan(@RequestBody Plan newPlan, @PathVariable String name) {

		Plan updatedPlan = repository.findByName(name);
		
		newPlan.setId(updatedPlan.getId());
		
		repository.save(newPlan);
		
	    EntityModel<Plan> entityModel = assembler.toModel(newPlan);

	    return ResponseEntity //
	        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
	        .body(entityModel);
	  }
	

  
}
