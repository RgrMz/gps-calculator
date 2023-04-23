package uclm.esi.gps.dvopcalculator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uclm.esi.gps.dvopcalculator.model.Calculator;

@RestController
@RequestMapping(path = "calculator")
public class CalculatorController {

	@PostMapping(value = "/sum")
	public double addTwoNumbers(@RequestParam("n1") String n1, @RequestParam("n2") String n2) {
		try {
			double result;
			
			if (n1.length() > 12 || n2.length() > 12)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One of the numbers has more than 12 digits.");
			
			Calculator myCalculator = new Calculator(Double.parseDouble(n1), Double.parseDouble(n2));
			result = myCalculator.addTwoNumbers();

			return result;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
