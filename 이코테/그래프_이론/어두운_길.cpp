#include <iostream>
#include <queue>
#include <tuple>

using namespace std;

int linked[200000];
priority_queue<tuple<int, int, int>> q;

int find_parent(int child) {
	while (linked[child] != child) {
		child = linked[child];
	}
	
	return child;
}

void union_parent(int a, int b) {
	a = find_parent(a);
	b = find_parent(b);
	
	if (a < b) 
		linked[b] = a;
	else
		linked[a] = b;	
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, m, total = 0, used = 0;
	cin >> n >> m;
	
	for (int i = 1; i < n; i++) {
		linked[i] = i;
	}
	
	while (m--) {
		int x, y, z;
		cin >> x >> y >> z;
		
		q.push(make_tuple(-z, x, y));
		total += z;
	}
	
	while (n > 1) {
		int x, y, z;
		tie(z, x, y) = q.top();
		q.pop();
	
		if (find_parent(x) != find_parent(y)) {
			n--;
			union_parent(x, y);
			used -= z;
		}
	}
	
	cout << total - used;
}