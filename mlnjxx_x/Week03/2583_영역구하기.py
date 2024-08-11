from collections import deque
n,m,k =map(int,input().split())
coordinate = [list(map(int,input().split())) for _ in range(k)]

board = [[0] * m for _ in range(n)]
dist = [[False] * m for _ in range(n)]
q = deque()
dx = [0,-1,0,1]
dy= [-1,0,1,0]


for x1,y1,x2,y2 in coordinate:
    for i in range(y1,y2):
        for j in range(x1,x2):
            dist[i][j] = True
            board[i][j] = -1

rcnt = 0 #  총 갯수
res = []
for i in range(n):
    for j in range(m):
        if board[i][j] == 0 and dist[i][j] == False:
            q.append((i,j)) 
            rcnt += 1
            cnt = 1
            dist[i][j] = True
        
            while q:
                x,y =q.popleft()
                for k in range(4):
                    nx = x + dx[k]
                    ny = y + dy[k]

                    if nx < 0 or nx >= n or ny < 0 or ny >= m:
                        continue

                    if board[nx][ny] == -1 or dist[nx][ny] == True:
                        continue
                    
                    cnt += 1
                    board[nx][ny] = board[x][y] + 1
                    dist[nx][ny] = True
                    q.append((nx,ny))
            res.append(cnt)

        
print(rcnt)
res.sort()
print(*res)


    



