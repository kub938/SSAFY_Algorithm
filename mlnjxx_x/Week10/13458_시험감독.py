N = int(input()) # 시험장 수 
persons = list(map(int,input().split())) # 응시자 수 A
A,B = map(int,input().split()) # 주감독 값, 부감독 값
ans = 0


for p in persons:
    # if A > B:
    #     ans += p // A
    #     ans += 1
    #     continue

    
        ans += 1
        if p-A < 0:
            continue
        p -= A
        ans += p // B
        if p % B == 0:
            continue
        
        ans += 1
        continue

print(ans)







'''
주감독은 반드시 한명 있어야하고, 

부감독은 여러명

주감독을 미리 채워넣고, 
나머지 부감독을 채우기가 정석

주감독이 부감독 보다 값이 더크면 -> 주감독
부감독이 더크거나 같으면 -> 주감독1 + 부감독으로 다채우기

반마다 주감독으로 값을 뺴고 음수면 종료

나머지값을 부감독 값으로 나눈값을 ans에 더하고
나머지값으로 초기화를 시키고, 
0이될때까지 반복.


'''
        

