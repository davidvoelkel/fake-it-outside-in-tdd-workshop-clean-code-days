package de.cleancodedays2017.fakeitworkshop;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class DiamondTest {

    private Diamond diamond;

    @Before
    public void setUp() throws Exception {
        diamond = new Diamond();
    }

//    @Test
//    public void diamondA() throws Exception {
//        assertThat(diamond.diamond("A"))
//                          .isEqualTo(asList("A"));
//    }
//
//    @Test
//    public void diamondB() throws Exception {
//        assertThat(diamond.diamond("B"))
//                          .isEqualTo(asList("_A_",
//                                            "B_B",
//                                            "_A_"));
//    }

    @Test
    public void diamondC() throws Exception {
        assertThat(diamond.diamond("C"))
                          .isEqualTo(asList(
                                  "__A__",
                                  "_B_B_",
                                  "C___C",
                                  "_B_B_",
                                  "__A__"));
    }

//    @Test
//    public void diamond2() throws Exception {
//        assertThat(diamond.diamond("D"))
//                .containsExactly("___A___",
//                                 "__B_B__",
//                                 "_C___C_",
//                                 "D_____D",
//                                 "_C___C_",
//                                 "__B_B__",
//                                 "___A___"
//        );
//    }

}