def DFS(x, y, depth):
    global min_cnt
    if x >= 9 and y > 9:
        if not is_there_1():
            min_cnt = min(min_cnt, depth)
        return
    if min_cnt <= depth:
        return
    if y > 9:
        DFS(x+1, 0, depth)
        return

    if arr[y][x] == 1:
        for size in range(5, 0, -1):
            if can_paste(size, x, y) and cnt_arr[size] < 5:
                paste(size, x, y, 0)
                cnt_arr[size] += 1
                DFS(x, y + 1, depth+1)
                paste(size, x, y, 1)
                cnt_arr[size] -= 1
    else:
        DFS(x, y + 1, depth)


def is_there_1():
    global arr
    for a in arr:
        for b in a:
            if b == 1:
                return True
    return False


def can_paste(size, x, y):
    global arr
    if size == 1:
        return True if arr[y][x] == 1 else False

    if y + size - 1 >= N or x + size - 1 >= N:
        return False

    for a in range(size):
        for b in range(size):
            if arr[y + a][x + b] == 0:
                return False
    return True


def paste(size, x, y, what):
    global arr
    if size == 1:
        arr[y][x] = what
        return
    for i in range(size):
        for j in range(size):
            arr[y+i][x+j] = what


N = 10
arr = [list(map(int, input().split())) for _ in range(N)]
min_cnt = 260000
cnt_arr = {1: 0, 2: 0, 3: 0, 4: 0, 5: 0}

DFS(0, 0, 0)

print(min_cnt if min_cnt < 26000 else -1)
