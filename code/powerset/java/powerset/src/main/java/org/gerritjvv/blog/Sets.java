package org.gerritjvv.blog;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Sets {

    /**
     * Generate the powersets of a set.
     * <pre>
     *
     * For a set S, the power set P is:
     *   c = combinations( S )
     *   subsets = distinct( map(Set, c ) )
     *
     * </pre>
     */
    public static <T> Stream<Set<T>> powerSets(T[] set) {

        return combinations(set).stream().map(Sets::asSet)
                .distinct();
    }

    /**
     * Returns all the possible combinations of a list of strings
     * {"a", "b", "c"}  => [{a, b, c}, {a, a, c} ... ]
     */
    public static <T> List<T[]> combinations(T[] set) {
        final int[] indices = new int[set.length];

        final int l = set.length;

        List<T[]> subSets = new ArrayList<>();
        int maxI = set.length - 1;

        while (indices[0] < l) {
            if (indices[maxI] > maxI) {

                for (int i = maxI; i > 0; i--) {
                    if (indices[i] > maxI) {
                        indices[i - 1]++; //increment next
                        indices[i] = 0; //reset
                    }
                }
            } else {
                subSets.add(copyFromIndices(set, indices));
                indices[maxI]++;
            }
        }

        return subSets;
    }

    public static <T> Set<T> asSet(T[] set) {
        Set<T> hashSet = new HashSet<>();
        Collections.addAll(hashSet, set);

        return hashSet;
    }

    private static <T> T[] copyFromIndices(T[] set, int[] indices) {

        T[] subSet = (T[]) Array.newInstance(set[0].getClass(), indices.length);

        for (int i = 0; i < indices.length; i++) {
            subSet[i] = set[indices[i]];
        }

        return subSet;
    }
}
