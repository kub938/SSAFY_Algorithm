def view(skyscrape, d):
    stack = []
    list_ = enumerate(skyscrape, 1) if d == 0 else reversed(list(enumerate(skyscrape, 1)))
    for idx, v in list_:
        while stack and stack[-1][1] <= v:
            stack.pop()
        count[idx] += len(stack)

        if stack:
            distance = abs(stack[-1][0] - idx)
            if distance < close[idx][1]:
                close[idx][0] = stack[-1][0]
                close[idx][1] = distance
            elif distance == close[idx][1] and stack[-1][0] < close[idx][0]:
                close[idx][0] = stack[-1][0]

        stack.append([idx, v])


N = int(input())
arr = list(map(int, input().split()))

count = [0] * (N + 1)
close = [[float('inf'), float('inf')] for _ in range(N+1)]

view(arr, 0)
view(arr, 1)

for i in range(1, N+1):
    if count[i] > 0:
        print(count[i], close[i][0])
    else:
        print(0)
