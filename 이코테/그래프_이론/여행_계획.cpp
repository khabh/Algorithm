#include <iostream>
#include <vector>
#include <set>
#include <queue>

using namespace std;

int n, m, start;
bool graph[501][501];
set<int> visit;

bool bfs(int start) {
	queue<int> q;
	q.push(start);
	
	while (!q.empty() && !visit.empty()) {
		int node = q.front();
		q.pop();
		
		for (int i = 1; i <= n; i++) {
			if (graph[i][node]) {
				graph[i][node] = false;
				graph[node][i] = false;
				q.push(i);
				if (visit.count(i)) {
					visit.erase(i);
				}
			}
		}
	}
	
	return visit.empty();
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> m;
		
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> graph[i][j];
		}
	}
	
	cin >> start;
	while (--m) {
		int v;
		cin >> v;
		visit.insert(v);
	}
	
	if (bfs(start)) {
		cout << "YES";
		return 0;
	}
	cout << "NO";
}