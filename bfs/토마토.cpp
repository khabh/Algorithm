#include <iostream>
#include <queue>
using namespace std;

int n, m, count = 0;
queue<pair<pair<int, int>, int>> q;
int tomato[1002][1002];
int deltaX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};

int bfs() {
	while(!q.empty()) {
		auto site = q.front().first;
		int day = q.front().second;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int X = site.first + deltaX[i];
			int Y = site.second + deltaY[i];
			if (tomato[X][Y] == 0) {
				count--;
				if (!count) {
					return day + 1;
				}
				q.push({{X, Y}, day + 1});
				tomato[X][Y] = 1;
			}
		}
	}
	
	if (count) {
		return -1;
	}
}


int main() {
	cin >> n >> m;
	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> tomato[i][j];
		}
	}
	
	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <= n; j++) {
			if (tomato[i][j] == 1) {
				q.push({{i, j}, 0});
				continue;
			}	
			if (tomato[i][j] == 0) 
				count++;
		}
	}
	
	for (int i = 1; i <= n; i++) {
		tomato[0][i] = -1;
		tomato[m + 1][i] = -1;
	}
	
	for (int i = 1; i <= m; i++) {
		tomato[i][0] = -1;
		tomato[i][n + 1] = -1;
	}
	
	if (!count) {
		cout << 0;
		return 0;
	}
	cout << bfs();
}
