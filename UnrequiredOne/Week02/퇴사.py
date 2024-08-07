N = int(input())
arr = [[0, 0] for i in range(N+1)]

for i in range(1, N+1):
    T, P = map(int, input().split())
    arr[i] = [T, P]

dp = [0 for _ in range(N + 1)]
for i in range(N-1, -1, -1):
    if i + arr[i+1][0] > N:
        dp[i] = dp[i + 1]
    else:
        dp[i] = max(dp[i + 1], arr[i+1][1] + dp[i + arr[i+1][0]])

print(dp[0])
