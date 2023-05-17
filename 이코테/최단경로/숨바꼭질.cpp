#include <iostream>
#include <queue>

#define INF 200000000

using namespace std;
vector<vector<pair<int, int>>> graph(20001);
int dist[20001];

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, m, max_dist = 0, count = 0, index;
	
	priority_queue<pair<int, int>> q;
	
	cin >> n >> m;
	
	for (int i = 2; i <= n; i++) {
		dist[i] = INF;
	}
	
	while (m--) {
		int a, b;
		cin >> a >> b;
		
		graph[a].push_back({b, 1});
		graph[b].push_back({a, 1});
	}
	
	q.push({0, 1});
	while (!q.empty()) {
		int distance = -q.top().first;
		int start = q.top().second;
		q.pop();
		
		
		if (dist[start] < distance)
			continue;
		for (pair<int, int> node : graph[start]) {
			int cost = distance + node.second;
			if (cost < dist[node.first]) {
				q.push({-cost, node.first});
				dist[node.first] = cost;
			}
		}
	}
	
	for (int i = 2; i <= n; i++) {
		if (dist[i] > max_dist) {
			count = 1;
			max_dist = dist[i];
			index = i;
			continue;
		} 
		if (dist[i] == max_dist) {
			count++;
		}
	}
	
	cout << index << " " << max_dist << " " << count;
}