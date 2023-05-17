#include <iostream>
#include <queue>
#include <tuple>

using namespace std;
int parent[100000];
priority_queue<pair<int, int>> xq, yq, zq;
priority_queue<tuple<int, int, int>> q;

void add_dist(priority_queue<pair<int, int>>& d) {
	pair<int, int> a = d.top();
	d.pop();
	pair<int, int> b = d.top();
	q.push(make_tuple(b.first - a.first, a.second, b.second));
}

int find_parent(int child) {
	while (parent[child] != child)
		child = parent[child];
		
	return child;
}

void union_parent(int a, int b) {
	a = find_parent(a);
	b = find_parent(b);
	
	if (a < b) 
		parent[b] = a;
	else 
		parent[a] = b;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, result = 0, x, y, z;
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> x >> y >> z;
		xq.push({x, i});
		yq.push({y, i});
		zq.push({z, i});
		
		parent[i] = i;
	}
	
	while (xq.size() > 1) {
		add_dist(xq);
		add_dist(yq);
		add_dist(zq);
	}
	
	while (!q.empty()) {
		int cost, a, b;
		tie(cost, a, b) = q.top();
		q.pop();
		
		if (find_parent(a) != find_parent(b)) {
			union_parent(a, b);
			result -= cost;
			n--;
		}
	}
	
	cout << result;
}