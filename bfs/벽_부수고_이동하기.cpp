#include <bits/stdc++.h>

using namespace std;
char board[1001][1001];
int dist[1001][1001][2];
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

int main() {
	int n, m;
	queue<tuple<int, int, int>> q;
	dist[0][0][0] = dist[0][0][1] = 1;
	cin >> n >> m;
	q.push({0, 0, 0});
	
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++)
			cin >> board[i][j];
	}
	
	if (n == 1 && m == 1) {
	    cout << "1";
	    return 0;
	}
	
	while(!q.empty()) {
		int x, y, broken;
		tie(x, y, broken) = q.front();
		q.pop();
		int next_dist = dist[x][y][broken] + 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx == n - 1 && ny == m - 1) {
				cout << next_dist;
				return 0;
			}
			if (nx < 0 || ny < 0 || nx >= n || ny >= m)
				continue;
			if (board[nx][ny] == '0' && !dist[nx][ny][broken]) {
				dist[nx][ny][broken] = next_dist;
				q.push({nx, ny, broken});
			}
			if (!broken && board[nx][ny] == '1' && !dist[nx][ny][1]) {
				dist[nx][ny][1] = next_dist;
				q.push({nx, ny, 1});
			}
			
		}
	}
	
	cout << "-1";
}