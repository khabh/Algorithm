import sys

read = sys.stdin.readline
N = int(read())
value = sorted([list(map(int, read().split())) for _ in range(N)], key=lambda x: (x[1], x[0]))
end = value[0][1]
count = 1
for n in value[1:]:
    if n[0] >= end:
        end = n[1]
        count += 1
print(count)