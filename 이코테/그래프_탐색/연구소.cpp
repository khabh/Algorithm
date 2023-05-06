#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;
int n, m, result = 0;
int board[8][8];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int virus(int a, int b, bool visit[][8]) {
	int count = 0;
	visit[a][b] = true;
	queue<pair<int, int>> q;
	q.push({a, b});
	while (!q.empty()) {
		count++;
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int X = x + dx[i];
			int Y = y + dy[i];
			if (X < 0 || Y < 0 || X >= n || Y >= m || visit[X][Y] || board[X][Y] == 1)
				continue;
			visit[X][Y] = true;
			q.push({X, Y});
 		}
	}
	
	return count;
}

void calc_safe_zone() {
	bool visit[8][8] = {false,};
	int count = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (board[i][j] == 1) {
				count++;
				continue;
			}
			if (board[i][j] == 2 && !visit[i][j]) {
				count += virus(i, j, visit);
			}
		}
	}
	
	result = max(result, m * n - count);
}

void dfs(int x, int y, int count) {
	if (count == 3) {
		calc_safe_zone();
		return;
	}
	
	for (int i = y + 1; i < m; i++) {
		if (!board[x][i]) {
			board[x][i] = 1;
			dfs(x, i, count + 1);
			board[x][i] = 0;
		}
	}
	
	for (int i = x + 1; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!board[i][j]) {
				board[i][j] = 1;
				dfs(i, j, count + 1);
				board[i][j] = 0;
			}
		}
	}
}


int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> board[i][j];
	dfs(0, -1, 0);
	cout << result;
}