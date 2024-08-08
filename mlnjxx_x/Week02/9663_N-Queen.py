n = int(input())


isused1 = [False] * 40
isused2 = [False] * 40
isused3 = [False] * 40
cnt = 0


def func(cur):
    global cnt
    if cur == n:
        cnt += 1
        return
    

    ###
    for i in range(n):
        if isused1[i] or isused2[cur +i] or isused3[cur-i+n-1]:
            continue 

        isused1[i] = True
        isused2[cur + i] = True
        isused3[cur-i+n-1] = True
        func(cur+1)
        isused1[i] = False
        isused2[cur+i] = False
        isused3[cur-i+n-1] = False

func(0)
print(cnt)
    
