N = int(input())
students = list(map(int, input().split()))
supervisor = list(map(int, input().split()))

answer = 0
for i in range(N):
    students[i] -= supervisor[0]
    answer += 1
    if students[i] > 0:
        mod = students[i] % supervisor[1]
        answer += students[i] // supervisor[1]
        if mod > 0:
            answer += 1

print(answer)
