package org.gerritjvv.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SetsTest {

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

    public SetsTest(TestRecord testRecord) {
        this.testRecord = testRecord;
    }

    @Test
    public void test() {
        List<Set<String>> subSets = Sets.powerSets(testRecord.data).collect(Collectors.toList());

        assertEquals(testRecord.expected.size(), subSets.size());


        subSets.forEach(s -> {
           assertTrue(testRecord.expected.contains(s));
        });
    }

    public static final class TestRecord {
        String[] data;
        List<Set<String>> expected;

        public TestRecord(String[][] expected, String... data) {
            this.data = data;

            List<Set<String>> l = new ArrayList<>();
            for (int i = 0; i < expected.length; i++) {
                l.add(Sets.asSet(expected[i]));
            }

            this.expected = l;
        }
    }
}
