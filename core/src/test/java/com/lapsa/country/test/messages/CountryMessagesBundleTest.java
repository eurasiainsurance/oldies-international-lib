package com.lapsa.country.test.messages;

import com.lapsa.country.Country;

public class CountryMessagesBundleTest extends EnumTypeMessagesBundleTest<Country>{

    @Override
    protected Country[] getAllEnumValues() {
	return Country.values();
    }

    @Override
    protected String getBundleBaseName() {
	return Country.BUNDLE_BASENAME;
    }
}
