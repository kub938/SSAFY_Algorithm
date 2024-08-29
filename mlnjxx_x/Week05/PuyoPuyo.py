from collections import deque

is_puyo = False  # 뿌요가 터졌는지 여부
vis = [[False] * 6 for _ in range(12)]  # 필드 방문 여부
board = [input().strip() for _ in range(12)]  # 필드 상태
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
ans = 0

# vis 배열 초기화
def reset_vis():
    for i in range(12):
        for j in range(6):
            vis[i][j] = False

# bfs 탐색을 기반으로 같은 색의 뿌요를 지우는 함수
def puyo(x, y):
    global is_puyo
    do_erase = False  # 뿌요를 지워야하는지 여부
    vis[x][y] = True
    color = board[x][y]
    q = deque()
    tmp = []  # 4개 이상일 경우 뿌요를 지우기 위해 위치를 저장하는 리스트
    q.append((x, y))
    tmp.append((x, y))
    
    while q:
        cur = q.popleft()
        for i in range(4):
            nx = cur[0] + dx[i]
            ny = cur[1] + dy[i]
            if nx < 0 or nx >= 12 or ny < 0 or ny >= 6:
                continue
            # 빈 칸이거나 색이 다를 경우 무시
            if vis[nx][ny] or board[nx][ny] == '.' or board[nx][ny] != color:
                continue
            vis[nx][ny] = True
            q.append((nx, ny))
            tmp.append((nx, ny))

    # 4개 이상 연결되어 있을 경우 연결된 뿌요 삭제
    if len(tmp) >= 4:
        is_puyo = True
        for cur in tmp:
            board[cur[0]] = board[cur[0]][:cur[1]] + '.' + board[cur[0]][cur[1]+1:]

# 주어진 필드 상태에 따라 뿌요를 처리하는 함수
while True:
    is_puyo = False  # 뿌요가 터졌는지 여부
    for i in range(6):
        # 필드의 빈 칸을 모두 뿌요 위로 올림
        for j in range(10, -1, -1):
            tmp = j
            # 빈 칸일 경우 위치 변경
            while tmp < 11 and board[tmp + 1][i] == '.':
                board[tmp], board[tmp + 1] = board[tmp + 1], board[tmp]
                tmp += 1

    # 필드 상의 터져야 하는 모든 뿌요 처리
    for i in range(12):
        for j in range(6):
            if not vis[i][j] and board[i][j] != '.':
                puyo(i, j)

    if is_puyo:
        ans += 1  # 연쇄 수 추가
        reset_vis()
    else:
        break

print(ans)
