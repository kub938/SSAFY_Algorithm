from itertools import permutations

N, K = map(int, input().split())
kits = list(map(int, input().split()))
answer = 0

for p in permutations(kits, N):
    weight = 500
    save_weight = True
    for i in range(1, N):
        weight += p[i] - K
        if weight < 500:
            save_weight = False
            break
    if save_weight:
        answer += 1

print(answer)
