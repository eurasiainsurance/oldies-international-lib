package test.phone;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.lapsa.international.country.Country;
import com.lapsa.international.phone.CountryCode;
import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.logging.MyLogger;

public class TestPhoneNumber {

    private static MyLogger logger = MyLogger.getDefault();

    private static void printFormat(final PhoneNumber a) {
	logger.INFO.log("Formatted: '%1$s' Plain: '%2$s'", //
		a.getFormatted(), // $1
		a.getPlain() // $2
	);
	logger.INFO.log(
		"Country: \"%1$s\" Country number: +%2$s Area code: \"%3$s\" Phone number: \"%4$s\"", //
		MyOptionals.of(a.getCountryCode()) //
			.map(CountryCode::name) //
			.map(Country::forAlpha2Code) //
			.map(Localized::regular) //
			.orElse(""), // $1
		MyOptionals.of(a.getCountryCode()) //
			.map(CountryCode::getPhoneCode) //
			.orElse(""), // $2
		a.getAreaCode(), // $3
		a.getPlain() // $4
	);
    }

    private void testStrict(final String value, final PhoneNumber expecting, final String expectingFormat) {
	final PhoneNumber a = PhoneNumber.of(value);
	printFormat(a);
	assertThat(a, allOf(not(nullValue()), is(equalTo(expecting))));
	assertThat(a.getFormatted(), allOf(not(nullValue()), is(equalTo(expectingFormat))));
    }

    private void testNonStrict(final String value) {
	final PhoneNumber a = PhoneNumber.assertValid(value);
	printFormat(a);
	assertThat(a, not(nullValue()));
	assertThat(a.getFormatted(), allOf(not(nullValue()), is(equalTo(value))));
    }

    @Test
    public void testStrictFormat1() {
	testStrict(" 8 (7272)-530721 ", PhoneNumber.of(CountryCode.KZ, "7272", "530721"), "+7 (7272) 530721");
    }

    @Test
    public void testStrictFormat2() {
	testStrict(" 8 (963)-777-79 79", PhoneNumber.of(CountryCode.RU, "963", "7777979"), "+7 (963) 7777979");
    }

    @Test
    public void testStrictFormat3() {
	testStrict(" 8 (727)2-530721 ", PhoneNumber.of(CountryCode.KZ, "727", "2530721"), "+7 (727) 2530721");
    }

    @Test
    public void testStrictFormat4() {
	testStrict(" 8 (701)937-7979", PhoneNumber.of(CountryCode.KZ, "701", "9377979"), "+7 (701) 9377979");
    }

    @Test
    public void testStrictFormat5() {
	testStrict("001 268-464-1234", PhoneNumber.of(CountryCode.AG, "268", "4641234"), "+1 (268) 4641234");
    }

    @Test
    public void testStrictFormat6() {
	testStrict("00007(701)9377979", PhoneNumber.of(CountryCode.KZ, "701", "9377979"), "+7 (701) 9377979");
    }

    @Test
    public void testStrictFormat7() {
	testStrict("+7(701)9377979", PhoneNumber.of(CountryCode.KZ, "701", "9377979"), "+7 (701) 9377979");
    }

    @Test
    public void testStrictFormat8() {
	testStrict("+77019377979", PhoneNumber.of(CountryCode.KZ, "701", "9377979"), "+7 (701) 9377979");
    }

    @Test
    public void testStrictFormat9() {
	testStrict("+380 (93) 1828087", PhoneNumber.of(CountryCode.UA, "93", "1828087"), "+380 (93) 1828087");
    }

    @Test
    public void testStrictFormat10() {
	testStrict("+37368346711", PhoneNumber.of(CountryCode.MD, "68", "346711"), "+373 (68) 346711");
    }

    @Test
    public void testStrictFormat11() {
	testStrict("375 17 566 17 56", PhoneNumber.of(CountryCode.BY, "17", "5661756"), "+375 (17) 5661756");
    }

    @Test
    public void testNonStrictFormat1() {
	testNonStrict("+8737368346711");
    }

    @Test
    public void testTwoWays1() {
	final String source = "+86 (4) 7124545";
	final PhoneNumber p = PhoneNumber.assertValid(source);
	final String target = p.getFormatted();
	assertThat(target, equalTo(source));
    }

    @Test
    public void testTwoWays2() {
	final String source = "+370 (07) 47124545";
	final PhoneNumber p = PhoneNumber.assertValid(source);
	final String target = p.getFormatted();
	assertThat(target, equalTo(source));
    }

    @Test
    public void testStrictParseFormat() {
	final String[] numbers = new String[] { " 8 (7272)-530721 ", "+1 268-464-1234", "+56(7)9377979",
		"494357019377979",
		"87272530721" };

	final String[] formatted = new String[] { "+7 (7272) 530721", "+1 (268) 4641234", "+56 (7) 9377979",
		"+49 (43) 57019377979", "+7 (727) 2530721" };

	for (int i = 0; i < numbers.length; i++) {
	    final String num = numbers[i];
	    final String frm = formatted[i];
	    final PhoneNumber a = PhoneNumber.assertValid(num);
	    printFormat(a);
	    assertThat(a, not(nullValue()));
	    assertThat(a.getFormatted(), allOf(not(nullValue()), is(equalTo(frm))));
	}
    }

}
