from collections import deque


class dice:
    def __init__(self):
        self.top = 1
        self.bottom = 6
        self.N = 5
        self.S = 2
        self.E = 3
        self.W = 4


direct = [(0, 1), (1, 0), (0, -1), (-1, 0)]


def roll_dice(r, di):
    if di == 0:
        r.top, r.E, r.bottom, r.W = r.W, r.top, r.E, r.bottom
    elif di == 1:
        r.top, r.S, r.bottom, r.N = r.N, r.top, r.S, r.bottom
    elif di == 2:
        r.top, r.E, r.bottom, r.W = r.E, r.bottom, r.W, r.top
    else:
        r.top, r.S, r.bottom, r.N = r.S, r.bottom, r.N, r.top


def is_valid_move(nr, nc):
    global n
    return 0 <= nr < n and 0 <= nc < n


if __name__ == "__main__":
    n, m = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]

    d = dice()

    score = 0
    r, c = 0, 1
    z = 0
    roll_dice(d, z)

    while m > 0:
        # 현재 좌표 숫자 확인한다.
        target = arr[r][c]

        # 점수 얻는다
        visited = [[0] * n for _ in range(n)]
        q = deque()
        q.append((r, c))
        visited[r][c] = 1
        cnt = 1
        while q:
            y, x = q.popleft()
            for dy, dx in direct:
                ny, nx = y + dy, x + dx
                if is_valid_move(ny, nx) and target == arr[ny][nx] and visited[ny][nx] != 1:
                    visited[ny][nx] = 1
                    cnt += 1
                    q.append((ny, nx))
        # print(target, z, cnt, d.bottom)
        score += cnt * target
        # 방향바꾼다
        if target == d.bottom:
            pass
        elif target < d.bottom:
            z = (z + 1) % 4
        else:
            z = (z + 3) % 4

        # 구룬다
        dr, dc = direct[z]
        nr, nc = r + dr, c + dc
        if is_valid_move(nr, nc):
            r, c = nr, nc
        else:
            z = (z + 2) % 4
            dr, dc = direct[z]
            r += dr
            c += dc

        roll_dice(d, z)
        m -= 1

    print(score)
