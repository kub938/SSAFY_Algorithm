def fisherman_turn():
    global fisherman_pos,total_sum
    # pos += 1
    # 가로값을 돌며 가장 가까운 상어를 잡아먹음
    fisherman_pos += 1
    for i in range(1,r+1):
        # 함수
        if shark_exist(i,fisherman_pos):
            total_sum += catchshark(i,fisherman_pos)
            return

def shark_exist(i,j):
    return len(board[i][j]) >= 1
    
def catchshark(i,j):
    velocity, direction, size = board[i][j][0]
    board[i][j].pop()
    return size 

def sharks_move():
    for i in range(1,r+1):
        for j in range(1, c+1):
            if shark_exist(i,j):
                shark_move(i,j)
            

    for i in range(1, r+1):
        for j in range(1, c+1):
            board[i][j] = dummy[i][j]
            dummy[i][j] = []
    
    # eat 
    for i in range(1, r+1):
        for j in range(1, c+1):
            if len(board[i][j]) >= 1:
                eat(i,j)

def eat(i,j):
    board[i][j].sort(key = lambda x : x[2] , reverse=True)
    while len(board[i][j]) > 1:
        board[i][j].pop()

def shark_move(i,j):
    # 방향을 지정해줘야함.
    velocity, direction, size = board[i][j][0]
    board[i][j].pop()

    # 상 하
    if 0 <= direction <= 1:
        mv = velocity % ((r  - 1) * 2)

        while mv:
            i += dx[direction]
            j += dy[direction]

            if i <= 0 or j <= 0 or i > r or j > c:
                direction = 1 - direction
                i += 2 * dx[direction]
                j += 2 * dy[direction]
            mv -= 1

    else:
        mv = velocity % ((c  - 1) * 2)

        while mv:
            i += dx[direction]
            j += dy[direction]

            if i <= 0 or j <= 0 or i > r or j > c:
                direction = 5 - direction
                i += 2 * dx[direction]
                j += 2 * dy[direction]
            mv -= 1

    dummy[i][j].append((velocity,direction,size))

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]
# 0->up , 1->down, 2->right, 3->left

r, c, m = map(int, input().split())
fisherman_pos = 0
total_sum = 0

board = [[[] for _ in range(105)] for _ in range(105)]
dummy = [[[] for _ in range(105)] for _ in range(105)]

# 입력 처리
for _ in range(m):
    x, y, velocity, direction, size = map(int, input().split())
    board[x][y].append((velocity, direction - 1, size))

# print("###################")
# for i in board:
        # print(i)


fishman_pos = 0

while fisherman_pos <= c:
    fisherman_turn()
    sharks_move()

    # print("###################")
    # for i in board:
        # print(i)


print(total_sum)
    

    
    





