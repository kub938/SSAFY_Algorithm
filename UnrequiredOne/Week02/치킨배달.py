from itertools import combinations


def abs_dist(a, b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])


N, M = map(int, input().split())

arr = []
stores = []
homes = []

for j in range(N):
    temp = list(map(int, input().split()))
    for i in range(N):
        if temp[i] == 1:
            homes.append([j, i])
        elif temp[i] == 2:
            stores.append([j, i])
    arr.append(temp)

length = len(stores)

dist = N * N * N

for c in combinations(stores, M):
    d_s = 0
    for home in homes:
        d = min(abs_dist(home, i) for i in c)
        d_s += d
    if d_s < dist:
        dist = d_s

print(dist)
