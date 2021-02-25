package rpa.controller.rest.initlialization;

import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import rpa.models.master.Office;
import rpa.models.master.User;
import rpa.services.admin.IntializationServiceInterface;

@RestController
public class InitializationController {

	private static final Logger LOG = Logger.getLogger(InitializationController.class.getName());
	@Autowired private IntializationServiceInterface service;

	/*********************************************************
	 * READ DATA
	 **********************************************************/

	@GetMapping(value = "/listCells/{officecode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cell>> listCell(@PathVariable Integer officecode) {
		try {
			List<Cell> cells = service.listCells(officecode);
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
			List<Cell> cells = service.listCells();
			LOG.info("cells: "+cells);
			System.out.println("Cells: "+cells);
			return ResponseEntity.ok().body(cells);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOffices/{officecode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Office>> listOffice(@PathVariable Integer officecode) {
		try {
			List<Office> offices= service.listOffices(officecode);
			return ResponseEntity.ok().body(offices);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listOffices", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Office>> listOffices() {
		LOG.info("listOffices");
		System.out.println("listOffices");
		try {
			List<Office> offices = service.listOffices();
			LOG.info("Offices: "+offices);
			return ResponseEntity.ok().body(offices);
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/listUsers/{usercode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User listUser(@PathVariable Integer usercode) {
		return service.listUser(usercode);
	}

	@GetMapping(value = "/listUsers/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User listUser(@PathVariable String username) {
		return service.listUser(username);
	}

	@GetMapping(value = "/listUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> listUsers() {
		return service.listUser();
	}

	/*******************************************************
	 * CREATE DATA
	 ***********************************************************/

	@PostMapping(value = "/createcell", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JSONObject> createCell(@RequestBody Cell cell) {
		return ResponseEntity.notFound().build();
	}

	@PostMapping(value = "/createoffice", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> createOffice(@RequestBody Office office) {
		return ResponseEntity.notFound().build();
	}

	@PostMapping(value = "/createuser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> createUser(@RequestBody User user) {
		return ResponseEntity.notFound().build();
	}

	/*******************************************************
	 * UPDATE DATA
	 ***********************************************************/

	@PutMapping(value = "/updatecell", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateCell(@RequestBody Cell cell) {
		return ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/updateoffice", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateOffice(@RequestBody Office office) {
		return ResponseEntity.notFound().build();
	}

	@PutMapping(value = "/updateuser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateUser(@RequestBody User user) {
		return ResponseEntity.notFound().build();
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
