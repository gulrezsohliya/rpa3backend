package rpa.controller.rest.initlialization;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rpa.models.master.Cell;
import rpa.models.master.ExamCenter;
import rpa.models.master.Office;
import rpa.models.master.User;
import rpa.services.admin.IntializationServiceInterface;

@RestController
public class InitializationController {

	private static final Logger LOG = Logger.getLogger(InitializationController.class.getName());
	@Autowired private IntializationServiceInterface IS;

	/*********************************************************
	 * READ DATA
	 **********************************************************/

	@GetMapping(value = "/listCells/{officecode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cell>> listCell(@PathVariable Integer officecode) {
		try {
			List<Cell> cells = IS.listCells(officecode);
			return ResponseEntity.accepted().body(cells);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}

	@GetMapping(value = "/listCells", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cell>> listCells() {
		LOG.info("listCells");
		System.out.println("listCells");
		try {
			List<Cell> cells = IS.listCells();
			LOG.info("cells: "+cells);
			System.out.println("Cells: "+cells);
			return ResponseEntity.ok().body(cells);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/listExamCenters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExamCenter>> listExamCenters() {
		LOG.info("listExamCenters");
		try {
			List<ExamCenter> examCenters = IS.listExamCenters();
			LOG.info("cells: "+examCenters);
			System.out.println("ExamCenter: "+examCenters);
			return ResponseEntity.ok().body(examCenters);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOffices/{officecode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Office>> listOffice(@PathVariable Integer officecode) {
		try {
			List<Office> offices= IS.listOffices(officecode);
			return ResponseEntity.ok().body(offices);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOffices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Office>> listOffices() {
		LOG.info("listOffices");
		try {
			List<Office> offices = IS.listOffices();
			return ResponseEntity.ok().body(offices);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listUsers/{usercode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User listUser(@PathVariable Integer usercode) {
		return IS.listUser(usercode);
	}

//	@GetMapping(value = "/listUsers/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody User listUser(@PathVariable String username) {
//		return IS.listUser(username);
//	}

	@GetMapping(value = "/listUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> listUsers() {
		return IS.listUser();
	}

	/*******************************************************
	 * CREATE DATA
	 ***********************************************************/

	@PostMapping(value = "/createcell", consumes = "application/json")
	public @ResponseBody ResponseEntity<JSONObject> createCell(@RequestBody Cell cell) {
		return ResponseEntity.notFound().build();
	}

	@PostMapping(value = "/createoffice", consumes = "application/json")
	public ResponseEntity<JSONObject> createOffice(@RequestBody Office office) {
		return ResponseEntity.notFound().build();
	}

	@PostMapping(value = "/createuser", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> createUser(@RequestBody User user) {
		LOG.info("createuser");
		LOG.info(user.toString());
		HashMap<String, Object> response = new HashMap<String, Object>();
		if(IS.saveUser(user)) {
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

	@PutMapping(value = "/updatecell", consumes = "application/json")
	public ResponseEntity<JSONObject> updateCell(@RequestBody Cell cell) {
		return ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/updateoffice", consumes = "application/json")
	public ResponseEntity<JSONObject> updateOffice(@RequestBody Office office) {
		return ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/updateuser", consumes = "application/json")
	public ResponseEntity<HashMap<String, Object>> updateUser(@RequestBody User user) {
		LOG.info("updateUser: \n"+user);
		HashMap<String, Object> response = new HashMap<String, Object>();
		if(IS.updateUser(user)) {
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
		if(IS.updateUserStatus(user)) {
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
	@DeleteMapping(value = "/deletecell/{cellcode}")
	public ResponseEntity<JSONObject> deleteCell(@PathVariable String cellcode) {
		return ResponseEntity.notFound().build();
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
