package com.lapsa.phone;

public interface PhoneNumberFactory {
    PhoneNumber create(CountryCode country, String number) throws PhoneFormatException;

    PhoneNumber parse(String format) throws PhoneFormatException;

    PhoneNumber parse(String format, CountryCode defaultCountryCode) throws PhoneFormatException;

    PhoneNumber parse(String format, boolean strict) throws PhoneFormatException;

    PhoneNumber parse(String format, CountryCode defaultCountryCode, boolean strict) throws PhoneFormatException;

    CountryCode identifyCountryCode(String number, boolean force) throws PhoneFormatException;
}
