def dice_roll(direct):
    global dice
    if direct == 1:
        dice["top"], dice["W"], dice["bottom"], dice["E"] = dice["W"], dice["bottom"], dice["E"], dice["top"]
    elif direct == 2:
        dice["top"], dice["W"], dice["bottom"], dice["E"] = dice["E"], dice["top"], dice["W"], dice["bottom"]
    elif direct == 3:
        dice["top"], dice["bottom"], dice["S"], dice["N"] = dice["S"], dice["N"], dice["bottom"], dice["top"]
    elif direct == 4:
        dice["top"], dice["bottom"], dice["S"], dice["N"] = dice["N"], dice["S"], dice["top"], dice["bottom"]


def valid_move(nx, ny):
    global N, M
    return 0 <= nx < M and 0 <= ny < N


N, M, y, x, K = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(N)]
cmd = list(map(int, input().split()))

dx = [0, 1, -1, 0, 0]
dy = [0, 0, 0, -1, 1]

dice = {"top": 0, "E": 0, "bottom": 0, "W": 0, "N": 0, "S": 0}

for c in cmd:
    nx, ny = x + dx[c], y + dy[c]
    if valid_move(nx, ny):
        dice_roll(c)
        if arr[ny][nx] == 0:
            arr[ny][nx] = dice["bottom"]
        else:
            dice["bottom"] = arr[ny][nx]
            arr[ny][nx] = 0
        print(dice["top"])
        x, y = nx, ny
