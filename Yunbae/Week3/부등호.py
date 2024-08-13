import sys
from itertools import combinations,permutations
n = int(input())
A = list(input().split())
arr = [i for i in range(10)]

max_value = 0
min_value = sys.maxsize
for p in permutations(arr,n+1):
    check = True
    for i in range(n):
        if A[i]=="<" and p[i]<p[i+1]:
            continue
        elif A[i]==">" and p[i]>p[i+1]:
            continue
        else:
            check = False
    if check:
        p = "".join(list(map(str, p)))
        max_value = max(int(p),max_value)
        min_value = min(int(p),min_value)
      
max_value = str(max_value).zfill(n+1)
min_value = str(min_value).zfill(n+1)

print(max_value)
print(min_value)



