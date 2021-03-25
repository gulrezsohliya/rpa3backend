package rpa.RestController.Examination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rpa.Models.Examination.Advertisement;
import rpa.Models.Examination.ExamSubjects;
import rpa.Models.Examination.OptionalSubjects;
import rpa.Models.master.Cell;
import rpa.Models.master.User;
import rpa.Services.Examination.ExaminationServiceInterface;

@RestController
public class ExaminationController {

	private static final Logger LOG = Logger.getLogger(ExaminationController.class.getName());
	@Autowired
	private ExaminationServiceInterface ES;

	@GetMapping(value = "/listAdvertisements", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Advertisement>> listAdvertisements(HttpServletRequest req) {
		try {
			return ResponseEntity.accepted().body(ES.listAdvertisements(((User) req.getSession().getAttribute("user")).getOfficecode()));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOptionalSubjects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OptionalSubjects>> listOptionalSubjects() {
		try {
			List<OptionalSubjects> cells = ES.listOptionalSubjects();
			return ResponseEntity.accepted().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listExamSubjects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExamSubjects>> listExamSubjects() {
		try {
			List<ExamSubjects> cells = ES.listExamSubjects();
			return ResponseEntity.accepted().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listQualificationsCategories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> listQualificationsCategories() {
		try {
			return ResponseEntity.accepted().body(ES.listQualificationsCategories());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	/// CREATE
	/// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping(value = "/createAdvertisement", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createAdvertisement(HttpServletRequest req,
			@RequestBody Advertisement adv) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		adv.setOfficecode(((User) req.getSession().getAttribute("user")).getOfficecode());
		adv.setUsercode(((User) req.getSession().getAttribute("user")).getUsercode());
		switch (ES.createAdvertisement(adv)) {
		case "CREATED":
			response.put("response", HttpStatus.CREATED);
			break;
		case "EXISTS":
			response.put("response", HttpStatus.ALREADY_REPORTED);
			break;
		default:
			response.put("response", HttpStatus.OK);
		}
		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/createOptionalSubject", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createOptionalSubjects(@RequestBody OptionalSubjects subject) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (ES.createOptionalSubject(subject)) {
		case "CREATED":
			response.put("response", HttpStatus.CREATED);
			break;
		case "EXISTS":
			response.put("response", HttpStatus.ALREADY_REPORTED);
			break;
		default:
			response.put("response", HttpStatus.OK);
		}
		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/createExamSubject", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createexamSubjects(@RequestBody ExamSubjects subject) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (ES.createExamSubject(subject)) {
		case "CREATED":
			response.put("response", HttpStatus.CREATED);
			break;
		case "EXISTS":
			response.put("response", HttpStatus.ALREADY_REPORTED);
			break;
		default:
			response.put("response", HttpStatus.OK);
		}
		return ResponseEntity.ok().body(response);
	}

	// UPDATE/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PutMapping(value = "/updateAdvertisement", consumes = "application/json")
	public ResponseEntity<Boolean> updateAdvertisement(@RequestBody Advertisement obj) {

		return ResponseEntity.ok().body(ES.updateAdvertisement(obj));
	}

	@PutMapping(value = "/updateOptionalSubject", consumes = "application/json")
	public ResponseEntity<Boolean> updateOptionalSubjects(@RequestBody OptionalSubjects subject) {

		return ResponseEntity.ok().body(ES.updateOptionalSubject(subject));
	}

	@PutMapping(value = "/updateExamSubject", consumes = "application/json")
	public ResponseEntity<Boolean> updateExamSubjects(@RequestBody ExamSubjects subject) {

		return ResponseEntity.ok().body(ES.updateExamSubject(subject));
	}

	/// DELETE//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@DeleteMapping(value = "/deleteAdvertisement/{code}")
	public ResponseEntity<Boolean> deleteAdvertisements(@PathVariable Integer code) {
		return ResponseEntity.ok().body(ES.deleteAdvertisement(code));
	}

	@DeleteMapping(value = "/deleteOptionalSubject/{code}")
	public ResponseEntity<Boolean> deleteOptionalSubjects(@PathVariable Integer code) {
		return ResponseEntity.ok().body(ES.deleteOptionalSubject(code));
	}

	@DeleteMapping(value = "/deleteExamSubject/{examinationsubjectcode}")
	public ResponseEntity<Boolean> deleteExamSubjects(@PathVariable Integer examinationsubjectcode) {
		return ResponseEntity.ok().body(ES.deleteExamSubject(examinationsubjectcode));
	}
}
