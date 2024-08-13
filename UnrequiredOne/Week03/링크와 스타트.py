from itertools import combinations


N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

entry = [i for i in range(N)]

min_diff = 98754321
for i in range(2, N//2 + 1):
    for c in combinations(entry, i):
        enemy = []
        for j in range(N):
            if j not in c:
                enemy.append(j)

        li_power = 0
        enemy_power = 0
        for e in combinations(c, 2):
            a, b = e
            li_power += arr[a][b] + arr[b][a]
        for e in combinations(enemy, 2):
            a, b = e
            enemy_power += arr[a][b] + arr[b][a]

        diff = abs(li_power - enemy_power)
        if min_diff > diff:
            min_diff = diff

        if min_diff == 0:
            break

    if min_diff == 0:
        break

print(min_diff)
