import sys
from itertools import combinations
n = int(input())
board = [list(map(int,input().split())) for _ in range(n)]
people = [i for i in range(n)]
min_stat = sys.maxsize

for k in range(1,n//2+1):
    for c in combinations(people,k):
        a_team = list(c)
        b_team = []
        for i in range(n):
            if not (i in a_team):
                b_team.append(i)

        a_stat = 0
        for i in range(len(a_team)):
            for j in range(len(a_team)):
                a_stat+= board[a_team[i]][a_team[j]] + board[a_team[j]][a_team[i]]

        b_stat = 0
        for i in range(len(b_team)):
            for j in range(len(b_team)):
                b_stat+= board[b_team[i]][b_team[j]] + board[b_team[j]][b_team[i]]

        stat_diff = abs(a_stat-b_stat)//2
        min_stat = min(min_stat,stat_diff)

print(min_stat)
