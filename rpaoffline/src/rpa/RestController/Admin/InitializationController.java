package rpa.RestController.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rpa.Models.Examination.ExamCenter;
import rpa.Models.Examination.ExamSubjects;
import rpa.Models.Examination.OfficeCenter;
import rpa.Models.Examination.OptionalSubjects;
import rpa.Models.Examination.Venue;
import rpa.Models.master.Cell;
import rpa.Models.master.Office;
import rpa.Models.master.OtherCategories;
import rpa.Models.master.Pageurls;
import rpa.Models.master.User;
import rpa.Services.Admin.IntializationServiceInterface;

@RestController
public class InitializationController {

	private static final Logger LOG = Logger.getLogger(InitializationController.class.getName());
	@Autowired
	private IntializationServiceInterface IS;

	/*********************************************************
	 * READ DATA
	 **********************************************************/

	@GetMapping(value = "/listOptionalSubjects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OptionalSubjects>> listOptionalSubjects() {
		try {
			List<OptionalSubjects> cells = IS.listOptionalSubjects();
			return ResponseEntity.accepted().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listExamSubjects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExamSubjects>> listExamSubjects() {
		try {
			List<ExamSubjects> cells = IS.listExamSubjects();
			return ResponseEntity.accepted().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOtherCategories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OtherCategories>> listOtherCategories() {
		try {
			List<OtherCategories> cells = IS.listOtherCategories();
			return ResponseEntity.accepted().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listCells/{officecode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cell>> listCell(@PathVariable Integer officecode) {
		try {
			List<Cell> cells = IS.listCellsforOffice(officecode);
			return ResponseEntity.accepted().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listCells/cellcode/{cellcode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cell>> listCellforCode(@PathVariable Integer cellcode) {
		try {
			List<Cell> cells = IS.listCellsforCode(cellcode);
			return ResponseEntity.accepted().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listCells", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cell>> listCells(HttpServletRequest req) {
		try {
			List<Cell> cells = null;
			if (((User) req.getSession().getAttribute("user")).getCellcode() == null) {
				cells = IS.listCells();
			} else {
				cells = IS.listCellsforCode(((User) req.getSession().getAttribute("user")).getCellcode());
			}
			return ResponseEntity.ok().body(cells);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listExamCenters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExamCenter>> listExamCenters() {
		try {
			List<ExamCenter> examCenters = IS.listExamCenters();
			return ResponseEntity.ok().body(examCenters);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listVenues", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venue>> listVenues() {
		try {
			List<Venue> venues = IS.listVenues();
			return ResponseEntity.ok().body(venues);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/listOfficesAndMappedCenters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Office>> listOffice(HttpServletRequest req) {
		try {
			List<Office> offices = null;
			if (((User) req.getSession().getAttribute("user")).getCellcode() == null) {
				offices = IS.listOfficesAndMappedCenters();
			} else {
				List<Cell> cells = IS.listCellsforCode(((User) req.getSession().getAttribute("user")).getCellcode());
				offices = IS.listOfficesAndMappedCenters(cells.get(0).getOfficecode());
			}
			return ResponseEntity.ok().body(offices);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOffices/{officecode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Office>> listOffice(@PathVariable Integer officecode) {
		try {
			List<Office> offices = IS.listOffices(officecode);
			return ResponseEntity.ok().body(offices);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOffices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Office>> listOffices(HttpServletRequest req) {
		try {
			List<Office> offices = null;
			if (((User) req.getSession().getAttribute("user")).getCellcode() == null) {
				offices = IS.listOffices();
			} else {
				List<Cell> cells = IS.listCellsforCode(((User) req.getSession().getAttribute("user")).getCellcode());
				offices = IS.listOffices(cells.get(0).getOfficecode());
			}
			return ResponseEntity.ok().body(offices);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listUsers/usercode/{usercode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User listUser(@PathVariable Integer usercode) {
		return IS.listUser(usercode);
	}

	@GetMapping(value = "/listUsers/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User listUser(@PathVariable String username) {
		return IS.listUser(username);
	}

	@GetMapping(value = "/listUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> listUsers() {
		return IS.listUser();
	}

	/*******************************************************
	 * CREATE DATA
	 ***********************************************************/
	@PostMapping(value = "/createOptionalSubject", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createOptionalSubjects(@RequestBody OptionalSubjects subject) {
		
		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (IS.createOptionalSubject(subject)) {
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
		switch (IS.createExamSubject(subject)) {
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

	@PostMapping(value = "/createOtherCategory", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> creatOtherCategory(@RequestBody OtherCategories othercategory) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (IS.createOtherCategory(othercategory)) {
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

	@PostMapping(value = "/createvenue", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createvenue(@RequestBody Venue venue) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (IS.createVenue(venue)) {
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

	@PostMapping(value = "/saveOfficeCenters", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> saveOfficeCenters(@RequestBody List<OfficeCenter> offcen) {
		
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (IS.saveOfficeCenters(offcen)) {
			response.put("response", HttpStatus.CREATED);
		}else {
			response.put("response", HttpStatus.METHOD_FAILURE);
		}
		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/createexamcenter", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createExamCenter(@RequestBody ExamCenter center) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (IS.createExamCenter(center)) {
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

	@PostMapping(value = "/createcell", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createCell(@RequestBody Cell cell) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (IS.createcell(cell)) {
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

	@PostMapping(value = "/createoffice", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createOffice(@RequestBody Office office) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		switch (IS.createOffice(office)) {
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

	@PostMapping(value = "/createuser", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createUser(@RequestBody User user) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (IS.saveUser(user)) {
			response.put("response", HttpStatus.CREATED);
			response.put("data", 1);
			return ResponseEntity.ok().body(response);
		}
		response.put("response", HttpStatus.OK);
		response.put("data", -1);
		return ResponseEntity.ok().body(response);
	}

	/*******************************************************
	 * UPDATE DATA
	 ***********************************************************/

	@PutMapping(value = "/updateOptionalSubject", consumes = "application/json")
	public ResponseEntity<Boolean> updateOptionalSubjects(@RequestBody OptionalSubjects subject) {
		
		return ResponseEntity.ok().body(IS.updateOptionalSubject(subject));
	}
	@PutMapping(value = "/updateExamSubject", consumes = "application/json")
	public ResponseEntity<Boolean> updateExamSubjects(@RequestBody ExamSubjects subject) {

		return ResponseEntity.ok().body(IS.updateExamSubject(subject));
	}

	@PutMapping(value = "/updateOtherCategory", consumes = "application/json")
	public ResponseEntity<Boolean> updateOtherCategory(@RequestBody OtherCategories otherCategory) {

		return ResponseEntity.ok().body(IS.updateOtherCategory(otherCategory));
	}

	@PutMapping(value = "/updatevenue", consumes = "application/json")
	public ResponseEntity<Boolean> updateVenue(@RequestBody Venue venue) {

		return ResponseEntity.ok().body(IS.updateVenue(venue));
	}

	@PutMapping(value = "/updateexamcenter", consumes = "application/json")
	public ResponseEntity<Boolean> updateExamcenter(@RequestBody ExamCenter center) {

		return ResponseEntity.ok().body(IS.updateExamCenter(center));
	}

	@PutMapping(value = "/updatecell", consumes = "application/json")
	public ResponseEntity<Boolean> updateCell(@RequestBody Cell cell) {

		return ResponseEntity.ok().body(IS.updatecell(cell));
	}

	@PutMapping(value = "/updateoffice", consumes = "application/json")
	public ResponseEntity<Boolean> updateOffice(@RequestBody Office office) {

		return ResponseEntity.ok().body(IS.updateOffice(office));
	}

	@PutMapping(value = "/updateuser", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> updateUser(@RequestBody User user) {
		LOG.info("updateUser: \n" + user);
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (IS.updateUser(user)) {
			response.put("response", HttpStatus.CREATED);
			response.put("data", 1);
			return ResponseEntity.ok().body(response);
		}
		response.put("response", HttpStatus.OK);
		response.put("data", -1);
		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value = "/updateuser/status", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> updateUserStatus(@RequestBody User user) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (IS.updateUserStatus(user)) {
			response.put("response", HttpStatus.CREATED);
			response.put("data", 1);
			return ResponseEntity.ok().body(response);
		}
		response.put("response", HttpStatus.OK);
		response.put("data", -1);
		return ResponseEntity.ok().body(response);
	}

	/*******************************************************
	 * DELETE DATA
	 ***********************************************************/
	@DeleteMapping(value = "/deleteOptionalSubject/{code}")
	public ResponseEntity<Boolean> deleteOptionalSubjects(@PathVariable Integer code) {
		return ResponseEntity.ok().body(IS.deleteOptionalSubject(code));
	}
	
	@DeleteMapping(value = "/deleteExamSubject/{examinationsubjectcode}")
	public ResponseEntity<Boolean> deleteExamSubjects(@PathVariable Integer examinationsubjectcode) {
		return ResponseEntity.ok().body(IS.deleteExamSubject(examinationsubjectcode));
	}

	@DeleteMapping(value = "/deleteOtherCategory/{othercategorycode}")
	public ResponseEntity<Boolean> deleteOtherCategory(@PathVariable Integer othercategorycode) {
		return ResponseEntity.ok().body(IS.deleteOtherCategory(othercategorycode));
	}

	@DeleteMapping(value = "/deletevenue/{venuecode}")
	public ResponseEntity<Boolean> deleteVenue(@PathVariable Integer venuecode) {
		return ResponseEntity.ok().body(IS.deleteVenue(venuecode));
	}

	@DeleteMapping(value = "/deleteexamcenter/{centercode}")
	public ResponseEntity<Boolean> deleteExamCenter(@PathVariable Integer centercode) {
		return ResponseEntity.ok().body(IS.deleteExamCenter(centercode));
	}

	@DeleteMapping(value = "/deletecell/{cellcode}")
	public ResponseEntity<Boolean> deleteCell(@PathVariable Integer cellcode) {
		return ResponseEntity.ok().body(IS.deletecell(cellcode));
	}

	@DeleteMapping(value = "/deleteuser/{officecode}")
	public ResponseEntity<JSONObject> deleteOffice(@PathVariable String officecode) {
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/deleteuser/{userid}")
	public ResponseEntity<JSONObject> deleteUser(@PathVariable String userid) {
		return ResponseEntity.notFound().build();
	}

}
