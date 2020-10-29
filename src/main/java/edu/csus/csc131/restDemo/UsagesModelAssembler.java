package edu.csus.csc131.restDemo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class UsagesModelAssembler implements RepresentationModelAssembler<Usages, EntityModel<Usages>> {

  @Override
  public EntityModel<Usages> toModel(Usages usages) {

    return EntityModel.of(usages, //
        linkTo(methodOn(UsagesController.class).one(usages.getId())).withSelfRel(),
        linkTo(methodOn(UsagesController.class).all(usages.getUserId(), usages.getYear(), usages.getMonth(), usages.getDate())).withRel("hourlyUsage"));
  }
  
}