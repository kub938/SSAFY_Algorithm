class Shark:
    def __init__(self, position, speed, direction, size):
        self.position = position
        self.speed = speed
        self.direction = direction
        self.size = size

    def go_shark(self):
        go = [abs(direct[self.direction][0] * self.speed) % (2 * R - 2), abs(direct[self.direction][1] * self.speed) % (2 * C - 2)]

        while go[0] > 0:
            if 0 <= self.position[0] + direct[self.direction][0] <= R - 1:
                self.position[0] += direct[self.direction][0]
                go[0] -= 1
            else:
                self.direction = 1 if self.direction == 0 else 0
        while go[1] > 0:
            if 0 <= self.position[1] + direct[self.direction][1] <= C - 1:
                self.position[1] += direct[self.direction][1]
                go[1] -= 1
            else:
                self.direction = 3 if self.direction == 2 else 2


direct = [(-1, 0), (1, 0), (0, 1), (0, -1)]

R, C, M = map(int, input().split())
sharks = []
for _ in range(M):
    r, c, s, d, z = map(int, input().split())
    shark = Shark([r-1, c-1], s, d-1, z)
    sharks.append(shark)

answer = 0
idx = 0

while idx < C and sharks:
    mozaic = [[[] for _ in range(C)] for _ in range(R)]
    target_shark = None
    min_ground = R + 2

    for shark in sharks:
        if shark.position[1] == idx and shark.position[0] <= min_ground:
            target_shark = shark
            min_ground = shark.position[0]

    if target_shark:
        answer += target_shark.size
        sharks.remove(target_shark)

    for shark in sharks:
        shark.go_shark()
        mozaic[shark.position[0]][shark.position[1]].append(shark)

    for i in range(R):
        for j in range(C):
            if len(mozaic[i][j]) > 1:
                mozaic[i][j].sort(reverse=True, key=lambda x: x.size)
                temp = mozaic[i][j][1:]
                while temp:
                    shark = temp.pop()
                    sharks.remove(shark)
                mozaic[i][j] = mozaic[i][j][0:1]

    idx += 1

print(answer)
