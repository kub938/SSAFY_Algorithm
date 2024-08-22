def search(depth, idx):
    global arr, core, direct, cnt, l, core_length, max_cnt, length
    if depth > len(core) or idx >= core_length:
        return

    for i in range(idx, core_length):
        dirs = direct[i]
        for j in range(len(dirs)):
            stack = []
            chk = True
            x, y = core[i]
            ix, iy = dx[dirs[j]], dy[dirs[j]]
            nx, ny = x, y

            while True:
                nx, ny = nx + ix, ny + iy
                if not (0 <= nx < N and 0 <= ny < N):
                    break
                elif arr[ny][nx] == 1 or arr[ny][nx] == 2:
                    chk = False
                    break
                else:
                    arr[ny][nx] = 2
                    stack.append((nx, ny))

            if chk:
                cnt += 1
                l += len(stack)
                if (cnt == max_cnt and l < length) or cnt > max_cnt:
                    max_cnt = cnt
                    length = l

                search(depth + 1, i + 1)

                l -= len(stack)
                cnt -= 1

            for x, y in stack:
                arr[y][x] = 0


T = int(input())
dx, dy = [-1, 1, 0, 0], [0, 0, 1, -1]

for tc in range(1, T+1):
    N = int(input())
    cnt, l = 0, 0
    arr = []
    core = []
    direct = []
    temp = list(map(int, input().split()))
    for i in range(N):
        if temp[i] == 1:
            cnt += 1
    arr.append(temp)

    for i in range(1, N - 1):
        temp = list(map(int, input().split()))
        if temp[0] == 1:
            cnt += 1
        for j in range(1, N - 1):
            if temp[j] == 1:
                core.append((j, i))
        if temp[-1] == 1:
            cnt += 1
        arr.append(temp)

    temp = list(map(int, input().split()))
    for i in range(N):
        if temp[i] == 1:
            cnt += 1
    arr.append(temp)

    eliminate = []
    for p in core:
        a, b = p
        temp = []
        for i in range(4):
            ix, iy = dx[i], dy[i]
            nx, ny = a, b
            chk = True
            while True:
                nx, ny = nx + ix, ny + iy
                if not (0 <= nx < N and 0 <= ny < N):
                    break
                if arr[ny][nx] == 1:
                    chk = False
                    break
            if chk:
                temp.append(i)
        if temp:
            direct.append(temp)
        else:
            eliminate.append((a, b))
    for a, b in eliminate:
        core.remove((a, b))

    core_length = len(core)
    wall_core_cnt = cnt
    max_cnt = wall_core_cnt
    length = N * N

    search(0, 0)

    print(f'#{tc} {length}')
