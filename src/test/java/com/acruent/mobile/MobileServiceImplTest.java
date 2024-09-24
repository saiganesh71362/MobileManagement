package com.acruent.mobile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.acruent.mobile.appconstants.MobileAppConstants;
import com.acruent.mobile.entity.Mobile;
import com.acruent.mobile.globalexceptionhandle.MobileDeletionException;
import com.acruent.mobile.globalexceptionhandle.MobileNotFoundException;
import com.acruent.mobile.globalexceptionhandle.MobileUpdateException;
import com.acruent.mobile.repository.MobileRepository;
import com.acruent.mobile.serviceimpl.MobileServiceImpl;

@SpringBootTest
class MobileServiceImplTest {

	@Mock
	MobileRepository mobileRepository;

	@InjectMocks
	MobileServiceImpl mobileServiceImpl;

	@Test
	@Order(1)
	void test_createNewMobile() {
		Long id = 1l;
		Mobile mobile1 = new Mobile();
		mobile1.setId(id);
		mobile1.setName("iPhone 13");
		mobile1.setPrice(99999.99);
		mobile1.setYear(2021);

		when(mobileRepository.save(mobile1)).thenReturn(mobile1);
		Mobile createNewMobile = mobileServiceImpl.createNewMobile(mobile1);

		assertEquals(mobile1.getId(), createNewMobile.getId());
		assertEquals(mobile1.getName(), createNewMobile.getName());
		assertEquals(mobile1.getPrice(), createNewMobile.getPrice());
		assertEquals(mobile1.getYear(), createNewMobile.getYear());

		verify(mobileRepository, times(1)).save(mobile1);
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

		when(mobileRepository.findById(id)).thenReturn(Optional.of(mobile1));

		Mobile mobileById = mobileServiceImpl.getMobileById(id);

		assertEquals(mobile1, mobileById);
	}

	@Test
	@Order(3)
	void test_getMobileById_notFound() {
		Long id = null;
		Mobile mobile1 = new Mobile();
		mobile1.setId(id);
//		mobile1.setName("iPhone 13");
//		mobile1.setPrice(99999.99);
//		mobile1.setYear(2021);

		when(mobileRepository.findById(id)).thenReturn(Optional.empty());

		MobileNotFoundException assertThrows2 = assertThrows(MobileNotFoundException.class,
				() -> mobileServiceImpl.getMobileById(id));

		assertEquals(MobileAppConstants.MOBILE_ID_NOT_FOUND + id, assertThrows2.getMessage());
	}

	@Test
	@Order(4)
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

		when(mobileRepository.findAll()).thenReturn(mobiles);

		List<Mobile> allMobiles = mobileServiceImpl.getAllMobiles();
		assertEquals(3, allMobiles.size());

	}

	@Test
	@Order(5)
	void test_updateMobileById() {

		Long id = 1l;
		Mobile existMobile = new Mobile();
		existMobile.setId(id);
		existMobile.setName("iPhone 13");
		existMobile.setPrice(99999.99);
		existMobile.setYear(2021);

		Mobile updateMobile = new Mobile();
		updateMobile.setId(id);
		updateMobile.setName("iPhone 13");
		updateMobile.setPrice(99999.99);
		updateMobile.setYear(2021);

		when(mobileRepository.existsById(id)).thenReturn(true);

		when(mobileRepository.findById(id)).thenReturn(Optional.of(existMobile));

		when(mobileRepository.save(existMobile)).thenReturn(existMobile);

		Mobile updateMobileById = mobileServiceImpl.updateMobileById(id, updateMobile);

		assertEquals(updateMobile.getId(), updateMobileById.getId());
		assertEquals(updateMobile.getName(), updateMobileById.getName());
		assertEquals(updateMobile.getPrice(), updateMobileById.getPrice());
		assertEquals(updateMobile.getYear(), updateMobileById.getYear());

	}

	@Test
	@Order(6)
	void test_updateMobileById_faild() {

		Long id = 1l;

		Mobile updateMobile = new Mobile();
		updateMobile.setId(id);
		updateMobile.setName("iPhone 13");
		updateMobile.setPrice(99999.99);
		updateMobile.setYear(2021);

		when(mobileRepository.existsById(id)).thenReturn(false);

		MobileUpdateException assertThrows2 = assertThrows(MobileUpdateException.class,
				() -> mobileServiceImpl.updateMobileById(id, updateMobile));

		assertEquals(MobileAppConstants.MOBILE_ID_NOT_FOUND + id, assertThrows2.getMessage());

	}

	@Test
	@Order(7)
	void test_deleteMobile() {

		Long id = 1l;
		Mobile mobile1 = new Mobile();
		mobile1.setId(id);
		mobile1.setName("iPhone 13");
		mobile1.setPrice(99999.99);
		mobile1.setYear(2021);

		when(mobileRepository.findById(id)).thenReturn(Optional.of(mobile1));

		Boolean deleteMobile = mobileServiceImpl.deleteMobile(id);

//		assertTrue(deleteMobile);

		assertEquals(true, deleteMobile);

		verify(mobileRepository, times(1)).deleteById(id);
	}

	@Test
	@Order(8)
	void test_deleteMobile_faild() {
		Long id = 1l;
		Mobile mobile1 = new Mobile();
		mobile1.setId(id);
		mobile1.setName("iPhone 13");
		mobile1.setPrice(99999.99);
		mobile1.setYear(2021);

		when(mobileRepository.findById(id)).thenReturn(Optional.empty());

		MobileDeletionException exception = assertThrows(MobileDeletionException.class,
				() -> mobileServiceImpl.deleteMobile(id));

		assertEquals(MobileAppConstants.MOBILE_ID_NOT_FOUND + id, exception.getMessage());
	}
}
