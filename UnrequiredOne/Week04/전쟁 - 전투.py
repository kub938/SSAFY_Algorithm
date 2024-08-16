from collections import deque


def valid_move(x, y):
    global N, M
    return 0 <= x < N and 0 <= y < M


def bfs(i, j, team):
    global arr
    power = 0
    queue = deque()
    queue.append((i, j))
    while queue:
        x, y = queue.popleft()
        if arr[y][x] == '0':
            continue
        arr[y][x] = '0'
        power += 1
        for ix, iy in zip(dx, dy):
            nx, ny = x + ix, y + iy
            if valid_move(nx, ny) and arr[ny][nx] == team:
                queue.append((nx, ny))
    return power


N, M = map(int, input().split())
arr = [list(input()) for _ in range(M)]
powers = [0, 0]

dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]

for i in range(N):
    for j in range(M):
        if arr[j][i] == '0':
            continue

        team = arr[j][i]
        temp = bfs(i, j, team)
        if team == 'W':
            powers[0] += temp * temp
        else:
            powers[1] += temp * temp

print(powers[0], powers[1])
