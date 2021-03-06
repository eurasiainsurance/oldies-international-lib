package com.lapsa.international.faces.country;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.lapsa.international.country.Country;

import tech.lapsa.javax.faces.commons.beans.localization.ListingBean;

@Named("Country")
@ApplicationScoped
public class CountryCDIBean implements ListingBean<Country> {

    @Override
    public Country[] getAll() {
	return Country.values();
    }

    @Override
    public Country[] getSelectable() {
	return Country.selectableValues();
    }

    @Override
    public Country[] getNonSelectable() {
	return Country.nonSelectableValues();
    }

    @Override
    public Country byName(String name) {
	return Country.valueOf(name);
    }
}
