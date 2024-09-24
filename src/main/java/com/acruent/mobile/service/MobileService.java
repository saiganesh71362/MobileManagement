package com.acruent.mobile.service;

import java.util.List;

import com.acruent.mobile.entity.Mobile;
import com.acruent.mobile.globalexceptionhandle.MobileCreationException;
import com.acruent.mobile.globalexceptionhandle.MobileDeletionException;
import com.acruent.mobile.globalexceptionhandle.MobileNotFoundException;
import com.acruent.mobile.globalexceptionhandle.MobileUpdateException;

public interface MobileService {

	Mobile createNewMobile(Mobile mobile) throws MobileCreationException;

	Mobile getMobileById(Long id) throws MobileNotFoundException ;

	List<Mobile> getAllMobiles();

	Mobile updateMobileById(Long id, Mobile mobile) throws MobileUpdateException ;

	Boolean deleteMobile(Long id) throws MobileDeletionException ;

}
