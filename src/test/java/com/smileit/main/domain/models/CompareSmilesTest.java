
package com.smileit.main.domain.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.main.common.methods.CompareSmiles;

public class CompareSmilesTest {
     @Test
     void compareSmilesTest(){
            assertTrue(CompareSmiles.compareSmiles("C", "C"));
            assertTrue(CompareSmiles.compareSmiles("C=C", "cc"));
            assertTrue(CompareSmiles.compareSmiles("C=C", "C=C"));
            assertTrue(CompareSmiles.compareSmiles("H", "[H]"));
            assertFalse(CompareSmiles.compareSmiles("O", "C"));
     }
}
