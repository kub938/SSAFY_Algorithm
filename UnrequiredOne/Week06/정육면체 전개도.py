class Dice:
    def __init__(self):
        self.up = None
        self.down = None
        self.left = None
        self.right = None
        self.front = None
        self.back = None

    def roll_front(self):
        self.up, self.back, self.down, self.front = self.back, self.down, self.front, self.up

    def roll_back(self):
        self.up, self.back, self.down, self.front = self.front, self.up, self.back, self.down

    def roll_left(self):
        self.up, self.left, self.down, self.right = self.right, self.up, self.left, self.down

    def roll_right(self):
        self.up, self.left, self.down, self.right = self.left, self.down, self.right, self.up

    def is_dice(self):
        info = [self.up, self.down, self.left, self.right, self.front, self.back]
        return None not in info

    def print(self):
        info = [self.up, self.down, self.left, self.right, self.front, self.back]
        print(info)


def roll_dice(d, pos, npos):
    y, x = pos
    ny, nx = npos
    if x - nx == 0:
        if y > ny:
            d.roll_back()
        else:
            d.roll_front()
    else:
        if x > nx:
            d.roll_left()
        else:
            d.roll_right()


def DFS(d, x, y, depth):
    global stack, visited

    if d.is_dice():
        return
    for dx, dy in direct:
        nx, ny = x + dx, y + dy
        if [nx, ny] in stack and visited[stack.index([nx, ny])] != 1:
            roll_dice(d, [x, y], [nx, ny])
            d.down = 1
            visited[stack.index([nx, ny])] = 1
            DFS(d, nx, ny, depth + 1)
            roll_dice(d, [nx, ny], [x, y])


direct = [(0, 1), (0, -1), (1, 0), (-1, 0)]


for case in range(3):
    stack = []
    for i in range(6):
        temp = list(map(int, input().split()))
        for j in range(6):
            if temp[j] == 1:
                stack.append([i, j])

    d = Dice()
    d.down = 1
    visited = [0 for _ in range(6)]
    visited[0] = 1
    DFS(d, stack[0][0], stack[0][1], 1)

    print('yes' if d.is_dice() else 'no')
