#include <iostream>
#include <vector>
#include <queue>

using namespace std;
vector<vector<int>> graph(300001, vector<int>());
bool visit[300001];
queue<pair<int, int>> nodes;
priority_queue<int> result;
int n, m, k, x;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> m >> k >> x;
	while (m--) {
		int from, to;
		cin >> from >> to;
		graph[from].push_back(to);
	}
	
	nodes.push({x, 0});
	
	visit[x] = true;
	while (!nodes.empty()) {
		int node = nodes.front().first;
		int dist = nodes.front().second;
		if (dist == k) 
			break;
		nodes.pop();	
		for (int n : graph[node]) {
			if (visit[n])
				continue;
			visit[n] = true;
			if (dist + 1 == k) {
				result.push(-n);
				continue;
			}
			nodes.push({n, dist + 1});
		}
	}
	
	if (result.empty()) {
		cout << -1;
		return 0;
	}
	while (!result.empty()) {
		cout << -result.top() << "\n";
		result.pop();
	}
}