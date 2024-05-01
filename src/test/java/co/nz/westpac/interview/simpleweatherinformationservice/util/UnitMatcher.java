package co.nz.westpac.interview.simpleweatherinformationservice.util;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.regex.Pattern;

public class UnitMatcher extends BaseMatcher<String> {

    public String unit;

    public static Matcher<String> matcher() {
        return new UnitMatcher();
    }

    @Override
    public boolean matches(Object unit) {
        this.unit = (String) unit;
        Pattern pattern = Pattern.compile("C|F");
        return pattern.matcher(this.unit).find();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(unit + " not match C or F");
    }

}