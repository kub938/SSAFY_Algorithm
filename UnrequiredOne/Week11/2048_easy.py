from itertools import permutations
from copy import deepcopy


def rotate_right():
    global temp_arr
    temp_board = deepcopy(temp_arr)

    for i in range(n):
        for j in range(n):
            temp_board[j][n-1-i] = temp_arr[i][j]

    temp_arr = temp_board


def downer():
    global temp_arr
    for i in range(n):
        stack = []
        for j in range(n):
            target = temp_arr[i][j]
            if target == 0:
                continue

            if stack and stack[-1] == target:
                stack.pop()
                stack.append(target * 2)
                stack.append(-1)
            else:
                stack.append(target)
        for j in range(n // 2):
            if -1 in stack:
                stack.remove(-1)

        stack = stack[::-1]
        for j in range(n):
            if stack:
                temp_arr[i][j] = stack.pop()
            else:
                temp_arr[i][j] = 0


def aside(direct):
    for _ in range(direct):
        rotate_right()
    downer()
    for _ in range((4 - direct) % 4):
        rotate_right()


n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]

selector = [i for i in range(4)]
selector.extend(selector)
selector.extend(selector)
perm = set(permutations(selector, 5))

answer = 0
if n not in (0, 1):
    for p in perm:
        temp_arr = deepcopy(arr)
        for i in p:
            aside(i)
            for j in temp_arr:
                answer = max(answer, max(j))
else:
    for i in arr:
        answer = max(answer, max(i))

print(answer)
