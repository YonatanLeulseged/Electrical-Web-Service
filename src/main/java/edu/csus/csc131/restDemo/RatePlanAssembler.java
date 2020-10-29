package edu.csus.csc131.restDemo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class RatePlanAssembler implements RepresentationModelAssembler<Plan, EntityModel<Plan>> {

  @Override
  public EntityModel<Plan> toModel(Plan plan) {

    return EntityModel.of(plan, //
        linkTo(methodOn(RatePlanController.class).one(plan.getName())).withSelfRel(),
        linkTo(methodOn(RatePlanController.class).all()).withRel("plans"));
  }
}