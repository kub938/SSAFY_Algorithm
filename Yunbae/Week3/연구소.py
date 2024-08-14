from itertools import combinations
from collections import deque
import copy
n,m = map(int,input().split())
board = [list(map(int,input().split())) for _ in range(n)]

def in_range(x,y):
    return 0<=x<n and 0<=y<m

def bfs(x,y):
    global test_board
    dx, dy = [0, 1, 0, -1], [1, 0, -1, 0]
    que = deque()
    que.append([x,y])
    visited[x][y] = 1
    while(que):
        x,y = que.popleft()
        for i in range(4):
            nx,ny = x+dx[i],y+dy[i]
            if in_range(nx,ny) and visited[nx][ny]==0 and test_board[nx][ny]==0:
                que.append([nx,ny])
                test_board[nx][ny] = 2
                visited[nx][ny] = 1

virus = []
zero_coord = []
wall_cnt = 0
for i in range(n):
    for j in range(m):
        if board[i][j]==0:
            zero_coord.append([i,j])
        elif board[i][j]==1:
            wall_cnt+=1
        elif board[i][j]==2:
            virus.append([i,j])

result = 0
for z in combinations(zero_coord,3):
    test_board = copy.deepcopy(board)
    for coord in z:
        x,y = coord
        test_board[x][y] = 1

    visited = [[0] * m for _ in range(n)]
    for v in virus:
        x,y = v
        bfs(x,y)

    zero_cnt = 0
    for i in range(n):
        for j in range(m):
            if test_board[i][j] == 0:
                zero_cnt +=1
    result = max(zero_cnt, result)

print(result)






