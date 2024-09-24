def union(a, b):
    a = find(a)
    b = find(b)
    if a == b:
        return

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


def find(a):
    if parent[a] == a:
        return a
    parent[a] = find(parent[a])
    return parent[a]


N, M = map(int, input().split())

parent = [i for i in range(N+1)]
edges = []
answer = 0

for _ in range(M):
    A, B, C = map(int, input().split())
    edges.append((C, A, B))

edges.sort(key=lambda x: x[0])
max_cost = 0
for i in range(M):
    c, a, b = edges[i]
    if find(a) == find(b):
        continue
    else:
        union(a, b)
        answer += c
        max_cost = max(max_cost, c)
print(answer - max_cost)
