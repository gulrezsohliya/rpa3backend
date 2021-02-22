/*
 * @author Decent Khongstia
 * 
 * @Please group related methods together and sort them alphabetically 
 * 
 */

package rpa.controller.admin;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rpa.models.master.Cell;
import rpa.models.master.Office;
import rpa.models.master.User;

@Controller
public class InitializationController {
	private static final Logger LOG = Logger.getLogger(InitializationController.class);

	/********************************************************
	 * PAGEURL
	 ***********************************************************/
	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public String createUser() {
		LOG.info("createuser");
		return "init/createuser";
	}

	@RequestMapping(value = "/initializecell", method = RequestMethod.GET)
	public String initCell() {
		LOG.info("initializecell");
		return "init/initializecell";
	}

	@RequestMapping(value = "/initializeoffice", method = RequestMethod.GET)
	public String initOffice() {
		LOG.info("initializeoffice");
		return "init/initializeoffice";
	}

	/*********************************************************
	 * READ DATA
	 **********************************************************/

	@RequestMapping(value = "/listCells/{cellcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String listCell(@PathVariable String cellcode) {
		JSONObject json = new JSONObject();
		return json.toJSONString();
	}

	@RequestMapping(value = "/listCells", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String listCells() {
		JSONObject json = new JSONObject();
		return json.toJSONString();
	}

	@RequestMapping(value = "/listOffices/{officecode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String listOffice(@PathVariable String officecode) {
		JSONObject json = new JSONObject();
		return json.toJSONString();
	}

	@RequestMapping(value = "/listOffices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String listOffices() {
		JSONObject json = new JSONObject();
		return json.toJSONString();
	}

	@RequestMapping(value = "/listUsers/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String listUser(@PathVariable String userid) {
		JSONObject json = new JSONObject();
		return json.toJSONString();
	}

	@RequestMapping(value = "/listUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONObject listUsers() {
		JSONObject json = new JSONObject();
		return json;
	}

	/*******************************************************
	 * CREATE DATA
	 ***********************************************************/

	@PostMapping(value = "/createcell", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> createCell(@RequestBody Cell cell) {
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
