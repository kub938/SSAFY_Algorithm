from collections import deque

n = int(input())
board = []
for _ in range(n):
    board.append(list(map(int, input().rstrip())))
visited = [[0]*n for _ in range(n)]
dx,dy = [-1,0,1,0],[0,1,0,-1]

def in_range(x, y):
    return 0 <= x < n and 0 <= y < n

def bfs(x, y):
    que = deque()
    que.append([x,y])
    visited[x][y]=1
    cnt = 0
    while (que):
        cnt += 1
        x, y = que.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if in_range(nx, ny) and visited[nx][ny] != 1 and board[nx][ny] == 1:
                visited[nx][ny] = 1
                que.append([nx, ny])
    return cnt

result = []
for i in range(n):
    for j in range(n):
        if visited[i][j]==0 and board[i][j]==1:
            result.append(bfs(i,j))

result.sort()
print(len(result))
for i in result:
    print(i);
