package com.acruent.mobile.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.acruent.mobile.appconstants.MobileAppConstants;
import com.acruent.mobile.entity.Sim;
import com.acruent.mobile.globalexceptionhandle.SimDeletionException;
import com.acruent.mobile.globalexceptionhandle.SimNotFoundException;
import com.acruent.mobile.globalexceptionhandle.SimUpdateException;
import com.acruent.mobile.repository.SimRepository;
import com.acruent.mobile.service.SimService;

@Component
public class SimServiceImpl implements SimService {

	SimRepository simRepository;

	public SimServiceImpl(SimRepository simRepository) {
		super();
		this.simRepository = simRepository;
	}

	@Override
	public Sim createNewSim(Sim sim) {
		if (sim.getName() == null || sim.getName().isEmpty()) {
			throw new IllegalArgumentException(MobileAppConstants.SIM_NULL_OR_EMPTY); // NoSimNameException
		}

		return simRepository.save(sim);
	}

	@Override
	public Sim getSimById(Long id) throws SimNotFoundException {
		Optional<Sim> findById = simRepository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			throw new SimNotFoundException(MobileAppConstants.SIM_ID_NOT_FOUND + id);
		}
	}

	@Override
	public List<Sim> getAllSims() {

		return simRepository.findAll();
	}

	@Override
	public Sim updateSimById(Long id, Sim sim) throws SimUpdateException {
		if (simRepository.existsById(id)) {
			Sim existingSim = simRepository.findById(id)
					.orElseThrow(() -> new SimUpdateException(MobileAppConstants.SIM_ID_NOT_FOUND + id));

			existingSim.setName(sim.getName());
			return simRepository.save(existingSim);

		} else {
			throw new SimUpdateException(MobileAppConstants.SIM_ID_NOT_FOUND + id);
		}
	}

	@Override
	public Boolean deleteById(Long id) throws SimDeletionException {
		Optional<Sim> findById = simRepository.findById(id);
		if (findById.isPresent()) {
			simRepository.deleteById(id);
			return true;
		} else {
			throw new SimDeletionException(MobileAppConstants.SIM_ID_NOT_FOUND + id);
		}

	}

	@Override
	public Sim getBySimName(String name) {

		return simRepository.findByName(name);

	}

}
