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
import org.springframework.web.bind.annotation.RestController;

import com.acruent.mobile.entity.Mobile;
import com.acruent.mobile.globalexceptionhandle.MobileDeletionException;
import com.acruent.mobile.globalexceptionhandle.MobileNotFoundException;
import com.acruent.mobile.globalexceptionhandle.MobileUpdateException;
import com.acruent.mobile.serviceimpl.MobileServiceImpl;

@RestController
public class MobileController {

	private static final Logger logger = LogManager.getLogger(MobileController.class);
	
	MobileServiceImpl mobileServiceImpl;

	public MobileController(MobileServiceImpl mobileServiceImpl) {
		super();
		this.mobileServiceImpl = mobileServiceImpl;
	}

	@PostMapping("/mobile/newMobileCreate")
	public ResponseEntity<Mobile> createNewMobile(@RequestBody Mobile mobile) {
		logger.info("Request Received To Create A New Mobile: {}", mobile);
		Mobile createNewMobile = mobileServiceImpl.createNewMobile(mobile);
		logger.info("Mobile Created Successfully: {}", createNewMobile);
		return new ResponseEntity<>(createNewMobile, HttpStatus.CREATED);
	}

	@GetMapping("/mobile/getMobileById/{id}")
	public ResponseEntity<Mobile> getMobileById(@PathVariable Long id) throws MobileNotFoundException {
		logger.info("Received Request To Get Mobile By ID: {}", id);
		Mobile mobileById = mobileServiceImpl.getMobileById(id);
		logger.info("Successfully Retrieved Mobile with ID: {}", mobileById.getId() + "--" + mobileById.getName());
		return new ResponseEntity<>(mobileById, HttpStatus.FOUND);
	}

	@GetMapping("/mobile/getAllMobile")
	public ResponseEntity<List<Mobile>> getAllMobiles() {
		logger.info("Received Request To Get All Mobiles Data");
		List<Mobile> allMobiles = mobileServiceImpl.getAllMobiles();
		logger.info("Successfully Retrieved Mobiles {} ", allMobiles.size());
		return new ResponseEntity<>(allMobiles, HttpStatus.FOUND);
	}

	@PutMapping("/mobile/updateMobile/{id}")
	public ResponseEntity<Mobile> updateMobileById(@PathVariable Long id, @RequestBody Mobile mobile)
			throws MobileUpdateException {
		logger.info("Received Request To Update Mobile With ID: {}", id);
		logger.debug("Mobile details: {}", mobile);
		Mobile updateMobileById = mobileServiceImpl.updateMobileById(id, mobile);
		logger.info("Successfully Updated Mobile With ID: {}", id);
		return new ResponseEntity<>(updateMobileById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/mobile/deleteMobile/{id}")
	public ResponseEntity<Boolean> deleteMobile(@PathVariable Long id) throws MobileDeletionException {
		logger.info("Received Request To Delete Mobile With ID: {}", id);
		Boolean deleteMobile = mobileServiceImpl.deleteMobile(id);
		logger.info("Successfully Deleted Mobile With ID: {}", id);
		return new ResponseEntity<>(deleteMobile, HttpStatus.OK);
	}
}
