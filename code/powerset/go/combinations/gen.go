package combinations

import (
	"sort"
	"strings"
)

func PowerSet(s []string) []*Set {
	return DistinctSets(MapSet(Combinations(s)))
}

func DistinctSets(subsets []*Set) []*Set {
	m := make(map[string]*Set)

	var vs []*Set

	for _, set := range subsets {
		// its annoying that go doesn't allow us to use Set and a struct,
		// I've gone for a dumb string impl here, there are other libs out there that use code gen but I've not had the patience to try them out for this blog
		m[set.String()] = set
	}

	for _, v := range m {
		vs = append(vs, v)
	}

	return vs
}

func MapSet(subsets [][]string) []*Set {

	var subsets2 []*Set

	for _, subset := range subsets {
		set := Set{}
		set.Add(subset)

		subsets2 = append(subsets2, &set)
	}

	return subsets2
}

// Combinations returns all the possible combinations of a list of strings
// e.g Combinations([]{"a", "b", "c"}, 3) => [{a, b, c}, {a, a, c} ... ]
func Combinations(s []string) [][]string {

	var subsets [][]string

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
			subsets = append(subsets, copyFromIndices(s, indices))

			indices[maxI]++
		}

	}

	return subsets
}

type Set struct {
	elements map[string]bool
}

func (s *Set) Add(elements []string) {
	if s.elements == nil {
		s.elements = make(map[string]bool)
	}

	for _, e := range elements {
		s.elements[e] = true
	}
}

func (s *Set) asList() []string {
	var ks []string
	for k := range s.elements {
		ks = append(ks, k)
	}

	sort.Strings(ks)

	return ks
}

func (s1 *Set) Equals(s2 *Set) bool {
	return arraysEquals(s1.asList(), s2.asList())
}

func (s *Set) String() string {
	return "{" + strings.Join(s.asList(), ",") + "}"
}

func NewSet(l []string) *Set {
	s := Set{}
	s.Add(l)
	return &s
}

func copyFromIndices(s []string, indices []int) []string {
	s2 := make([]string, len(s))

	for i := 0; i < len(s); i++ {
		s2[i] = s[indices[i]]
	}

	return s2
}

func arraysEquals(s1 []string, s2 []string) bool {

	if len(s1) != len(s2) {
		return false
	}

	for i := 0; i < len(s1); i++ {
		if s1[i] != s2[i] {
			return false
		}
	}

	return true
}
