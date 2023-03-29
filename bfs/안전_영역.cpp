#include <iostream>
#include <queue>
#include <string.h>

using namespace std;
int height[100][100];
bool visit[100][100];
int n;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

void bfs(int x, int y) {
	queue<pair<int, int>> q;
	q.push({x, y});
	visit[x][y] = true;
	
	while(!q.empty()) {
		auto site = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nextX = site.first + dx[i];
			int nextY = site.second + dy[i];
			if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n || !height[nextX][nextY] || visit[nextX][nextY])
				continue;
			visit[nextX][nextY] = true;
			q.push({nextX, nextY});
		}
	}
}

int main() {
	int min_h = 101, max_h = 0;
	int result = 1;
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int h;
			cin >> h;
			height[i][j] = h;
			if (h < min_h)
				min_h = h;
			if (h > max_h)
				max_h = h; 
		}
	}
	
	for (int h = min_h; h < max_h; h++) {
		int count = 0;
		memset(visit, false, sizeof(visit));
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (height[i][j] <= h) {
					height[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (height[i][j] && !visit[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
		if (result < count)
			result = count;
	}
	cout << result;
}
