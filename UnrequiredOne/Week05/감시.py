from copy import deepcopy


def expand(x, y, dire):
    dx, dy = direct[dire]
    nx, ny = x, y
    while True:
        nx += dx
        ny += dy
        if is_valid_move(nx, ny) and arr[ny][nx] != 6:
            arr[ny][nx] = '#'
        else:
            break


def is_valid_move(x, y):
    return 0 <= x < M and 0 <= y < N


direct = [(-1, 0), (1, 0), (0, -1), (0, 1)]
N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
camera, pick = [[], []], []
answer = 0
for i in range(N):
    for j in range(M):
        if arr[i][j] in (0, 6):
            if arr[i][j] == 0:
                answer += 1
            continue
        if arr[i][j] == 5:
            camera[-1].append((j, i))
        else:
            camera[0].append((j, i, arr[i][j]))


if camera[-1]:
    for c in camera[-1]:
        x, y = c
        for i in range(4):
            expand(x, y, i)

    s = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                s += 1
    answer = min(s, answer)

if camera[0]:
    l = len(camera[0])
    MAX_PERMUTATION = pow(4, l)
    perm = [0 for _ in range(l)]
    original_arr = deepcopy(arr)
    for PERMUTATION in range(MAX_PERMUTATION):
        a = PERMUTATION
        idx = 0
        s = 0
        while a > 0:
            if a // 4 > 0:
                perm[idx] = a % 4
                a //= 4
                idx += 1
            else:
                perm[idx] = a
                break
        arr = deepcopy(original_arr)

        for i in range(l):
            x, y, d = camera[0][i]
            if d == 1:
                expand(x, y, perm[i])
            elif d == 2:
                if perm[i] % 2 == 1:
                    expand(x, y, 0)
                    expand(x, y, 1)
                else:
                    expand(x, y, 2)
                    expand(x, y, 3)
            elif d == 3:
                if perm[i] == 0:
                    expand(x, y, 0)
                    expand(x, y, 2)
                elif perm[i] == 1:
                    expand(x, y, 0)
                    expand(x, y, 3)
                elif perm[i] == 2:
                    expand(x, y, 1)
                    expand(x, y, 2)
                elif perm[i] == 3:
                    expand(x, y, 1)
                    expand(x, y, 3)
            else:
                expand(x, y, (perm[i] + 1) % 4)
                expand(x, y, (perm[i] + 2) % 4)
                expand(x, y, (perm[i] + 3) % 4)

        for i in range(N):
            for j in range(M):
                if arr[i][j] == 0:
                    s += 1

        answer = min(s, answer)

print(answer)
