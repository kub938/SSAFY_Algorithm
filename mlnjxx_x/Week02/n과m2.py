n,m = map(int,input().split())


arr = [0] * 10
issued = [0] * 10


def func(k):

    # base
    if k == m:
        for i in range(m):
            print(arr[i] , end= " ")
        print()
        return
    
    start = 1
    if k != 0:
        start = arr[k-1]+1
        
    for i in range(start,n+1):
        if not issued[i]:
            arr[k] = i
            issued[i] = 1                                       
            func(k+1)
            issued[i] = 0

func(0)
            
