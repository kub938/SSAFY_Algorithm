from itertools import permutations


arr = [i for i in range(10)]

N = int(input())
li = list(input().split())

perm = permutations(arr, N+1)

right = []
for p in perm:
    r = 0
    for i in range(N):
        if (li[i] == '<' and p[i] < p[i+1]) or (li[i] == '>' and p[i] > p[i+1]):
            r += 1
        else:
            break
    if r == N:
        right.append(p)

answer1 = ''.join(str(i) for i in right[0])
answer2 = ''.join(str(i) for i in right[-1])

print(answer2)
print(answer1)
