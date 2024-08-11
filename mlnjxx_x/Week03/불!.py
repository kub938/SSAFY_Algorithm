from collections import deque
n,m = map(int,input().split())
board = [list(map(str,input())) for _ in range(n)]

person = [[-1] * m for _ in range(n)]
fire = [[-1] * m for _ in range(n)]

dx = [0,-1,0,1]
dy = [-1,0,1,0]

q1 = deque()
q2 = deque()



for i in range(n):
    for j in range(m):
        if board[i][j] == "J":
            q1.append((i,j))
            person[i][j] = 0

        if board[i][j] == "F":
            q2.append((i,j))
            fire[i][j] = 0

# print(q1,q2)
# print(board)
# print(person)
# print(fire)



while q2:
    x, y = q2.popleft()

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
    
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue

        if board[nx][ny] == '#' or fire[nx][ny] >= 0:
            continue

        fire[nx][ny] = fire[x][y] + 1 
        q2.append((nx,ny))


while q1:
    x, y = q1.popleft()

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            print(person[x][y]+ 1)
            exit()

        if board[nx][ny] == '#' or person[nx][ny] >= 0:
            continue
        
        if fire[nx][ny] == -1:
            if person[x][y]+ 1  >= fire[nx][ny]:
                continue

        person[nx][ny] = person[x][y] + 1
        q1.append((nx,ny))

print("IMPOSSIBLE")

# 만약에.. 탈출할수 없을때 ?

# [0 1234]
# [1234 0]
# [1234 . m-1]
# [n-1, 1234]

