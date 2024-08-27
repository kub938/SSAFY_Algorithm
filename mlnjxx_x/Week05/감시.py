def upd(x,y,direction):
    dx = [0,-1,0,1]
    dy = [-1,0,1,0]
    dir = direction % 4
    
    while True:
        x += dx[dir]
        y += dy[dir]
        if  x < 0 or x >= n or y < 0 or y >= m or board2[x][y] == 6:
            return
        if board2[x][y] != 0:
            continue
        board2[x][y] = 7


n,m = map(int,input().split())
# 각 변수들
board1 = [list(map(int,input().split())) for i in range(n)] # 원본배열
board2 = [] # 복사할 배열
x,y = 0,0 
cctv  = [] # cctv 받아옴
mn = 0 # 0의 개수 


# 0의 수와 CCTV의 수를 계산.
for i in range(n):
    for j in range(m):
        if board1[i][j] != 0 and board1[i][j] != 6:
            cctv.append((i,j))
        # 빈 공간이라면 mn에 추가
        if board1[i][j] == 0 :
            mn += 1


k = pow(4, len(cctv))


# 모든 조합의 경우의 수에대해 
for tmp in range(k):
    board2 = [raw[:] for raw in board1]
    for i in range(len(cctv)):
        dir = tmp % 4
        tmp //= 4
        x, y = cctv[i]

        if board1[x][y] == 1:
            upd(x,y,dir)

        elif board1[x][y] == 2:
            upd(x,y,dir)
            upd(x,y,dir+2)

        elif board1[x][y] == 3:
            upd(x,y,dir)
            upd(x,y,dir + 1)

        elif board1[x][y] == 4:
            upd(x,y,dir)
            upd(x,y,dir+1)
            upd(x,y,dir+2)

        elif board1[x][y] == 5:
            upd(x,y,dir)
            upd(x,y,dir+1)
            upd(x,y,dir+2)
            upd(x,y,dir+3)

    # board2를 돌면서 합 
    val = sum(1 for i in range(n) for j in range(m) if board2[i][j] == 0)
    # mn = min(mn,val)

    if mn > val:
        print(board2)
        mn = min(mn, val)
    

print(mn)