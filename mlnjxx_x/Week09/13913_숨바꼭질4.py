from collections import deque

n, m = map(int, input().split())

# 최대 크기 설정
MAX_SIZE = 200000
board = [-1] * (MAX_SIZE + 1)
previous = [-1] * (MAX_SIZE + 1)  # 이전 위치를 기록할 배열
q = deque()

board[n] = 0
q.append(n)

while q:
    x = q.popleft()

    for nx in [x + 1, x - 1, x * 2]:
        if 0 <= nx <= MAX_SIZE and board[nx] == -1:
            board[nx] = board[x] + 1
            previous[nx] = x  # 현재 위치 x에서 nx로 이동했음을 기록
            q.append(nx)

            if nx == m:  # 목표 위치에 도달하면 종료
                break

# 결과 출력
if board[m] != -1:
    print(board[m])  # 최소 이동 횟수
    # 이동 경로 역추적
    path = []
    current = m
    while current != -1:
        path.append(current)
        current = previous[current]
    path.reverse()  # 경로를 역순으로 뒤집음
    print(" ".join(map(str, path)))  # 경로 출력