package se.jrl.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

public class Utils {
 
	private static boolean SSN;
	private static List<String> invalidSSNList = new ArrayList<>();

	public static ResponseEntity<String> validateCCNumber(String data) {
		SSN = check(data);
		if (SSN == false) {
			invalidSSNList.add(data);
			return ResponseEntity.badRequest().body("Is not a valid SSN");
		}
		return ResponseEntity.ok("Is a valid SSN");
	}

	public static List<String> getInvalidSSNList() {
		return invalidSSNList;
	}

	private static boolean check(String ccNumber) {
		ccNumber = ccNumber.replaceAll("\\D+","");
		int sum = 0;
		boolean alternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
	}

}