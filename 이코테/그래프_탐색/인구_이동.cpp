#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

int n, l, r;
int board[50][50];
int dx[4] = {0, 0, 1, -1}, dy[4] = {1, -1, 0, 0};

bool visit[50][50];
void bfs(int a, int b) {
	int index = 0, sum = board[a][b], changed;
	vector<pair<int, int>> q;
	q.push_back({a, b});
	visit[a][b] = true;
	while (index < q.size()) {
		int x = q[index].first, y = q[index].second;
		index++;
		for (int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			if (X < 0 || Y < 0 || X >= n || Y >= n || visit[X][Y])
				continue;
			int gap = abs(board[x][y] - board[X][Y]);
			if (gap <= r && gap >= l) {
				visit[X][Y] = true;
				q.push_back({X, Y});
				sum += board[X][Y];
			}
		}
	}
	
	changed = sum / q.size();
	for (auto node : q) {
		board[node.first][node.second] = changed;
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int day = 0;
	cin >> n >> l >> r;
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < n; ++j)
			cin >> board[i][j];
	
	while (1) {
		bool movable = false;
		memset(visit, false, sizeof(visit));
			
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j])
					continue;
				if (i < n - 1) {
					int gap = abs(board[i][j] - board[i + 1][j]);
					if (gap <= r && gap >= l) {
						bfs(i, j);
						movable = true;
						continue;
					}
				}
				if (j < n - 1) {
					int gap = abs(board[i][j] - board[i][j + 1]);
					if (gap <= r && gap >= l) {
						bfs(i, j);
						movable = true;
					}
				}
			}
		}
		
		if (!movable)
			break;
		day++;
	}
	
	cout << day;
}