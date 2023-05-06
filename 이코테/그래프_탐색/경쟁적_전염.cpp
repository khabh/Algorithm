#include <iostream>
#include <queue>

using namespace std;
bool virus[1001];
int board[201][201];
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, K, s, resultX, resultY;
	queue<pair<int, int>> nodes;
	cin >> n >> K;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			cin >> board[i][j];
	
	cin >> s >> resultX >> resultY;
	for (int k = 1; k <= K; k++) {
	    for (int i = 1; i <= n; i++) {
		    for (int j = 1; j <= n; j++) {
		        if (board[i][j] == k)
		            nodes.push({i, j});
		    }
	    }
	}
	
	while (s--) {
		queue<pair<int, int>> next_nodes;
		while (!nodes.empty()) {
		    int x = nodes.front().first;
		    int y = nodes.front().second;
		    nodes.pop();
		    for (int k = 0; k < 4; k++) {
				int X = x + dx[k];
				int Y = y + dy[k];
				if (X < 1 || Y < 1 || X > n || Y > n || board[X][Y])
					continue;
				board[X][Y] = board[x][y];
				next_nodes.push({X, Y});
		    }
		}
		nodes = next_nodes;
	}
	
	cout << board[resultX][resultY];
}