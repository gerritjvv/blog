package combinations

// Combinations returns all the possible combinations of a list of strings
// e.g Combinations([]{"a", "b", "c"}, 3) => [{a, b, c}, {a, a, c} ... ]
func Combinations(s []string, subsets [][]string) [][]string {

	subsets2 := subsets

	l := len(s)
	indices := make([]int, l)

    maxI := len(s) - 1

	for indices[0] < l {

		if indices[maxI] > maxI {

			for i := maxI; i > 0; i-- {

				if indices[i] > maxI {
					indices[i-1]++ // increment next
					indices[i] = 0 // reset
				}
			}


		} else {
			subsets2 = append(subsets2, copyFromIndices(s, indices))

			indices[maxI]++
		}

	}

	return subsets2
}

func copyFromIndices(s []string, indices []int) []string {
	s2 := make([]string, len(s))

	for i := 0; i < len(s); i++ {
		s2[i] = s[indices[i]]
	}

	return s2
}