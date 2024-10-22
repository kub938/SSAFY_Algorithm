from itertools import permutations
import sys

input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for i in range(N)]
picker = [i for i in range(1, 9)]
max_score = 0

perm = permutations(picker, 8)

s = set()

for p in perm:
    line_up = list(p[:3])
    line_up.append(0)
    line_up.extend(p[3:])

    score, out = 0, 0
    batter = 0
    for arr_stage in arr:
        out = 0
        r1, r2, r3 = 0, 0, 0
        while True:
            hit = arr_stage[line_up[batter]]
            if hit == 0:
                out += 1
                if out == 3:
                    batter = (batter + 1) % 9
                    break
            elif hit == 1:
                score += r3
                r1, r2, r3 = 1, 1 if r1 else 0, 1 if r2 else 0
            elif hit == 2:
                score += (r2+r3)
                r1, r2, r3 = 0, 1, 1 if r1 else 0
            elif hit == 3:
                score += (r1+r2+r3)
                r1, r2, r3 = 0, 0, 1
            elif hit == 4:
                score += (r1+r2+r3) + 1
                r1, r2, r3 = 0, 0, 0
            batter = (batter + 1)%9
    max_score = max(score, max_score)

print(max_score)
