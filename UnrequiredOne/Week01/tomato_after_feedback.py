from collections import deque


day = 0

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

n, m = map(int, input().split())
arr = [[0 for _ in range(n)] for _ in range(m)]
q = deque()

num_of_full_tomato = n * m
num_of_gotten_tomato = 0

for i in range(m):
    arr[i] = list(map(int, input().split()))
    for j in range(n):
        if arr[i][j] == 1:
            q.append((0, arr[i][j], i, j))
            num_of_gotten_tomato += 1
        elif arr[i][j] == -1:
            num_of_full_tomato -= 1

while q:
    d, is_good_tomato, x, y = q.popleft()
    nd = 0
    for i, j in zip(dx, dy):
        nx, ny = x+i, y+j
        if 0 <= nx < m and 0 <= ny < n and arr[nx][ny] != -1:
            if arr[nx][ny] == 1:
                day = d
                continue
            elif is_good_tomato == 1:
                nd = d+1
                arr[nx][ny] = 1
                num_of_gotten_tomato += 1
            q.append((nd, is_good_tomato, nx, ny))

if num_of_full_tomato != num_of_gotten_tomato:
    day = -1

print(day)
