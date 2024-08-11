from collections import deque

n = int(input())
q = deque()
q.append(n)

mx = 10000000

board = [-1] * mx

board[n] = 1

while q:
    x = q.popleft()

    for i in [x / 3, x / 2, x - 1]:
        # i가 float 타입인지 확인하고, 정수가 아닐 경우 continue
        if isinstance(i, float) and not i.is_integer():
            continue
        
        nx = int(i)  # 정수로 변환
        
        if nx < 0 or nx >= mx // 3:
            continue
        
        if board[nx] != -1:
            continue

        board[nx] = board[x] + 1
        q.append(nx)

print(board[1]-1)
