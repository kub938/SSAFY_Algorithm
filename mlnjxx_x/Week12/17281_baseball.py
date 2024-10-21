n = int(input())
ening = [list(map(int,input().split())) for _ in range(n)]


arr =[]
isused = [False] * 10
total = 0


def dfs(depth):
    global total
    if depth == 8 :
        pos = arr[:3] + [0] + arr[3:]
        
        res = 0
        
        point = 0
        # for i in range(n):
        for en in ening:
        
            r1,r2,r3 = 0,0,0
            out = 0

            while out < 3:
            # 1차원으로 받아야지 통과되더라 
            # 타석을 들고 그대로 이동 해야함
            
                if en[pos[point]] == 0:
                    out += 1
                    
                elif en[pos[point]] == 1:
                    res += r3
                    r3 = r2
                    r2 = r1
                    r1 = 1

                elif en[pos[point]] == 2:
                    res += (r2+r3)                    
                    r3 = r1
                    r2 = 1
                    r1 = 0

                elif en[pos[point]] == 3:
                    res += (r1+r2+r3)
                    r3 = 1
                    r2 = 0
                    r1 = 0


                elif en[pos[point]] == 4:
                    res += (r1+r2+r3) + 1
                    r3 = 0
                    r2 = 0
                    r1 = 0
                
                point += 1
                if point == 9:
                    point = 0


        total = max(total,res)
        return
    

    for i in range(1, 9):
        if not isused[i]:
            isused[i] = True
            arr.append(i)
            dfs(depth+1)
            arr.pop()
            isused[i] = False

dfs(0)
print(total)


