import heapq


def dijkstra(start_point):
    global arr
    q = []

    dist = [inf] * (n+1)

    heapq.heappush(q, (0, start_point))
    dist[start_point] = 0

    while q:
        d, now = heapq.heappop(q)
        if dist[now] < d:
            continue
        for i in arr[now]:
            c = d + i[1]
            if c < dist[i[0]]:
                dist[i[0]] = c
                heapq.heappush(q, (c, i[0]))

    return dist


inf = 100000000
T = int(input())
for tc in range(1, T+1):
    n, m, t = map(int, input().split())
    s, g, h = map(int, input().split())

    arr = [[] for _ in range(n+1)]
    
    # 도로 잇기
    for _ in range(m):
        a, b, d = map(int, input().split())
        arr[a].append((b, d))
        arr[b].append((a, d))

    dist = dijkstra(s)
    g_dist = dijkstra(g)
    h_dist = dijkstra(h)

    # 후보지
    candidate = [int(input()) for _ in range(t)]

    answer = []
    for end_point in candidate:
        if dist[g] + g_dist[h] + h_dist[end_point] == dist[end_point] or dist[h] + h_dist[g] + g_dist[end_point] == dist[end_point]:
            answer.append(end_point)
    answer.sort()

    for ans in answer:
        print(ans, end=' ')
    print()

