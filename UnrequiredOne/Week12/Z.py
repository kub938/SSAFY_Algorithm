n, r, c = map(int, input().split())
answer = 0
while n > 0:
    n -= 1
    pow2 = 2**n
    pow4 = 4**n

    if r < pow2 and c < pow2:
        continue
    elif r < pow2 <= c:
        answer += pow4 * 1
        c -= pow2
    elif c < pow2 <= r:
        answer += pow4 * 2
        r -= pow2
    else:
        answer += pow4 * 3
        r -= pow2
        c -= pow2

print(answer)
