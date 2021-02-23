package rpa.controller.rest.admin;

import java.util.List;

import org.apache.log4j.Logger;
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
import rpa.services.admin.AdminService;

@RestController
public class InitializationController {

	private static final Logger LOG = Logger.getLogger(InitializationController.class);
	@Autowired private AdminService service;

	/*********************************************************
	 * READ DATA
	 **********************************************************/

	@GetMapping(value = "/listCells/{cellcode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONObject listCell(@PathVariable String cellcode) {
		JSONObject json = new JSONObject();
		return json;
	}

	@GetMapping(value = "/listCells", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONObject listCells() {
		JSONObject json = new JSONObject();
		return json;
	}

	@GetMapping(value = "/listOffices/{officecode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONObject listOffice(@PathVariable String officecode) {
		JSONObject json = new JSONObject();
		return json;
	}

	@GetMapping(value = "/listOffices", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONObject listOffices() {
		JSONObject json = new JSONObject();
		return json;
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
