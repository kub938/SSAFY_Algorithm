import heapq

n, k = map(int, input().split())
MAX_VALUE = 100001
arr = [n]
t = 0

if n == 0 and k != 0:
    n += 1
    arr.append(n)
    t += 1

q = []
q.append((t, n, [i for i in arr]))

time_checker = [MAX_VALUE for _ in range(MAX_VALUE)]

while q:
    nt, nn, narr = heapq.heappop(q)
    if nn == k and time_checker[nn] >= nt:
        t = nt
        arr = narr
        break
    elif nn < k:
        if nn * 2 <= 100000:
            if nt + 1 < time_checker[nn * 2]:
                narr_1 = [i for i in narr]
                narr_1.append(2 * nn)
                heapq.heappush(q, (nt + 1, nn * 2, narr_1))
                # q.append((nt + 1, nn * 2, narr_1))
                time_checker[2 * nn] = nt + 1

        if time_checker[nn + 1] >= nt + 1:
            narr_2 = [i for i in narr]
            narr_2.append(nn + 1)
            heapq.heappush(q, (nt + 1, nn + 1, narr_2))
            # q.append((nt + 1, nn + 1, narr_2))
            time_checker[nn + 1] = nt + 1

        if nn - 1 > 0:
            if nt + 1 < time_checker[nn - 1]:
                narr_3 = narr
                narr_3.append(nn - 1)
                heapq.heappush(q, (nt + 1, nn - 1, narr_3))
                # q.append((nt + 1, nn - 1, narr_3))
                time_checker[nn - 1] = nt + 1
    else:
        initial = nn
        for i in range(initial-k):
            nn -= 1
            narr.append(nn)
            nt += 1
            if time_checker[nn] < nt:
                break
            time_checker[nn] = nt
        if nn != k:
            continue
        q.append((nt, nn, narr))

print(t)
print(*arr)
