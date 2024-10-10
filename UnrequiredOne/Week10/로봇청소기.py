def is_valid_move(x, y):
    return 0 <= x < M and 0 <= y < N


direct = [(0, -1), (1, 0), (0, 1), (-1, 0)] # 북 동 남 서

N, M = map(int, input().split())
y, x, d = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

cnt = 0

while True:
    if arr[y][x] == 0:
        arr[y][x] = 2
        cnt += 1
        continue

    valid = False
    for i in range(4):
        dx, dy = direct[i]
        nx, ny = x + dx, y + dy
        if is_valid_move(nx, ny) and arr[ny][nx] == 0:
            valid = True
            break

    if valid:
        d = (d + 3) % 4
        dx, dy = direct[d]
        nx, ny = x + dx, y + dy
        if is_valid_move(nx, ny) and arr[ny][nx] == 0:
            x, y = nx, ny

    else:
        nd = (d + 2) % 4
        nx, ny = x + direct[nd][0], y + direct[nd][1]
        if not is_valid_move(nx, ny) or arr[ny][nx] == 1:
            break
        x, y = nx, ny

print(cnt)
