#include <iostream>
#include <queue>

using namespace std;

int board[101][101];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int k, n, l, t = 0, dir = 0, x = 1, y = 1, tail_x = 1, tail_y = 1;
	queue<pair<int, int>> move;
	queue<int> tail_move;
	cin >> n >> k;
	
	while (k--) {
		int x, y;
		cin >> x >> y;
		board[x][y] = 2;
	}
	cin >> l;
	while (l--) {
		int t;
		char d;
		cin >> t >> d;
		if (d == 'D') 
			move.push({t, 1});
		else 
			move.push({t, -1});
	}
	
	board[1][1] = 1;
	while (1) {
		t++;
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		tail_move.push(dir);
		if (nx < 1 || ny < 1 || nx > n || ny > n || board[nx][ny] == 1) {
			cout << t;
			return 0;
		}	
		x = nx;
		y = ny;
		if (!board[x][y]) {
			board[tail_x][tail_y] = 0;
			int temp = tail_move.front();
			tail_move.pop();
			tail_x += dx[temp];
			tail_y += dy[temp];
		}
		board[x][y] = 1;
		if (!move.empty() && move.front().first == t) {
			dir += move.front().second;
			if (dir == 4)
				dir = 0;
			else if (dir == -1)
				dir = 3;
			move.pop();
		}
	}
}