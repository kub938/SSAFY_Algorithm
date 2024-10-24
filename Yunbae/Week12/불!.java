from collections import deque

def escape(x,y):
    return 0<=x<n and 0<=y<m

def bfs():
    global ans
    while que:
        x,y,t = que.popleft()
        for i in range(4):
            nx,ny = x+dx[i],y+dy[i]
            if t == 'J' and not escape(nx, ny):
                ans = dis[x][y]
                return
            elif escape(nx, ny) and t == 'F' and board[nx][ny] == '.' and dis[nx][ny] == True:
                dis[nx][ny] = '#'
                que.append([nx, ny, 'F'])
            elif t == 'J' and board[nx][ny] == '.' and dis[nx][ny] == True:
                dis[nx][ny] = dis[x][y] + 1
                que.append([nx, ny, 'J'])
    ans = "IMPOSSIBLE"

que = deque()
n,m = map(int,input().split())
board = [list(input()) for _ in range(n)]
dis = [[0]*m for _ in range(n)]
dx,dy = [-1,1,0,0],[0,0,-1,1]
ans = 0


for i in range(n):
    for j in range(m):
        if board[i][j]=="#":
            dis[i][j] = "#"
        elif board[i][j] ==".":
            dis[i][j] = True
        elif board[i][j] =="J":
            dis[i][j] = 1
            que.append([i,j,"J"])
        elif board[i][j] == "F":
            dis[i][j] = "#"
            que.appendleft([i,j,"F"])

bfs()
print(ans)




