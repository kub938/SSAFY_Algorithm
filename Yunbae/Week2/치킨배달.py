import sys
from itertools import combinations

n, m = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(n)]
result = sys.maxsize
house = []
chicken_store = []

for i in range(n):
    for j in range(n):
        if city[i][j] == 1:
            house.append([i,j])
        elif city[i][j] == 2:
            chicken_store.append([i,j])

for store in combinations(chicken_store, m):
    dist = 0
    for h in house:
        chicken_dis = sys.maxsize
        for j in range(m):
            chicken_dis = min(chicken_dis, abs(h[0] - store[j][0]) + abs(h[1] - store[j][1]))
        dist += chicken_dis
    result = min(result, dist)

print(result)
