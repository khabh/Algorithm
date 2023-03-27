#include <iostream>
#include <queue>
using namespace std;

int farm[50][50] = {};
int m, n, k;
int delatX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};

void bfs(int a, int b) {
	queue<pair<int, int>> q;
	q.push({a, b});
	farm[a][b] = 0;
	while (!q.empty()) {
		auto site = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int X = delatX[i] + site.first;
			int Y = deltaY[i] + site.second;
			if (0 > X || 0 > Y || X >= m || Y >= n || !farm[X][Y])
				continue;
			farm[X][Y] = 0;
			q.push({X, Y});
		}
	}
}

void solve() {
	int result = 0;
	farm[50][50] = {};
	cin >> m >> n >> k;
	
	while (k--) {
		int a, b;
		cin >> a >> b;
		farm[a][b] = 1;
	}
	
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (farm[i][j] == 1) {
				bfs(i, j);
				result++;
			}
		}
	}
	
	cout << result << "\n";
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int t;
	cin >> t;
	while (t--) {
		solve();
	}
}
