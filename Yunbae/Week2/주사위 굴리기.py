n, m, x, y, K = map(int,input().split())
board = [list(map(int,input().split())) for _ in range(n)]
move = list(map(int,input().split()))
dx = [0,0,-1,1]
dy = [1,-1,0,0]
dice = [0,0,0,0,0,0]
turn_pos = {1:[3,1,0,5,4,2], 2:[2,1,5,0,4,3], 3:[4,0,2,3,5,1], 4:[1,5,2,3,0,4]}

def in_range(x,y):
    return 0<=x<n and 0<=y<m


def dice_roll(move):
    dice[0],dice[1],dice[2],dice[3],dice[4],dice[5] = dice[turn_pos[move][0]],dice[turn_pos[move][1]],dice[turn_pos[move][2]],dice[turn_pos[move][3]],dice[turn_pos[move][4]],dice[turn_pos[move][5]]


for i in move:
    nx,ny = x+dx[i-1],y+dy[i-1]
    if in_range(nx,ny):
        x,y = nx,ny

        change_num = turn_pos[i]
        dice_roll(i)
        if board[x][y] == 0:
            board[x][y] = dice[5]
        else:
            dice[5] = board[x][y]
            board[x][y] = 0

        print(dice[0])


