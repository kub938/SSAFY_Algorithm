n,m  = map(int,input().split())


arr = [0] * 10
issued = [0] * 10


def func(k):
    # baseCondion
    if k == m:
        for i in range(m):
            print(arr[i], end=" ")
        print()
        return
    
    #### 핵심 for ####
    for i in range(1,n+1):
        if not (issued[i]):
            arr[k] = i
            issued[i] = 1
            func(k+1)
            issued[i] = 0

func(0)


    
