package de.cleancodedays2017.fakeitworkshop;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class GreenbarPatternsTest {
    @Test
    public void formatDate() throws Exception {
        assertThat(format(LocalDate.of(2017, 6, 21)))
                            .isEqualTo("2017-06-21");

    }

    private String format(LocalDate of) {
        return null;
    }
}
