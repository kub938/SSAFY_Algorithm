import heapq

direct = [(-1, 0), (1, 0), (0, -1), (0, 1)]


def is_valid_move(R, C):
    return 0 <= R < r and 0 <= C < c


r, c = map(int, input().split())
arr = []
init_r, init_c = -1, -1
fires = []

for i in range(r):
    temp = list(input())
    for j in range(c):
        if temp[j] == 'J':
            init_r, init_c = i, j
            temp[j] = '.'
        elif temp[j] == 'F':
            fires.append((i, j))
    arr.append(temp)

visited = [[0]*(c+1) for _ in range(r+1)]

q = [(0, init_r, init_c)]
prev_time = -1
answer = -1
while q:
    time, cur_r, cur_c = heapq.heappop(q)

    if arr[cur_r][cur_c] == 'F':
        continue

    if cur_r in (0, r-1) or cur_c in (0, c-1):
        answer = time
        break

    for dr, dc in direct:
        nr, nc = cur_r + dr, cur_c + dc
        if is_valid_move(nr, nc) and arr[nr][nc] == '.' and visited[nr][nc] != 1:
            heapq.heappush(q, (time+1, nr, nc))
            visited[nr][nc] = 1

    if prev_time != time:
        temp = []
        for fire_r, fire_c in fires:
            for dr, dc in direct:
                nr, nc = fire_r+dr, fire_c+dc
                if is_valid_move(nr, nc) and arr[nr][nc] == '.':
                    arr[nr][nc] = 'F'
                    temp.append((nr, nc))
        fires = temp
        prev_time = time

print(answer + 1 if answer != -1 else "IMPOSSIBLE")
