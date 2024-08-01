from collections import deque

n,m,v = list(map(int,input().split()))

board = [[False] * (n+1) for _ in range(n+1)]
vis = [False] * (n+1)
vis2 = [False] * (n+1)

for _ in range(m):
    x,y = map(int,input().split())
    board[x][y] = True
    board[y][x] = True




    
def dfs(v):
    vis2[v] = True
    print(v, end =" ")
    for i in range(1, n+1):
        if not vis2[i] and board[v][i] == 1:
            dfs(i)

def bfs(v):
    q= deque([v])
    vis[v] = True
    while q:
        v = q.popleft()
        print(v , end =" ")
        for i in range(1,n+1):
            if not vis[i] and board[v][i] == 1:
                q.append(i)
                vis[i] = True




dfs(v)
print() 
bfs(v)


