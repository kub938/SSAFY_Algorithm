from collections import deque
n, m = map(int, input().split())

board = [list(map(int,input().split())) for _ in range(m)]
dist = [[0] * n for _ in range(m)]

dx = [0,-1,0,1]
dy = [-1,0,1,0]
q = deque()
for i in range(m):
    for j in range(n):
        if board[i][j] == 1:
            q.append((i,j))
            
        if board[i][j] == 0:
            dist[i][j] = -1

        

while q:
    x,y = q.popleft()
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
    

        if nx < 0 or nx >= m or ny < 0 or ny >= n:
            continue
        if dist[nx][ny] >= 0:
            continue
        dist[nx][ny] = dist[x][y] +1
        q.append((nx,ny))
    
    

print(dist)
print(board)

MAX = 0

for i in range(m):
    for j in range(n):
        if dist[i][j] == -1:
            print(-1)
            exit(0)
            
        MAX = max(dist[i][j], MAX)
        
print(MAX)

