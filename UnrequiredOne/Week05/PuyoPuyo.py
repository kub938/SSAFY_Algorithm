from collections import deque


def is_valid_move(x, y):
    return 0 <= x < 12 and 0 <= y < 6


color = ['R', 'Y', 'G', 'B', 'P']
direct = [(-1, 0), (1, 0), (0, -1), (0, 1)]

arr = [list(input()) for _ in range(12)][::-1]
cnt = 0

while True:
    get_bomb = False
    for i in range(12):
        for j in range(6):
            if arr[i][j] == '.':
                continue
            visited = [[0 for _ in range(6)] for _ in range(12)]
            q = deque()
            q.append((i, j))
            count = 0
            idx = color.index(arr[i][j])
            stack = set()
            while q:
                x, y = q.popleft()
                for dx, dy in direct:
                    nx, ny = x+dx, y+dy
                    if is_valid_move(nx, ny) and arr[nx][ny] != '.' and idx == color.index(arr[nx][ny]) and visited[nx][ny] == 0:
                        q.append((nx, ny))
                        stack.add((nx, ny))
                        count += 1
                        visited[nx][ny] = 1
            if len(stack) > 3:
                get_bomb = True
                for x, y in stack:
                    arr[x][y] = '.'
            del visited, q, stack
    if get_bomb:
        cnt += 1
    else:
        break

    for i in range(6):
        x, y = 0, 1
        while x < y < 12:
            if arr[x][i] == '.' and arr[y][i] != '.':
                arr[x][i], arr[y][i] = arr[y][i], arr[x][i]
            if arr[x][i] != '.':
                x += 1
            if arr[y][i] == '.':
                y += 1
            if x >= y:
                y += 1

print(cnt)
