from collections import deque
import sys, copy
input = sys.stdin.readline

# 사무실의 세로, 가로(N x M)
N, M = map(int, input().split())
space = [list(map(int, input().split())) for _ in range(N)]
tmp_space = copy.deepcopy(space)

# 남, 동, 북, 서 방향을 가리킴 
# 남쪽을 시작으로 반시계방향으로 돌아가는 순서로 되어 있음
dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]

# 사무실의 범위를 벗어나는지 체크해주는 함수
def check(row, col):
    return row < 0 or row >=N or col < 0 or col >=M
    
def init():
    obj = deque() # cctv의 위치를 저장할 큐 
    answer = 0
    for i in range(N):
        for j in range(M):
            # 벽이아니고 빈칸이아니면 
            if space[i][j] != 6 and space[i][j] != 0:
                obj.append((space[i][j], i, j))   
            # cctv번호, cctv y좌표, cctv x좌표 저장
            
            # cctv가 아에 없는 경우도 고려해서 빈칸의 갯수로 맞춰줌
            if space[i][j] == 0: answer += 1
    return obj, answer
    
# cctv번호와 좌표, 빈칸 개수 초기화
cctv, answer = init()

# 특정 방향을 감시한다는 것은 그 방향으로 이동하는 것으로 생각할 수 있음
# y: y축좌표, x: x축좌표, direction: 우리가 감시할 방향
def move(y, x, direction):
    direction %= 4
    while True:
        y += dy[direction]
        x += dx[direction]
        # 범위를 벗어났거나 벽을 만났다면 return
        if check(y, x) or tmp_space[y][x] == 6:
            return
        # 빈칸이아니면 
        if tmp_space[y][x] != 0:
            continue
        # 빈칸이면
        tmp_space[y][x] = '#'
        

# 각각의 cctv에 대해서 모든 방향을 고려해 모든 경우의 수를 구함
for i in range(4**len(cctv)):
    case = i
    tmp_space = copy.deepcopy(space)
    
    for j in range(len(cctv)):
        d = case % 4
        case //= 4
        
        num, y, x = cctv[j]
        
        # cctv번호 별로 가르키는 방향으로 이동함
        # 이 부분을 간결하게 작성하려면 어떻게 해야할까 (고민)
        if num == 1:
            move(y, x, d)
        elif num == 2:
            move(y, x, d); move(y, x, d+2)
        elif num == 3:
            move(y, x, d); move(y, x, d+1)
        elif num == 4:
            move(y, x, d); move(y, x, d+1); move(y, x, d+2)
        else:
            move(y, x, d); move(y, x, d+1); move(y, x, d+2); move(y, x, d+3)
    
    # 하나의 case에서 수행을 다 마치면 사각 지대의 개수를 구함
    zero_cnt = 0
    for i in tmp_space:
        zero_cnt += i.count(0)
    answer = min(zero_cnt, answer)
print(answer)
