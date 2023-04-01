#include <iostream>
#include <queue>
#include <string.h>
#include <tuple>
#define NOT_VISITED 0

using namespace std;

int k, w, h;
int dx[12] = {0, 0, 1, -1, 1, 1, 2, 2, -2, -2, -1, -1};
int dy[12] = {1, -1, 0, 0, 2, -2, 1, -1, 1, -1, 2, -2};
int state[32][200][200];
char board[200][200];
queue<tuple<int, int, int>> q;

bool out_of_bound(int x, int y) {
	return 0 > x || 0 > y || x >= h || y >= w || board[x][y] == '1';
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> k >> w >> h;
	
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++)
			cin >> board[i][j];
	}
	
	if (w == 1 && h == 1) {
		cout << 0;
		return 0;
	}
	
	state[0][0][0] = 1;
	q.push({0, 0, 0});
	while (!q.empty()) {
		int x, y, jump;
		tie(x, y, jump) = q.front();
		
		int time = state[jump][x][y];
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (out_of_bound(nx, ny) || state[jump][nx][ny] != NOT_VISITED)
				continue;
			if (nx == h - 1 && ny == w - 1) {
				cout << time;
				return 0;
			}
			q.push({nx, ny, jump});
			state[jump][nx][ny] = time + 1;
		}
		
		if (jump >= k)
			continue;
		for (int i = 4; i < 12; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (out_of_bound(nx, ny) || state[jump + 1][nx][ny] != NOT_VISITED)
				continue;
			if (nx == h - 1 && ny == w - 1) {
				cout << time;
				return 0;
			}
			q.push({nx, ny, jump + 1}); 
			state[jump + 1][nx][ny] = time + 1;
		}
	}
	cout << -1;
}
