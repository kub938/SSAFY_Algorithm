import heapq


def is_valid_move(r, c, mr, mc):
    return 0 <= r < mr and 0 <= c < mc

def solution(land, height):
    answer = 0
    row = len(land)
    col = len(land[0])
    visited = [[-1] * col for _ in range(row)]
    direct = [(-1, 0), (0, -1), (1, 0), (0, 1)]
    q = [(0, 0, 0)]
    while q:
        cost, r, c = heapq.heappop(q)
        if visited[r][c] == -1:
            visited[r][c] = cost
        else:
            continue
        for dr, dc in direct:
            nr, nc = r + dr, c + dc
            if is_valid_move(nr, nc, row, col) and visited[nr][nc] == -1:
                new_cost = abs(land[nr][nc] - land[r][c])
                if height < new_cost:
                    heapq.heappush(q, (new_cost, nr, nc))
                else:
                    heapq.heappush(q, (0, nr, nc))

    for i in range(row):
        for j in range(col):
            answer += visited[i][j]

    return answer
