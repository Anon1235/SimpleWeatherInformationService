package co.nz.westpac.interview.simpleweatherinformationservice.util;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.regex.Pattern;

public class NumberMatcher extends BaseMatcher<String> {

    public String number;

    public static Matcher<String> matcher() {
        return new NumberMatcher();
    }

    @Override
    public boolean matches(Object number) {
        this.number = (String) number;
        Pattern pattern = Pattern.compile("^[-]?\\d+$");
        return pattern.matcher(this.number).find();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(number + " not match number");
    }

}