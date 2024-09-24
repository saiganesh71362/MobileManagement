package com.acruent.mobile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.acruent.mobile.entity.Mobile;
import com.acruent.mobile.entity.Sim;
import com.acruent.mobile.globalexceptionhandle.SimDeletionException;
import com.acruent.mobile.globalexceptionhandle.SimNotFoundException;
import com.acruent.mobile.globalexceptionhandle.SimUpdateException;
import com.acruent.mobile.repository.SimRepository;
import com.acruent.mobile.serviceimpl.SimServiceImpl;

@SpringBootTest
class SimServiceImplTest {

	@Mock
	SimRepository simRepository;

	@InjectMocks
	SimServiceImpl simServiceImpl;

	@Test
	@Order(1)
	void test_createNewSim() {
		Long id1 = 1l;
		Mobile mobile1 = null;
		Sim sim1 = new Sim();
		sim1.setId(id1);
		sim1.setName("Airtel");
		sim1.setMobile(mobile1);

		when(simRepository.save(sim1)).thenReturn(sim1);

		Sim createNewSim = simServiceImpl.createNewSim(sim1);
		assertEquals(sim1.getId(), createNewSim.getId());
		assertEquals(sim1.getName(), createNewSim.getName());
		assertEquals(sim1.getMobile(), createNewSim.getMobile());

		verify(simRepository, timeout(1)).save(sim1);
	}

	@Test
	@Order(2)
	void test_createNewSim_faild() {
		Sim sim1 = new Sim();
		sim1.setName(null);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			simServiceImpl.createNewSim(sim1);
		});

		assertEquals("Sim name cannot be null or empty", exception.getMessage());
		verify(simRepository, never()).save(sim1);
	}

	@Test
	@Order(3)
	void test_getSimById() {
		Long id1 = 1l;
		Mobile mobile1 = null;
		Sim sim1 = new Sim();
		sim1.setId(id1);
		sim1.setName("Airtel");
		sim1.setMobile(mobile1);

		when(simRepository.findById(id1)).thenReturn(Optional.of(sim1));
		assertEquals(sim1, simServiceImpl.getSimById(id1));
	}

	@Test
	@Order(4)
	void test_getSimById_notFound() {

		Long id1 = 1l;
		Mobile mobile1 = null;
		Sim sim1 = new Sim();
		sim1.setId(id1);
		sim1.setName("Airtel");
		sim1.setMobile(mobile1);

		when(simRepository.findById(id1)).thenReturn(Optional.empty());

		SimNotFoundException assertThrows2 = assertThrows(SimNotFoundException.class,
				() -> simServiceImpl.getSimById(id1));

		assertEquals("Sim ID Not Found :" + id1, assertThrows2.getMessage());

		verify(simRepository).findById(id1);
	}

	@Test
	@Order(5)
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

		when(simRepository.findAll()).thenReturn(listOfSims);
		assertEquals(2, simServiceImpl.getAllSims().size());
	}

	@Test
	@Order(6)
	void test_updateSimById() {
		Long id1 = 1l;
		Mobile mobile1 = null;
		Sim exstingSim = new Sim();
		exstingSim.setId(id1);
		exstingSim.setName("Airtel");
		exstingSim.setMobile(mobile1);

		Sim updatingSim = new Sim();
		updatingSim.setId(id1);
		updatingSim.setName("Jio");
		updatingSim.setMobile(mobile1);

		when(simRepository.existsById(id1)).thenReturn(true);
		when(simRepository.findById(id1)).thenReturn(Optional.of(exstingSim));
		when(simRepository.save(exstingSim)).thenReturn(exstingSim);

		Sim updateSimById = simServiceImpl.updateSimById(id1, updatingSim);

		assertEquals(updatingSim.getId(), updateSimById.getId());
		assertEquals(updatingSim.getName(), updateSimById.getName());
		assertEquals(updatingSim.getMobile(), updateSimById.getMobile());

	}

	@Test
	@Order(7)
	void test_updateSimById_Faild() {
		Long id1 = 1l;

		Sim updatingSim = new Sim();
		updatingSim.setId(id1);
		updatingSim.setName("Jio");

		when(simRepository.existsById(id1)).thenReturn(false);
		when(simRepository.findById(id1)).thenReturn(Optional.empty());

		SimNotFoundException assertThrows2 = assertThrows(SimNotFoundException.class,
				() -> simServiceImpl.getSimById(id1));

		assertEquals("Sim ID Not Found :" + id1, assertThrows2.getMessage());

	}

	@Test
	@Order(8)
	void test_deleteById() {
		Long id1 = 1l;
		Mobile mobile1 = null;
		Sim sim1 = new Sim();
		sim1.setId(id1);
		sim1.setName("Airtel");
		sim1.setMobile(mobile1);

		when(simRepository.findById(id1)).thenReturn(Optional.of(sim1));
		Boolean deleteById = simServiceImpl.deleteById(id1);
		assertTrue(true);

		verify(simRepository, times(1)).deleteById(id1);

	}

	@Test
	@Order(9)
	void test_deleteById_faild() {
		Long id1 = 1L;

		when(simRepository.findById(id1)).thenReturn(Optional.empty());

		SimDeletionException assertThrows2 = assertThrows(SimDeletionException.class,
				() -> simServiceImpl.deleteById(id1));

		assertEquals("Sim ID Not Found :" + id1, assertThrows2.getMessage());

		verify(simRepository, never()).deleteById(id1);
	}

	@Test
	void test_getBySimName() {
		Long id1 = 1l;
		Mobile mobile1 = null;
		String name = "Airtel";
		Sim sim1 = new Sim();
		sim1.setId(id1);
		sim1.setName(name);
		sim1.setMobile(mobile1);

		when(simRepository.findByName(name)).thenReturn(sim1);
		assertEquals(name, simServiceImpl.getBySimName(name).getName());
	}
}
