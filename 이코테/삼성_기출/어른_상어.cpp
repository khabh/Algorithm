#include <iostream>

using namespace std;

int n, m, k, board[20][20], smell[20][20][2], dir[401], priority[401][4][4], t = 0, remain;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int dx[4] = {-1, 1, 0, 0}, dy[4] = {0, 0, -1, 1};
	cin >> n >> m >> k;
	remain = m;

	for (int i = 0; i < n; i++) 
		for (int j = 0; j < n; j++) 
			cin >> board[i][j];
			
	for (int i = 1; i <= m; i++) {
		cin >> dir[i];
		dir[i]--;
	}
		
		
	for (int i = 1; i <= m; i++) {
		int d;
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < 4; k++) {
				cin >> d;
				priority[i][j][k] = --d;
			}
		}
	}
		
	while (remain > 1) {
		int new_board[20][20];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				new_board[i][j] = board[i][j];
				if (smell[i][j][0] && !board[i][j]) {
					if (smell[i][j][1] == 1)
						smell[i][j][0] = 0;
					smell[i][j][1]--;
					continue;
				}
				if (board[i][j]) {
					smell[i][j][0] = board[i][j];
					smell[i][j][1] = k;
				}	
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!board[i][j])
					continue;
				int shark = board[i][j], x = -1, y, next_d;
				bool self = false;
				for (int d : priority[shark][dir[shark]]) {
					int nx = i + dx[d], ny = j + dy[d];
					if (nx < 0  || ny < 0 || nx >= n || ny >= n || (smell[nx][ny][0] && smell[nx][ny][0] != shark) || (smell[nx][ny][0] == shark && self))
						continue;
					x = nx;
					y = ny;
					next_d = d;
					self = true;
					if (!smell[nx][ny][0]) 
						break;
				}
				dir[shark] = next_d;
				new_board[i][j] = 0;
				if (x == -1)
					continue;
				if (!new_board[x][y]) {
					new_board[x][y] = shark;
					continue;
				}
				remain--;
				new_board[x][y] = min(new_board[x][y], shark);
			}
		}
		
		t++;
		if (t > 1000) {
			cout << -1;
			return 0;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = new_board[i][j];
			}
		}
	}
	
	cout << t;
}