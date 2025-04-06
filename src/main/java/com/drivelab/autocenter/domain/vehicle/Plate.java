package com.drivelab.autocenter.domain.vehicle;

import com.drivelab.autocenter.domain.DomainException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Embeddable
public class Plate {

    public static final String PATTERN = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}";

    @Column(name = "plate")
    private String value;

    protected Plate() {
        //Public empty constructor needed by ORM
    }

    public Plate(@NonNull String value) {
        setValue(value);
    }

    private void setValue(@NonNull String value) {
        Pattern pattern = Pattern.compile(PATTERN, CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new DomainException("Plate " + value + " is not valid");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
