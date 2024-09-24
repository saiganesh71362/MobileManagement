package com.acruent.mobile.service;

import java.util.List;

import com.acruent.mobile.entity.Sim;
import com.acruent.mobile.globalexceptionhandle.SimCreationException;
import com.acruent.mobile.globalexceptionhandle.SimDeletionException;
import com.acruent.mobile.globalexceptionhandle.SimNotFoundException;
import com.acruent.mobile.globalexceptionhandle.SimUpdateException;

public interface SimService {

	Sim createNewSim(Sim sim) throws SimCreationException;

	Sim getSimById(Long id) throws SimNotFoundException;

	List<Sim> getAllSims();

	Sim updateSimById(Long id, Sim sim) throws SimUpdateException;

	Boolean deleteById(Long id) throws SimDeletionException;

	Sim getBySimName(String name) throws SimNotFoundException;

}
