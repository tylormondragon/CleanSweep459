package com.cleansweep; /**
 * Class: SE<xxx> - <descrption>
 * Author: Raquib Talukder
 **/


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class ControlSystemTest {

    @Test
    public void Test(){
        System.out.println("test");
        Assertions.assertThat("test").isEqualTo("test");
    }
}
