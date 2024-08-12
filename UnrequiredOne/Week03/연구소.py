from itertools import combinations
from collections import deque

N, M = map(int, input().split())

arr = []
safe_area = []
virus = []

dx = [0, 0, 1, -1]
dy = [-1, 1, 0, 0]


def valid_move(ax, ay):
    global N, M
    return 0 <= ax < N and 0 <= ay < M


for i in range(N):
    temp = list(map(int, input().split()))
    arr.append(temp)
    for j in range(M):
        if arr[i][j] == 0:
            safe_area.append((i, j))
        elif arr[i][j] == 2:
            virus.append((i, j))

max_safe = 0

arr_temp = [[0 for _ in range(M)] for _ in range(N)]
for i in range(N):
    for j in range(M):
        arr_temp[i][j] = arr[i][j]

for c in combinations(safe_area, 3):
    for a, b in c:
        arr_temp[a][b] = 1

    contained = []
    contained.extend(virus)

    q = deque()
    q.extend(virus)
    safe = 0

    visited = [[0 for _ in range(M)] for _ in range(N)]
    while q:
        x, y = q.popleft()
        arr_temp[x][y] = 2
        visited[x][y] = 1
        for ix, iy in zip(dx, dy):
            nx = x + ix
            ny = y + iy
            if valid_move(nx, ny) and visited[nx][ny] != 1 and arr_temp[nx][ny] == 0:
                q.append((nx, ny))
                contained.append((nx, ny))

    for i in range(N):
        for j in range(M):
            if arr_temp[i][j] == 0:
                safe += 1

    while contained:
        a, b = contained.pop()
        arr_temp[a][b] = 0

    for a, b in c:
        arr_temp[a][b] = 0

    max_safe = max(max_safe, safe)

print(max_safe)
