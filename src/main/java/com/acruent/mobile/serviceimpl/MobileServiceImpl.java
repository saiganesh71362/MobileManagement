package com.acruent.mobile.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.acruent.mobile.appconstants.MobileAppConstants;
import com.acruent.mobile.entity.Mobile;
import com.acruent.mobile.entity.Sim;
import com.acruent.mobile.globalexceptionhandle.MobileDeletionException;
import com.acruent.mobile.globalexceptionhandle.MobileNotFoundException;
import com.acruent.mobile.globalexceptionhandle.MobileUpdateException;
import com.acruent.mobile.repository.MobileRepository;
import com.acruent.mobile.service.MobileService;

@Component
public class MobileServiceImpl implements MobileService {

	MobileRepository mobileRepository;

	public MobileServiceImpl(MobileRepository mobileRepository) {
		super();
		this.mobileRepository = mobileRepository;
	}

	@Override
	public Mobile createNewMobile(Mobile mobile) {

		// Validate mobile name
		if (mobile.getName() == null || mobile.getName().isEmpty()) {
			throw new IllegalArgumentException("Mobile name cannot be null or empty");
		}
//		InvalidMobileDataException

		// Validate mobile price
		if (mobile.getPrice() == null || mobile.getPrice() < 1000 || mobile.getPrice() > 10000000) {
			throw new IllegalArgumentException("Mobile price must be between 1000 and 10000000");
		}

		// Validate mobile release year
		if (mobile.getYear() == null) {
			throw new IllegalArgumentException("Mobile release year is required");
		}

		// Ensure each Sim entity has a reference to the parent Mobile
		if (mobile.getSim() != null && !mobile.getSim().isEmpty()) {
			for (Sim sim : mobile.getSim()) {
				sim.setMobile(mobile); // Set the Mobile reference in each Sim
			}
		}

		// Save the Mobile entity, which also saves associated Sim entities
		return mobileRepository.save(mobile);
	}

	@Override
	public Mobile getMobileById(Long id) throws MobileNotFoundException {

		Optional<Mobile> findById = mobileRepository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			throw new MobileNotFoundException(MobileAppConstants.MOBILE_ID_NOT_FOUND + id);
		}
	}

	@Override
	public List<Mobile> getAllMobiles() {

		return mobileRepository.findAll();
	}

	@Override
	public Mobile updateMobileById(Long id, Mobile mobile) throws MobileUpdateException {
		if (mobileRepository.existsById(id)) {
			Mobile existingMobile = mobileRepository.findById(id)
					.orElseThrow(() -> new MobileUpdateException(MobileAppConstants.MOBILE_ID_NOT_FOUND + id));

			existingMobile.setName(mobile.getName());
			existingMobile.setPrice(mobile.getPrice());
			existingMobile.setYear(mobile.getYear());

			return mobileRepository.save(existingMobile);

		} else {
			throw new MobileUpdateException(MobileAppConstants.MOBILE_ID_NOT_FOUND + id);
		}
	}

	@Override
	public Boolean deleteMobile(Long id) throws MobileDeletionException {

		Optional<Mobile> findById = mobileRepository.findById(id);
		if (findById.isPresent()) {
			mobileRepository.deleteById(id);
			return true;
		} else {
			throw new MobileDeletionException(MobileAppConstants.MOBILE_ID_NOT_FOUND + id);
		}

	}

}
