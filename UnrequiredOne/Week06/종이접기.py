punch_mark = [
    [0, 1,
     2, 3],
    [1, 0,
     3, 2],
    [3, 2,
     1, 0],
    [2, 3,
     0, 1]
]

k = int(input())
arr = list(input().split())
length = pow(2, k)
first_punch_hole = int(input())

answer = [[0] * length for _ in range(length)]

last_horizon, last_vertical = None, None
for i in range(len(arr)):
    if arr[i] in ['L', 'R']:
        last_horizon = arr[i]
    elif arr[i] in ['U', 'D']:
        last_vertical = arr[i]

cursor = 0
if (last_horizon, last_vertical) == ('L', 'U'):
    cursor = 0
elif (last_horizon, last_vertical) == ('R', 'U'):
    cursor = 1
elif (last_horizon, last_vertical) == ('L', 'D'):
    cursor = 2
elif (last_horizon, last_vertical) == ('R', 'D'):
    cursor = 3

mark = 0
for i in range(4):
    if punch_mark[i][cursor] == first_punch_hole:
        mark = i

for i in range(0, length, 2):
    for j in range(0, length, 2):
        answer[i][j] = punch_mark[mark][0]
        answer[i][j+1] = punch_mark[mark][1]
        answer[i+1][j] = punch_mark[mark][2]
        answer[i+1][j+1] = punch_mark[mark][3]

for i in answer:
    for j in range(length):
        print(i[j], end=" ")
    print()
