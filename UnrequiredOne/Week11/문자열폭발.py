s = list(input())
ss = input()
length = len(ss)
stack = []
for i in s:
    stack.append(i)
    if "".join(stack[-length:] + stack[:0]) == ss:
        for _ in range(length):
            stack.pop()

if not stack:
    print('FRULA')
else:
    for i in stack: print(i, end='')
