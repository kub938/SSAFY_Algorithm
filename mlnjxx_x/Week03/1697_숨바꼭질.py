from collections import deque
n,m = map(int,input().split())


board = [-1]*200000
q = deque()
board[n] = 1
q.append(n)




while q:
    x = q.popleft()
    for i in [x+1, x-1, x*2]:
        nx = i
        if nx < 0 or nx > 100000 or board[nx] != -1:
            continue

        board[nx] = board[x] + 1 
        q.append(nx)
        
print(board[m]-1)





