package com.acruent.mobile.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acruent.mobile.entity.Sim;
import com.acruent.mobile.globalexceptionhandle.SimDeletionException;
import com.acruent.mobile.globalexceptionhandle.SimNotFoundException;
import com.acruent.mobile.globalexceptionhandle.SimUpdateException;
import com.acruent.mobile.serviceimpl.SimServiceImpl;

@RestController
public class SimController {

	private static final Logger logger = LogManager.getLogger(SimController.class);
	
	SimServiceImpl simServiceImpl;

	public SimController(SimServiceImpl simServiceImpl) {
		super();
		this.simServiceImpl = simServiceImpl;
	}

	@PostMapping("/sim/newSimCreate")
	public ResponseEntity<Sim> createNewSim(@RequestBody Sim sim) {
		logger.info("Request Received To Create A New Sim: {}", sim);
		Sim createNewSim = simServiceImpl.createNewSim(sim);
		logger.info("Sim Created Successfully: {}", createNewSim);
		return new ResponseEntity<>(createNewSim, HttpStatus.CREATED);
	}

	@GetMapping("/sim/getSimById/{id}")
	public ResponseEntity<Sim> getSimById(@PathVariable Long id) throws SimNotFoundException {
		logger.info("Received Request To Get Sim By ID: {}", id);
		Sim simById = simServiceImpl.getSimById(id);
		logger.info("Successfully Retrieved Sim with ID: {}", simById.getId() + "--" + simById.getName());
		return new ResponseEntity<>(simById, HttpStatus.FOUND);
	}

	@GetMapping("/sim/getAllSims")
	public ResponseEntity<List<Sim>> getAllSims() {
		logger.info("Received Request To Get All Sim Data");
		List<Sim> allSims = simServiceImpl.getAllSims();
		logger.info("Successfully Retrieved Sim {} ", allSims.size());
		return new ResponseEntity<>(allSims, HttpStatus.FOUND);
	}

	@PutMapping("/sim/updateSimById/{id}")
	public ResponseEntity<Sim> updateSimById(@PathVariable Long id, @RequestBody Sim sim) throws SimUpdateException {
		logger.info("Received Request To Update Sim With ID: {}", id);
		logger.debug("Sim details: {}", sim);
		Sim updateSimById = simServiceImpl.updateSimById(id, sim);
		logger.info("Successfully Updated Sim With ID: {}", id);
		return new ResponseEntity<>(updateSimById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/sim/deleteById/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Long id) throws SimDeletionException {
		logger.info("Received Request To Delete Sim With ID: {}", id);
		Boolean deleteById = simServiceImpl.deleteById(id);
		logger.info("Successfully Deleted Sim With ID: {}", id);
		return new ResponseEntity<>(deleteById, HttpStatus.OK);
	}

	@GetMapping("/sim/getSimByName")
	public ResponseEntity<Sim> getBySimName(@RequestParam(value = "simName") String name) {
		logger.info("Received Request To  Sim  Name: {}", name);
		Sim bySimName = simServiceImpl.getBySimName(name);
		if (bySimName != null) {
			logger.info("Sim Found: {}", bySimName);
			return new ResponseEntity<>(bySimName, HttpStatus.OK);
		} else {
			logger.warn("Sim With Name Not Found {}", name);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
