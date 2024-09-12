def go_shoot(start_x, start_y, d):
    global max_score
    nx, ny = start_x, start_y
    score = 0
    while True:
        dx, dy = direct[d]
        nx += dx
        ny += dy

        if not is_valid_move(nx, ny):
            d = d + 1 if d % 2 == 0 else d - 1
            score += 1
            continue
        else:
            if arr[ny][nx] in score_block:
                score += 1
                d = direct.index(block[arr[ny][nx]][d])
            elif arr[ny][nx] > 5:
                nx, ny = warp_hole[arr[ny][nx]][warp_hole[arr[ny][nx]].index([nx, ny]) ^ 1]
        if (nx == start_x and ny == start_y) or arr[ny][nx] == -1:
            max_score = max(score, max_score)
            return


def is_valid_move(x, y):
    return 0 <= x < N and 0 <= y < N


score_block = [1, 2, 3, 4, 5]
block = {
    1: [(0, -1), (-1, 0), (0, 1), (1, 0)],
    2: [(0, 1), (-1, 0), (1, 0), (0, -1)],
    3: [(1, 0), (0, 1), (-1, 0), (0, -1)],
    4: [(1, 0), (0, -1), (0, 1), (-1, 0)],
    5: [(1, 0), (-1, 0), (0, 1), (0, -1)]
}
direct = [(-1, 0), (1, 0), (0, -1), (0, 1)]

T = int(input())
for tc in range(1, T+1):
    N = int(input())
    arr = []
    starting_point = []
    warp_hole = {6: [], 7: [], 8: [], 9: [], 10: []}
    for i in range(N):
        temp = list(map(int, input().split()))
        for j in range(N):
            if temp[j] == 0:
                starting_point.append([j, i])
                continue
            elif temp[j] in (6, 7, 8, 9, 10):
                warp_hole[temp[j]].append([j, i])
        arr.append(temp)

    max_score = 0
    for x, y in starting_point:
        for d in range(4):
            go_shoot(x, y, d)
    print(f'#{tc}', max_score)
