def union(a, b, parent):
    a, b = find(a, parent), find(b, parent)
    if a == b:
        return
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


def find(a, parent):
    if parent[a] != a:
        parent[a] = find(parent[a], parent)
    return parent[a]


def MST():
    parent = [i for i in range(N + 1)]
    count = 0
    for i in range(M + 1):
        c, a, b = edges[i]
        if find(a, parent) != find(b, parent):
            union(a, b, parent)
            count += c
    return count


N, M = map(int, input().split())

edges = []
for _ in range(M + 1):
    A, B, C = map(int, input().split())
    edges.append((C, A, B))

edges.sort(key=lambda x: x[0])
asc = MST()

edges.sort(key=lambda x: -x[0])
desc = MST()

print((N-asc)**2 - (N-desc)**2)
