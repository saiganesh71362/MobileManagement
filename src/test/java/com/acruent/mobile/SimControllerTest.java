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

import com.acruent.mobile.controller.SimController;
import com.acruent.mobile.entity.Mobile;
import com.acruent.mobile.entity.Sim;
import com.acruent.mobile.serviceimpl.SimServiceImpl;

@SpringBootTest
class SimControllerTest {

	@Mock
	SimServiceImpl simServiceImpl;

	@InjectMocks
	SimController simController;

	@Test
	@Order(1)
	void test_createNewSim() {

		Mobile mobile1 = null;
		Sim sim1 = new Sim();
		sim1.setName("Airtel");
		sim1.setMobile(mobile1);

		when(simServiceImpl.createNewSim(sim1)).thenReturn(sim1);
		ResponseEntity<Sim> createNewSim = simController.createNewSim(sim1);

		assertEquals(HttpStatus.CREATED, createNewSim.getStatusCode());
		assertEquals(sim1, createNewSim.getBody());
	}

	@Test
	@Order(2)
	void test_getSimById() {
		Long id = 1l;
		Sim sim1 = new Sim();
		sim1.setId(id);
		sim1.setName("Airtel");
		when(simServiceImpl.getSimById(id)).thenReturn(sim1);
		ResponseEntity<Sim> simById = simController.getSimById(id);
		assertEquals(HttpStatus.FOUND, simById.getStatusCode());
		assertEquals(sim1, simById.getBody());

	}

	@Test
	@Order(3)
	void test_getAllSims() {
		ArrayList<Sim> listOfSims = new ArrayList<>();

		Long id1 = 1l;
		Mobile mobile1 = null;
		Sim sim1 = new Sim();
		sim1.setId(id1);
		sim1.setName("Airtel");
		sim1.setMobile(mobile1);

		Long id2 = 2l;
		Mobile mobile2 = null;
		Sim sim2 = new Sim();
		sim2.setId(id2);
		sim2.setName("Jio");
		sim2.setMobile(mobile2);

		listOfSims.add(sim1);
		listOfSims.add(sim2);

		when(simServiceImpl.getAllSims()).thenReturn(listOfSims);

		ResponseEntity<List<Sim>> allSims = simController.getAllSims();

		assertEquals(HttpStatus.FOUND, allSims.getStatusCode());

		assertEquals(2, allSims.getBody().size());
	}

	@Test
	@Order(4)
	void test_updateSimById() {

		Long id = 1l;
		Sim sim1 = new Sim();
		sim1.setId(id);
		sim1.setName("BSNL");

		when(simServiceImpl.updateSimById(id, sim1)).thenReturn(sim1);

		ResponseEntity<Sim> updateSimById = simController.updateSimById(id, sim1);

		assertEquals(HttpStatus.ACCEPTED, updateSimById.getStatusCode());
		assertEquals(sim1.getName(), updateSimById.getBody().getName());
	}

	@Test
	@Order(5)
	void test_deleteById() {
		Mobile mobile1 = null;
		Long id = 1l;
		Sim sim1 = new Sim();
		sim1.setId(id);
		sim1.setName("Airtel");
		sim1.setMobile(mobile1);

		when(simServiceImpl.deleteById(id)).thenReturn(true);
		ResponseEntity<Boolean> deleteById = simController.deleteById(id);

		assertEquals(HttpStatus.OK, deleteById.getStatusCode());
		assertEquals(true, deleteById.getBody());
	}

	@Test
	@Order(6)
	void test_getBySimName() {
		Long id = 1l;
		String name = "Airtel";
		Sim sim1 = new Sim();
		sim1.setId(id);
		sim1.setName(name);

		when(simServiceImpl.getBySimName(name)).thenReturn(sim1);

		ResponseEntity<Sim> bySimName = simController.getBySimName(name);

		assertEquals(HttpStatus.OK, bySimName.getStatusCode());
		assertEquals(sim1, bySimName.getBody());
	}
}
