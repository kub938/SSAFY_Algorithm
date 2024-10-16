def rotate():
    global tmpboard
    tmp = [raw[:] for raw in tmpboard]

    for i in range(n):
        for j in range(n):
            tmpboard[i][j] = tmp[n-1-j][i]


def tilted(dir):
    global tmpboard

    for _ in range(dir):
        rotate()
    

    for i in range(n):
        arr = tmpboard[i]
        tilted = [0] * n 
        merged = [False] * n


        for j in range(n):

            if arr[j] == 0:
                continue

            if tilted[0] == 0:
                tilted[0] = arr[j]
                continue

            idx = j
            while idx >= 0 and tilted[idx] == 0:
                idx -= 1
            
            if idx >= 0 and not merged[idx] and tilted[idx] == arr[j]:
                tilted[idx] *= 2
                merged[idx] = True

            else:
                tilted[idx+1] = arr[j]
            
        tmpboard[i] = tilted

n = int(input())
board= [list(map(int,input().split())) for _ in range(n)] 
res = 0


total = pow(4,5)
for tmp in range(total):
    tmpboard = [raw[:] for raw in board]

    for _ in range(5):
        dir = tmp % 4
        tmp //= 4
        tilted(dir)
    
    for k in range(n):
        for l in range(n):
            res = max(tmpboard[k][l], res)
    


print(res)
