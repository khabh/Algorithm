#include <iostream>
#include <queue>

using namespace std;

int deltaX[8] = {1, 1, 2, 2, -1, -1, -2, -2};
int deltaY[8] = {2, -2, 1, -1, 2, -2, 1, -1};

int bfs() {
	int visit[300][300] = {};
	queue<pair<pair<int, int>, int>> q;
	int l, x1, y1, x2, y2;
	
	cin >> l >> x1 >> y1 >> x2 >> y2;
	if (x1 == x2 && y1 == y2)
		return 0;
	q.push({{x1, y1}, 0});
	visit[x1][y1] = 1;
	
	while (!q.empty()) {
		int x = q.front().first.first;
		int y = q.front().first.second;
		int count = q.front().second;
		q.pop(); 
			
		for (int i = 0; i < 8; i++) {
			int X = deltaX[i] + x;
			int Y = deltaY[i] + y;
			if (X < 0 || X >= l || Y < 0 || Y >= l || visit[X][Y])
				continue;
			if (X == x2 && Y == y2)
				return count + 1;
			visit[X][Y] = 1;
			q.push({{X, Y}, count + 1});
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int t;
	cin >> t;
	while (t--) {
		cout << bfs() << "\n";
	}
}
