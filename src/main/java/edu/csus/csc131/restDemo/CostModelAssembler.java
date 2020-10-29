package edu.csus.csc131.restDemo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class CostModelAssembler implements RepresentationModelAssembler<Cost, EntityModel<Cost>> {

  @Override
  public EntityModel<Cost> toModel(Cost cost) {

    return EntityModel.of(cost);
  }
}