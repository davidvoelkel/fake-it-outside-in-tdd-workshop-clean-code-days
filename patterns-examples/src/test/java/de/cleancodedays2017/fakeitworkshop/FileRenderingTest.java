package de.cleancodedays2017.fakeitworkshop;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class FileRenderingTest {
    @Test
    public void renderFiles() throws Exception {
        List<String> files = asList( "README.md",
                "test.txt",
                "index.html");
        assertThat(render(files)).isEqualTo(asList(
                " ------------------- ",
                "| Files:          3 |",
                "|         README.md |",
                "|          test.txt |",
                "|        index.html |",
                " ------------------- "
        ));
    }

    private List<String> render(List<String> files) {
        return null;
    }

}
