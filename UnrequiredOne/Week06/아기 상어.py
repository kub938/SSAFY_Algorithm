 from collections import deque


def is_valid_move(x, y):
    return 0 <= x < N and 0 <= y < N


def BFS(x, y):
    global time, baby_shark_num_of_eat_fish, baby_shark_size
    q = deque()
    q.append((0, x, y))
    candidate = []
    visited_map = [[0 for _ in range(N)] for _ in range(N)]
    while q:
        cost, nx, ny = q.popleft()
        if [nx, ny] in edible_fishes:
            candidate.append((cost, nx, ny))
        else:
            for dx, dy in direct:
                ix, iy = nx + dx, ny + dy
                if is_valid_move(ix, iy) and arr[iy][ix] <= baby_shark_size and visited_map[iy][ix] != 1:
                    q.append((cost+1, ix, iy))
                    visited_map[iy][ix] = 1

    if candidate:
        candidate.sort(key=lambda ax: (ax[0], ax[2], ax[1]))
        cost, nx, ny = candidate[0]
        time += cost
        baby_shark_num_of_eat_fish += 1
        t = [nx, ny]
        edible_fishes.remove([nx, ny])
        return t
    else:
        return None


N = int(input())
direct = [(0, -1), (-1, 0), (1, 0), (0, 1)]

arr = []
location = [[], [], [], [], [], [], []]
x, y = 0, 0

for i in range(N):
    temp = list(map(int, input().split()))
    for j in range(N):
        if 1 <= temp[j] <= 6:
            location[temp[j]].append([j, i])
        elif temp[j] == 9:
            x, y = j, i
            temp[j] = 0
    arr.append(temp)

baby_shark_size = 2
baby_shark_num_of_eat_fish = 0

edible_fishes = []
edible_fishes.extend(location[1])
time = 0

while edible_fishes:
    target = BFS(x, y)
    if target is None:
        break

    x, y = target

    if baby_shark_size == baby_shark_num_of_eat_fish:
        baby_shark_num_of_eat_fish = 0
        if baby_shark_size < 7:
            edible_fishes.extend(location[baby_shark_size])
        baby_shark_size += 1
print(time)
