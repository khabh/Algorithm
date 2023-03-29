#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#define X first
#define Y second

using namespace std;
int map[100][100] = {};
int M, N, K;
int deltaX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};

int bfs(int a, int b) {
	queue<pair<int, int>> q;
	int count = 1;
	q.push({a, b});
	while (!q.empty()) {
		int x = q.front().X;
		int y = q.front().Y;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int X = x + deltaX[i];
			int Y = y + deltaY[i];
			if (X < 0 || Y < 0 || X >= M || Y >= N || map[X][Y])
				continue;
			map[X][Y] = 1;
			count++;
			q.push({X, Y});
		}
	}
	
	return count;
}

int main() {
	cin >> M >> N >> K;
	vector<int> result;
	
	while (K--) {
		int x1, y1, x2, y2;
		cin >> x1 >> y1 >> x2 >> y2;
		
		for (int i = y1; i < y2; i++) {
			int x = M - i - 1;
			for (int j = x1; j < x2; j++)
				map[x][j] = 1;
		}
	}
	
	for(int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (!map[i][j]) {
				map[i][j] = 1;
				result.push_back(bfs(i, j));
			}
		}
	}
	
	sort(result.begin(), result.end());
	cout << result.size() << "\n";
	for (auto& i: result) {
		cout << i << " ";
	}
} 
