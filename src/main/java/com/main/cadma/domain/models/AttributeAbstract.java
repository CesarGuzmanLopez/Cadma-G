package com.main.cadma.domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.main.cadma.interfaces.SmileFactory;

public abstract class AttributeAbstract<ATTR> {
    protected static SmileFactory smileFactory;
    private static final String REGEX_GET_NUMBERS = "([-+]?\\d*\\.?\\d*e?\\d*d?\\d+)";
    private ATTR value;
    private String name;
    private boolean found;

    protected AttributeAbstract(ATTR value, String name, boolean active) {
        this.value = value;
        this.name = name;
        this.found = active;

    }

    public abstract void lineAnalyze(String line);

    public static void setSmileFactory(SmileFactory smileFactory) {
        AttributeAbstract.smileFactory = smileFactory;
    }

    public ATTR getValue() {
        return value;
    }

    protected void found() {
        found = true;
    }

    public void setValue(ATTR value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can't be null");
        }
        this.value = value;
        found();
    }

    public boolean isFound() {
        return found && value != null;
    }

    public String getName() {
        return name;
    }

    public static Float numExtract(String line) {
        Matcher x = Pattern.compile(REGEX_GET_NUMBERS).matcher(line);
        x.find();
        String num = x.group(0);
        return Float.parseFloat(num);
    }

    public static List<Float> numExtractList(String line) {
        Matcher x = Pattern.compile(REGEX_GET_NUMBERS).matcher(line);
        List<Float> num = new ArrayList<>();
        while (x.find()) {
            num.add(Float.parseFloat(x.group(0)));
        }
        return num;

    }
}
