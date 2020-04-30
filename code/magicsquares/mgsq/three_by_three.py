"""
Solves a 3x3 square programmatically.

It is not meant to be a full blown solution for magic squares, but rather a writeup
of my thoughts on how it can be solved.
"""
import statistics


def make_pairs(I, mid):
    """
    We take pairs as [ [9, 1], [8, 2], [7, 3], [6, 4]]
    :param I:
    :param mid:
    :return:
    """
    h = 0
    t = len(I) - 1
    pairs = []
    while h < mid-1:
        pairs.append([I[h], I[t]])
        h += 1
        t -= 1

    return pairs


def squares(n):
    I = [x for x in range(1, n * n + 1)]
    cols = n
    mid = statistics.median(I)

    print(f"I: {I}")
    print(f"cols: {cols}")
    print(f"mid: {mid}")

    pairs = make_pairs(I, mid)
    print(f"pairs: {pairs}")

    # the pairs are taken from the left and rigt of mid
    # so that the length is mid-1
    assert len(pairs) == mid-1, f"len(pairs) = {len(pairs)} mid-1 = {mid-1}"
    assert len(pairs[0]) == cols-1


if __name__ == '__main__':
    squares(3)
