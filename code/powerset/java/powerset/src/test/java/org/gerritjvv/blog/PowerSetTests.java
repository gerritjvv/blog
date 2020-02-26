package org.gerritjvv.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the powerset is calculated correctly in Set
 */
@RunWith(Parameterized.class)
public class PowerSetTests {

    @Parameterized.Parameters
    public static Collection<TestRecord> data() {
        return Arrays.asList(
                new TestRecord(
                        new String[][]{
                                {"a"},
                                {"b"},
                                {"c"},
                                {"a", "b"},
                                {"a", "c"},
                                {"a", "b", "c"},
                                {"b", "c"},
                        },
                        "a",
                        "b",
                        "c"
                )
        );
    }

    TestRecord testRecord;

    public PowerSetTests(TestRecord testRecord) {
        this.testRecord = testRecord;
    }

    @Test
    public void testSetsPowerSets() {
        List<Set<String>> subSets = Sets.powerSets(testRecord.data).collect(Collectors.toList());

        assertEquals(testRecord.expected.size(), subSets.size());


        subSets.forEach(s -> assertTrue(testRecord.expected.contains(s)));
    }

    public static final class TestRecord {
        String[] data;
        List<Set<String>> expected;

        public TestRecord(String[][] expected, String... data) {
            this.data = data;

            List<Set<String>> l = new ArrayList<>();
            for (String[] strings : expected) {
                l.add(Sets.asSet(strings));
            }

            this.expected = l;
        }
    }
}
