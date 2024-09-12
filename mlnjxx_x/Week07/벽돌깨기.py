def comb(cnt, total, block_maps):
    global total_blocks, broken_blocks
 
    if total == total_blocks:
        broken_blocks = total
        return
 
    # 구슬을 다 쏘면 결과 return
    if cnt == 0:
        if total > broken_blocks:
            broken_blocks = total
        return
 
    for i in range(W):
        arr = [x[:] for x in block_maps]  # 게임판 전체를 받아온다.
        stack = set()                     # 좌표 엉키지 않게 set 사용
        blocks = 0                        # 벽돌 초기값
 
        for row in range(H):
            if arr[row][i]:  # 좌표 값이 존재(=block이 있다.)하면 stack에 좌표를 추가
                stack.add((row, i))
                break
                
        while stack:
            row, col = stack.pop()
            if not arr[row][col]:
                continue
 
            blocks += 1
            for k in range(4):
                next_row, next_col = row, col
                for _ in range(arr[row][col] - 1):
                    next_row += dx[k]
                    next_col += dy[k]
                    if 0 <= next_row < H and 0 <= next_col < W:
                        stack.add((next_row, next_col))
            arr[row][col] = 0
 
        for y in range(W):
            idx = H - 1
            for x in range(H - 1, -1, -1):
                if not arr[x][y]:
                    continue
                arr[idx][y], arr[x][y] = arr[x][y], arr[idx][y]
                idx -= 1
 
        comb(cnt-1, total+blocks, arr)
 
T = int(input())
 
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
 
for test_case in range(1, T+1):
    N, W, H = map(int, input().split())  # N : 구슬 쏘는 횟수 W : 가로 H : 세로
    block_maps = [list(map(int, input().split())) for _ in range(H)]
    total_blocks = 0  # 전체 벽돌의 갯수를 저장한다.
 
    for i in range(H):
        for j in range(W):
            if block_maps[i][j]:
                total_blocks += 1
 
    broken_blocks = 0  # 부순 벽돌 초기값
    comb(N, 0, block_maps)
 
    answer = total_blocks-broken_blocks  # 답 : 전체 벽돌 - 부순 벽돌
 
    print(f'#{test_case} {answer}')
