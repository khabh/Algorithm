#include <iostream>
#include <deque>

using namespace std;
int w, h, result;
char board[20][20];
int dx[4] = {0, 1, 1, 1};
int dy[4] = {1, 0, 1, -1};
int block[2][2] = {
	{1, 2}, 
	{2, 3}
};

void dfs() {
	bool movable[4];
	int a = -1, b = -1;
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			if (board[i][j] == '.') {
				a = i;
				b = j;
				board[i][j] = '#';
				break;
			}
		}
		if (a != -1)
			break;
	}
	
	if (a == -1) {
		result++;
		return;
	}
	
	for (int i = 0; i < 4; i++) {
		int x = a + dx[i];
		int y = b + dy[i];
		if (x < 0 || y < 0 || x >= h || y >= w || board[x][y] == '#')
			movable[i] = false;
		else
			movable[i] = true;
	}

	for (int i = 0; i < 2; i++) {
		if (!movable[i])
			continue;
		int x1 = a + dx[i];
		int y1 = b + dy[i];
		board[x1][y1] = '#';
		for (int j : block[i]) {
			if (!movable[j])
				continue;
			int x2 = a + dx[j];
			int y2 = b + dy[j];
			board[x2][y2] = '#';
			dfs();
			board[x2][y2] = '.';
		}
		board[x1][y1] = '.';
	}
	
	board[a][b] = '.';
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int c;
	cin >> c;
	
	while (c--) {
		result = 0;
		cin >> h >> w;
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				cin >> board[i][j];
		dfs();
		cout << result << "\n";
	}
}