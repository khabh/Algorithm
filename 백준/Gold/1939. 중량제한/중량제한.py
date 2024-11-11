import sys
from collections import deque


def bfs(weight):
    visit = [False] * (N + 1)
    visit[factory1] = True
    queue = deque([factory1])
    while queue:
        n = queue.popleft()
        if n == factory2:
            return True
        for node, w in graph[n].items():
            if not visit[node] and w >= weight:
                visit[node] = True
                queue.append(node)
    return False


read = sys.stdin.readline
N, M = map(int, read().split())
graph = [{} for _ in range(N + 1)]
start = end = 0
for _ in range(M):
    a, b, c = map(int, read().split())
    end = max(end, c)
    c = max(graph[a].get(b, 0), c)
    graph[a][b] = graph[b][a] = c
factory1, factory2 = map(int, read().split())
while start <= end:
    mid = (start + end) // 2
    if bfs(mid):
        start = mid + 1
    else:
        end = mid - 1
print(end)
