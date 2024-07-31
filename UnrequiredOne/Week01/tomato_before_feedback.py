import heapq


day = 0

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

n, m = map(int, input().split())
arr = [[0 for _ in range(n)] for _ in range(m)]
q = []

for i in range(m):
    arr[i] = list(map(int, input().split()))
    for j in range(n):
        if arr[i][j] == 1:
            q.append((0, arr[i][j], i, j))

while q:
    d, is_good_tomato, x, y = heapq.heappop(q)
    for i, j in zip(dx, dy):
        nx, ny = x+i, y+j
        if 0 <= nx < m and 0 <= ny < n and arr[nx][ny] != -1:
            nd = d
            if arr[nx][ny] == 1:
                day = nd
                continue
            elif is_good_tomato == 1:
                nd = d+1
                arr[nx][ny] = 1
            heapq.heappush(q, (nd, is_good_tomato, nx, ny))

for i in range(m):
    if 0 in arr[i]:
        day = -1
        break

print(day)
