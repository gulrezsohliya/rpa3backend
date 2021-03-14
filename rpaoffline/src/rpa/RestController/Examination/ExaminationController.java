package rpa.RestController.Examination;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rpa.Models.master.Cell;
import rpa.Services.Examination.ExaminationServiceInterface;

@RestController
public class ExaminationController {

	private static final Logger LOG = Logger.getLogger(ExaminationController.class.getName());
	@Autowired private ExaminationServiceInterface ES;

	@GetMapping(value = "/listQualificationsCategories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String,Object>>> listQualificationsCategories() {
		try {
			return ResponseEntity.accepted().body(ES.listQualificationsCategories());
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
}
