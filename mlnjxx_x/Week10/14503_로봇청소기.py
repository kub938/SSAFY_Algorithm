'''
방향 존재
1. 현재 칸이 청소되지 않았을때 청소
2. 4칸 중 빈칸이 없는 경우 후진 할 수 있으면 한 칸 후진하고 1번
    뒤에 벽이 있으면 작동 멈춤


3. 4칸중 빈칸이 있다 
    반시계 90 회전
    바라보는 방향을 기준으로  앞쪽 칸이 청소 되지 않았으면 한칸 전진
'''


n,m = map(int,input().split())
rx,ry,dir = map(int,input().split())
board = [list(map(int,input().split())) for _ in range(n)]
# dist = [[0] for _ in range(m) for _ in range(n)]
board[rx][ry] = 2

cnt = 1
# 북 동 남 서
dx = [-1,0,1,0]
dy = [0,1,0,-1]
# dir = [0,1,2,3] 


# for i in range(n):
#     for j in range(m):
#         if board[i][j] == 1:
#             dist[i][j] = -1


# 4방향 탐색후 0이라면 반시계회전후 전진

while True:
    is_clean =False
        
    for i in range(4):
        if is_clean:
            break
            
        nx = rx + dx[i]     
        ny = ry + dy[i]


        if nx < 0 or ny < 0 or nx >= n or ny >= m:
            continue

        if board[nx][ny] == 0: # 청소할 칸이 하나라도 존재한다.
            while True:
                dir = dir - 1
                if dir < 0:
                    dir = 3
                nx = rx + dx[dir]
                ny = ry + dy[dir]

                if board[nx][ny] == 0:
                    board[nx][ny] = 2
                    cnt += 1
                    is_clean = True
                    rx,ry = nx,ny
                    break
    if not is_clean:
        tmp_dir = (dir+2) % 4 # 
        rx = rx + dx[tmp_dir]
        ry = ry + dy[tmp_dir]
        
        if board[rx][ry] == 1:
            print(cnt)
            break
    '''
    그니까 북에서 남쪽가고
    바라보는 방향은 그대로 바라보고있고,
    이동만 남쪽으로 이동해야해
    '''
        
            
    



                    
                    


                
            
        

        



