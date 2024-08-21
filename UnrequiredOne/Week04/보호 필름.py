from itertools import combinations


def is_answer():
    global arr, D, W, K

    chk = [False for _ in range(W)]
    last_chk = True
    for w in range(W):
        i, j, cnt = 0, 0, 1
        while j < D - 1 and cnt < K:
            j += 1
            if arr[i][w] == arr[j][w]:
                cnt += 1
            else:
                i = j
                cnt = 1

        if cnt >= K:
            chk[w] = True
        last_chk = last_chk and chk[w]
        if not last_chk:
            break
    return last_chk


T = int(input())
for tc in range(1, T+1):
    D, W, K = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(D)]
    cnt = 0
    if K > 1:
        if not is_answer():
            comb_index = [i for i in range(D)]
            check = False
            while not check and cnt <= K:
                cnt += 1
                for c in combinations(comb_index, cnt):
                    temp = []
                    for idx in c:
                        tmp = [arr[idx][i] for i in range(W)]
                        temp.append(tmp)

                    m = pow(2, cnt)
                    for i in range(m):
                        toip = list(bin(i)[2:][::-1])
                        if len(toip) < cnt:
                            for _ in range(cnt - len(toip)):
                                toip.append(0)
                        for a, b in zip(c, toip):
                            arr[a] = [int(b)] * W
                        check = is_answer()
                        if check:
                            break
                    if check:
                        break
                    for i, idx in zip(range(cnt), c):
                        arr[idx] = temp[i]
    print(f'#{tc} {cnt}')
