package com.lapsa.international.localization;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lapsa.international.InternationalLocalizedElement;

public enum LocalizationLanguage implements InternationalLocalizedElement {
    RUSSIAN("ru"), // русский
    ENGLISH("en", Locale.ENGLISH), // английский
    KAZAKH("kk"), // казахский
    //
    ;

    private final String tag;
    private final Locale locale;

    //

    private LocalizationLanguage(String tag) {
	this.tag = Objects.requireNonNull(tag);
	this.locale = Locale.forLanguageTag(tag);
    }

    private LocalizationLanguage(String tag, Locale locale) {
	this.tag = Objects.requireNonNull(tag);
	this.locale = Objects.requireNonNull(locale);
    }

    //

    public static final Stream<LocalizationLanguage> valuesStream() {
	return Stream.of(values());
    }

    //

    private static final LocalizationLanguage DEFAULT = LocalizationLanguage.ENGLISH;

    private final static Map<String, LocalizationLanguage> LANGUAGES_BY_TAG = valuesStream() //
	    .collect(Collectors.collectingAndThen(
		    Collectors.toMap(LocalizationLanguage::getTag, Function.identity()),
		    Collections::unmodifiableMap));

    //

    public static LocalizationLanguage byTag(String tag) {
	return LANGUAGES_BY_TAG.get(tag);
    }

    public static LocalizationLanguage byLocale(Locale locale) {
	return byTag(locale.getLanguage());
    }

    public static LocalizationLanguage byLocalePriorityListOrDefault(List<Locale> locales) {
	return locales.stream() //
		.filter(Predicates.objectNotNull()) //
		.map(LocalizationLanguage::byLocale) //
		.filter(Predicates.objectNotNull()) //
		.findFirst() //
		.orElse(_orDefault(null));
    }

    public static LocalizationLanguage byTagOrDefault(String tag) {
	return _orDefault(byTag(tag));
    }

    public static LocalizationLanguage byLocaleOrDefault(Locale locale) {
	return _orDefault(byLocale(locale));
    }

    public static LocalizationLanguage getDefault() {
	return _orDefault(null);
    }

    private static LocalizationLanguage _orDefault(final LocalizationLanguage lang) {
	LocalizationLanguage result = lang;
	if (result == null)
	    result = byLocale(Locale.getDefault());
	if (result == null)
	    result = DEFAULT;
	return result;
    }

    // GENERATED

    public String getTag() {
	return tag;
    }

    public Locale getLocale() {
	return locale;
    }
}
