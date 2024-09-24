import heapq

direct = [(-1, 0), (1, 0), (0, 1), (0, -1)]


def is_valid_move(x, y):
    return 0 <= x < N and 0 <= y < N


T = int(input())
for tc in range(1, T+1):
    N = int(input())
    arr = []
    for _ in range(N):
        temp = list(input())
        for i in range(N):
            temp[i] = int(temp[i])
        arr.append(temp)

    start, end = (0, 0), (N-1, N-1)
    q = [(0, 0, 0)]
    answer = 0
    visited = [[0 for _ in range(N)] for _ in range(N)]
    while q:
        c, x, y = heapq.heappop(q)
        if (x, y) == end:
            answer = c
            break
        for dx, dy in direct:
            nx, ny = x + dx, y + dy
            if is_valid_move(nx, ny) and visited[ny][nx] != 1:
                nc = c + arr[ny][nx]
                heapq.heappush(q, (nc, nx, ny))
                visited[ny][nx] = 1
    print(f'#{tc} {answer}')
