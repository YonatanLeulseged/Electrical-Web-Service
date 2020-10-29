package edu.csus.csc131.restDemo;

class UsagesNotFoundException extends RuntimeException {

	UsagesNotFoundException(Long id) {
    super("Could not find usage " + id);
  }
//	UsagesNotFoundException(){
//		super("Could not find usage data");
//	}
}