import sys
from itertools import combinations
input = sys.stdin.readline

def calculate_power(team) :
    team_power = 0
    for i in range(len(team)) :
        for j in range(i+1, len(team)) :
            p1, p2 = team[i], team[j]
            team_power += power[p1][p2] + power[p2][p1]
    return team_power

ans = int(1e9)
n = int(input())
power = [list(map(int, input().split())) for _ in range(n)]
for cnt in range(1, n//2 + 1) :
    combination = combinations(range(n), cnt)
    for comb in combination :
        start_team = comb
        q2 = list(set(range(n)) - set(start_team))
        start_team_power = calculate_power(start_team)
        q2_power = calculate_power(q2)
        ans = min(ans, abs(start_team_power - q2_power))
print(ans)