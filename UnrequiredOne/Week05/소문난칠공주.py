from itertools import combinations
from collections import deque


def is_valid_move(x, y):
    return 0 <= x < N and 0 <= y < N


direct = [(0, 1), (0, -1), (1, 0), (-1, 0)]
N = 5
arr = [list(input()) for _ in range(N)]

jwapyo = [[i, j] for j in range(N) for i in range(N)]
answer = 0

for c in combinations(jwapyo, 7):
    q = deque()
    q.append(c[0])
    cnt = 1
    stack = [c[0]]
    while q:
        x, y = q.popleft()
        for dx, dy in direct:
            nx, ny = x+dx, y+dy
            if is_valid_move(nx, ny) and [nx, ny] in c and [nx, ny] not in stack:
                stack.append([nx, ny])
                q.append([nx, ny])
                cnt += 1

    if cnt != 7:
        continue

    s = 0
    for i in range(7):
        if arr[c[i][0]][c[i][1]] == 'S':
            s += 1
    if s > 3:
        answer += 1

print(answer)
