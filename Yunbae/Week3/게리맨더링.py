from itertools import combinations,permutations
import sys

n = int(input())
people_cnt = list(map(int,input().split()))
board = [[0]*n for _ in range(n)]
people = [i for i in range(n)]

def blue_dfs(x):
    global blue_visited
    blue_visited[x] = 1
    for i in range(n):
        if board[x][i] == 1 and color[i] ==1 and blue_visited[i]==0:
            blue_dfs(i)

def red_dfs(x):
    global red_visited
    red_visited[x] = 1
    for i in range(n):
        if board[x][i] == 1 and color[i] ==2 and red_visited[i]==0:
            red_dfs(i)

for i in range(n):
    data = list(map(int,input().split()))
    data = data[1:]
    for j in range(len(data)):
        board[i][data[j]-1] = board[data[j]-1][i] = 1

min_value = sys.maxsize
for i in range(n):
    for c in combinations(people, i):
        color = [1] * n
        for j in c:
            color[j] = 2

        blue_target,red_target = 0,0
        for j in range(n):
            if color[j] == 1:
                blue_target = j
            elif color[j] == 2:
                red_target = j

        blue_cnt = sys.maxsize
        red_cnt = sys.maxsize
        blue_visited = [0]*n
        red_visited = [0]*n
        blue_dfs(blue_target)
        red_dfs(red_target)

        cnt = 0
        for j in range(n):
            if blue_visited[j] == 1 and color[j] == 1:
                blue_cnt += people_cnt[j]
                cnt+=1
            if red_visited[j] == 1 and color[j] == 2:
                red_cnt += people_cnt[j]
                cnt+=1

        if cnt==n:
            min_value = min(abs(blue_cnt-red_cnt),min_value)

if sys.maxsize == min_value:
    print(-1)
else:
    print(min_value)
