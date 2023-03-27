#include <iostream>
#include <queue>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int visit[100001] = {};
	int result = 0;
	queue<pair<int, int>> q;
	int n, k;
	cin >> n >> k;
	visit[n] = 1;
	q.push({n, 0});
	
	if (n == k) {
		cout << 0;
		return 0;
	}
	while (!q.empty()) {
		int a = q.front().first;
		int t = q.front().second;
		q.pop();
		if (a + 1 == k || a - 1 == k || a * 2 == k) {
			cout << t + 1;
			return 0;
		}
			
		if (a + 1 < 100002 && !visit[a + 1]) {
			visit[a + 1] = 1;
			q.push({a + 1, t + 1});	
		}
		if (a - 1 >= 0 && !visit[a - 1]) {
			visit[a - 1] = 1;
			q.push({a - 1, t + 1});
		}
		if (a * 2 < 100002 && !visit[a * 2]) {
			visit[a * 2] = 1;
			q.push({a * 2, t + 1});
		}
	}
}
