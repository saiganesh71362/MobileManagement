package com.acruent.mobile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.acruent.mobile.controller.MobileController;
import com.acruent.mobile.entity.Mobile;
import com.acruent.mobile.serviceimpl.MobileServiceImpl;

@SpringBootTest
class MobileControllerTest {

	@Mock
	MobileServiceImpl mobileServiceImpl;

	@InjectMocks
	MobileController mobileController;

	@Test
	@Order(1)
	void test_createNewMobile() {

		Mobile mobile1 = new Mobile();
		mobile1.setName("iPhone 13");
		mobile1.setPrice(99999.99);
		mobile1.setYear(2021);

		when(mobileServiceImpl.createNewMobile(mobile1)).thenReturn(mobile1);

		ResponseEntity<Mobile> createNewMobile = mobileController.createNewMobile(mobile1);

		assertEquals(HttpStatus.CREATED, createNewMobile.getStatusCode());
		assertEquals(mobile1, createNewMobile.getBody());
	}

	@Test
	@Order(2)
	void test_getMobileById() {
		Long id = 1l;
		Mobile mobile1 = new Mobile();
		mobile1.setId(id);
		mobile1.setName("iPhone 13");
		mobile1.setPrice(99999.99);
		mobile1.setYear(2021);

		when(mobileServiceImpl.getMobileById(id)).thenReturn(mobile1);

		ResponseEntity<Mobile> mobileById = mobileController.getMobileById(id);

		assertEquals(HttpStatus.FOUND, mobileById.getStatusCode());
		assertEquals(mobile1, mobileById.getBody());
	}

	@Test
	@Order(3)
	void test_getAllMobiles() {
		List<Mobile> mobiles = new ArrayList<>();

		Mobile mobile1 = new Mobile();
		mobile1.setName("iPhone 13");
		mobile1.setPrice(99999.99);
		mobile1.setYear(2021);

		// Create Mobile 2
		Mobile mobile2 = new Mobile();
		mobile2.setName("Samsung Galaxy S21");
		mobile2.setPrice(79999.99);
		mobile2.setYear(2021);

		// Create Mobile 3
		Mobile mobile3 = new Mobile();
		mobile3.setName("Google Pixel 6");
		mobile3.setPrice(69999.99);
		mobile3.setYear(2021);

		mobiles.add(mobile1);
		mobiles.add(mobile2);
		mobiles.add(mobile3);

		when(mobileServiceImpl.getAllMobiles()).thenReturn(mobiles);

		ResponseEntity<List<Mobile>> allMobiles = mobileController.getAllMobiles();

		assertEquals(HttpStatus.FOUND, allMobiles.getStatusCode());

		assertEquals(3, allMobiles.getBody().size());
	}

	@Test
	@Order(4)
	void test_updateMobileById() {
		Long id = 1l;

		Mobile updateMobile = new Mobile();
		updateMobile.setId(id);
		updateMobile.setName("iPhone 14");
		updateMobile.setPrice(99999.99);
		updateMobile.setYear(2021);

		when(mobileServiceImpl.updateMobileById(id, updateMobile)).thenReturn(updateMobile);

		ResponseEntity<Mobile> updateMobileById = mobileController.updateMobileById(id, updateMobile);

		assertEquals(HttpStatus.ACCEPTED, updateMobileById.getStatusCode());
		assertEquals(updateMobile.getName(), updateMobileById.getBody().getName());

	}

	@Test
	@Order(5)
	void test_deleteMobile() {
		Long id = 1l;

		Mobile updateMobile = new Mobile();
		updateMobile.setId(id);
		updateMobile.setName("iPhone 14");
		updateMobile.setPrice(99999.99);
		updateMobile.setYear(2021);

		when(mobileServiceImpl.deleteMobile(id)).thenReturn(true);
		ResponseEntity<Boolean> deleteMobile = mobileController.deleteMobile(id);

		assertEquals(HttpStatus.OK, deleteMobile.getStatusCode());
		assertEquals(true, deleteMobile.getBody());
	}

}
