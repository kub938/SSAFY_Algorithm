# 총 25명 , 5*5 정사각형
# S, Y
#
# S = 소문난 칠공주 시작
# 조건
# 7명 , 가로세로로 반드시 인접 -> bfs
# 이다솜파의 학생들로만 구성될 필요는 없지만 최소 4명이상은 반드시 포함
# 여학생반의 자리 배치도가 주어졌을때 소문난 칠공주를 경성할 수 있는 모든 경우의 수를 구해라
# map 2중포문 좌표값 dfs에 넘김 그후 dfs에서 4방향으로 dfs돌리면서 arr의 value 확인
# dfs, inRange안에 들어오면 각 방향으로 dfs cnt == 7까지, 각 칸의 value를 arr에 넣음, 결과값에서 S가 4명 이상이면 result+=1

arr = []
map = [list(input()) for _ in range(5)]
dx, dy = [0, 1, 0, -1], [1, 0, -1, 0]
result = 0


def in_range(x, y):
    return 0 <= x < 5 and 0 <= y < 5


def dfs(x, y, cnt, visited):
    global result
    global arr

    if cnt == 7:
        S_cnt = 0
        for i in range(7):
            if arr[i] == 'S':
                S_cnt += 1
            if S_cnt >= 4:
                result += 1
                return
    visited[x][y] = 1
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if in_range(nx, ny) and visited[nx][ny] == 0:
            arr.append(map[nx][ny])
            visited[nx][ny] = 1
            dfs(nx, ny, cnt + 1,visited)
            visited[nx][ny] = 0
            arr.pop()


for i in range(5):
    for j in range(5):
        visited = [[0]*5 for _ in range(5)]
        dfs(i, j, 0,visited)

print(result//2)
