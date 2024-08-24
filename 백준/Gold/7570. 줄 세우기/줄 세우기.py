import sys

read = sys.stdin.readline
N = int(read())
order = [0] * (N + 1)
for i, number in enumerate(map(int, read().split())):
    order[number] = i
result = 1
count = 1
for i in range(1, N):
    if order[i] < order[i + 1]:
        count += 1
    else:
        result = max(result, count)
        count = 1
print(N - result)