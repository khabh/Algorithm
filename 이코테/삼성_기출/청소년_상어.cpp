#include <iostream>
#include <iomanip>
#include <algorithm>
#define X first
#define Y second

using namespace std;

int result = 0, dx[8] = {-1, -1, 0, 1, 1, 1, 0, -1}, dy[8] = {0, -1, -1, -1, 0, 1, 1, 1};

pair<int, int> find_fish(int num, int board[4][4][2]) {
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			if (board[i][j][0] == num)
				return {i, j};
		}
	}
	
	return {-1, -1};
}

void dfs(int prev[4][4][2], pair<int, int> position, int sum) {
	int x = position.X, y = position.Y, board[4][4][2];

	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			board[i][j][0] = prev[i][j][0];
			board[i][j][1] = prev[i][j][1];
		}
	}
	
	sum += board[x][y][0];
	board[x][y][0] = 0;
	
	for (int i = 1; i < 17; i++) {
		auto fish = find_fish(i, board);
		if (fish.X == -1)
			continue;
		int a = fish.X, b = fish.Y, d = board[a][b][1];
		for (int j = 0; j < 8; j++) {
			int nx = a + dx[d], ny = b + dy[d];
			if (nx < 0 || ny < 0 || nx > 3 || ny > 3 || (nx == x && ny == y)) {
				d = (d + 1) % 8;
				continue;
			}
			if (board[nx][ny][0]) {
				board[a][b][0] = board[nx][ny][0];
				board[a][b][1] = board[nx][ny][1];
			}
			else {
				board[a][b][0] = 0;
			}
			board[nx][ny][0] = i;
			board[nx][ny][1] = d;
			break;
		}
	}
	
	int dir = board[x][y][1];
	bool is_end = true;
	
	for (int i = 1; i < 4; i++) {
		int nx = x + dx[dir] * i, ny = y + dy[dir] * i;
		if (nx < 0 || ny < 0 || nx > 3 || ny > 3)
			break;
		if (!board[nx][ny][0])
			continue;
		is_end = false;
		dfs(board, {nx, ny}, sum);
	}
	
	if (is_end) {
		result = max(result, sum);
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int board[4][4][2];
	
	for (int i = 0; i < 4; i++) {
		int d;
		for (int j = 0; j < 4; j++) {
			cin >> board[i][j][0];
			cin >> d;
			board[i][j][1] = --d;
		}
	}
	
	dfs(board, {0, 0}, 0);
	
	cout << result;
}