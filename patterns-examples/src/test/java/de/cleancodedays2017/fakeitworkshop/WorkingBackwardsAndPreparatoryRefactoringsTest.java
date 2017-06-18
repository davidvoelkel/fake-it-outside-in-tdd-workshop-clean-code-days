package de.cleancodedays2017.fakeitworkshop;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class WorkingBackwardsAndPreparatoryRefactoringsTest {
    @Test
    public void sum() throws Exception {
        assertThat(sum("1,2,3")).isEqualTo(6);
    }

    @Test
    public void toCharacters() throws Exception {
        assertThat(toCharacters(asList(0, 1, 2)))
                .isEqualTo("a,b,c");
    }

    private String toCharacters(List<Integer> characterIndices) {
        return null;
    }

    private int sum(String commaSeparatedSummands) {
        return 0;
    }
}
