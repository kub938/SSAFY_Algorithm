from collections import deque


def vaild_move(x, y):
    global N
    return 0 <= x < N and 0 <= y < N


N = int(input())
arr = []

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for i in range(N):
    temp = []
    tmp = input()
    for j in tmp:
        temp.append(int(j))
    arr.append(temp)

h = []
q = deque()
for i in range(N):
    for j in range(N):
        if arr[i][j] == 1:
            homes = 0
            q.append((i, j))
            while q:
                x, y = q.popleft()
                if vaild_move(x, y) and arr[x][y] == 1:
                    homes += 1
                    arr[x][y] = 0
                    for ix, iy in zip(dx, dy):
                        nx, ny = x + ix, y + iy
                        q.append((nx, ny))
            h.append(homes)

print(len(h))
h.sort()
for i in h:
    print(i)
