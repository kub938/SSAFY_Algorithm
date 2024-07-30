from collections import deque
from copy import deepcopy

N, M, V = map(int, input().split())

dic = dict()
dic[V] = []

for _ in range(M):
    a, b = map(int, input().split())
    if a not in dic:
        dic[a] = []
    if b not in dic:
        dic[b] = []
    dic[a].append(b)
    dic[b].append(a)

for d in dic.values():
    d.sort()

stack = []
stack.append(V)

visited = []

while stack:
    node = stack.pop()
    if node not in visited:
        visited.append(node)
        arr = deepcopy(dic[node])
        arr.sort(reverse=True)
        stack.extend(arr)
for i in visited:
    print(i, end=" ")
print()

q = deque()
q.append(V)

visited = []
while q:
    node = q.popleft()
    if node not in visited:
        visited.append(node)
        q.extend(dic[node])

for i in visited:
    print(i, end=" ")
