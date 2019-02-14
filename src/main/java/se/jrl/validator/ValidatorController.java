package se.jrl.validator;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorController {

	@RequestMapping(value = "/validate/{data}")
	public ResponseEntity<String> getResource(@PathVariable String data) {
		return Utils.validateCCNumber(data);
	}

	@RequestMapping(value = "/validate")
	public ResponseEntity<String> isNull() {
		return ResponseEntity.badRequest().body("SSN can not be null");
	}

	@RequestMapping(value = "/getInvalidSSNList")
	public List<String> getIncorrectSSNList() {
		return Utils.getInvalidSSNList();
	}

}
