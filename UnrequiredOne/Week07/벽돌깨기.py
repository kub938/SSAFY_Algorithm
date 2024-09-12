from itertools import *
from copy import deepcopy
from collections import deque


def is_valid_move(x, y):
    return 0 <= x < W and 0 <= y < H


def bomb(t_arr, x, y, r):
    direct = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    t_arr[y][x] = 0
    for dx, dy in direct:
        nx, ny = x, y
        for i in range(1, r):
            nx += dx
            ny += dy
            if is_valid_move(nx, ny):
                if t_arr[ny][nx] != 0:
                    bomb(t_arr, nx, ny, t_arr[ny][nx])


def down(t_arr):
    for a in range(W):
        stack = deque()
        for b in range(H-1, -1, -1):
            if t_arr[b][a] != 0:
                stack.append(t_arr[b][a])

        for b in range(H-1, -1, -1):
            if stack:
                t_arr[b][a] = stack.popleft()
            else:
                t_arr[b][a] = 0


T = int(input())
for tc in range(1, T+1):
    N, W, H = map(int, input().split())
    arr = []
    pick = []
    for i in range(W):
        pick += [i] * N
    for i in range(H):
        temp = list(map(int, input().split()))
        arr.append(temp)

    min_brick = W * H
    perm = set(permutations(pick, N))
    for p in perm:
        tmp_arr = deepcopy(arr)
        for i in p:
            for j in range(H):
                if tmp_arr[j][i] == 0:
                    continue
                else:
                    bomb(tmp_arr, i, j, tmp_arr[j][i])
                    down(tmp_arr)
                    break

        min_brick = min(min_brick, sum(sum(0 if tmp_arr[a][b] == 0 else 1 for b in range(W)) for a in range(H)))
        if min_brick == 0:
            break

    print(f'#{tc}', min_brick)
