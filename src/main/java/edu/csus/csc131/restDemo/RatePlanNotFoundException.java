package edu.csus.csc131.restDemo;

class RatePlanNotFoundException extends RuntimeException {

  RatePlanNotFoundException(String name) {
    super("Could not find rate plan " + name);
  }
}