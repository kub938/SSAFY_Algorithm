import sys
from itertools import combinations
from collections import deque

# 방향 벡터
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

N, M = map(int, sys.stdin.readline().split())
board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
chicken = []
tmp_board = [[0] * N for _ in range(N)]

# 치킨집과 집의 위치 저장
for i in range(N):
    for j in range(N):
        if board[i][j] == 2:
            chicken.append((i, j))
        elif board[i][j] == 1:
            tmp_board[i][j] = 1

ans = sys.maxsize

# 조합을 통해 M개의 치킨집 선택
for c in combinations(chicken, M):
    dist = [[-1] * N for _ in range(N)]
    q = deque(c)

    # 초기 거리 설정
    for t in c:
        dist[t[0]][t[1]] = 0
    
    tmp = 0

    # BFS 시작
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if tmp_board[nx][ny] != 2 and dist[nx][ny] == -1:
                    q.append((nx, ny))
                    dist[nx][ny] = dist[x][y] + 1

    # 집까지의 최소 거리 계산
    for i in range(N):
        for j in range(N):
            if tmp_board[i][j] == 1:
                tmp += dist[i][j]

    ans = min(ans, tmp)

print(ans)
