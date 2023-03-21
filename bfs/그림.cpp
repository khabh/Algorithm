#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int n, m;
char picture[501][501];
int deltaX[4] = {0, 1, -1, 0};
int deltaY[4] = {1, 0, 0, -1};

int bfs(int x,int y) {
	picture[x][y] = '0';
	queue<pair<int, int>> q;
	q.push({x, y});
	int count = 1;
	
	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int X = a + deltaX[i];
			int Y = b + deltaY[i];
			if (X < 0 || X >= n || Y < 0 || Y >= m)
				continue;
			if (picture[X][Y] == '1') {
				count++;
				picture[X][Y] = '0';
				q.push({X, Y});
			}
		}
	}
	return count;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int result = 0, count = 0;
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) 
			cin >> picture[i][j];
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (picture[i][j] == '1') {
				count++;
				result = max(result, bfs(i, j));
			}
		}	
	}
	
	cout << count << "\n";
	cout << result;
}
