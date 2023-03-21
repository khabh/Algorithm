#include <iostream>
#include <queue>
using namespace std;

int N, M;
char maze[100][100];
int deltaX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};

int solve() {
	queue<pair<pair<int, int>, int>> q;
	maze[0][0] = '0';
	q.push({{0, 0}, 1});
	
	while (!q.empty()) {
		int count = q.front().second;
		auto site = q.front().first;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int X = site.first + deltaX[i];
			int Y = site.second + deltaY[i];
			if (X < 0 || X > N || Y < 0 || Y > M || maze[X][Y] == '0')
				continue;
			if (X == N && Y == M)
				return count + 1;
			maze[X][Y] = '0';
			q.push({{X, Y}, count + 1});
		}
	}
}


int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	cin >> N >> M;
	N--;
	M--;
	for (int i = 0; i <= N; i++) {
		string path;
		cin >> path;
		for (int j = 0; j <= M; j++) {
			maze[i][j] = path[j];
		}
	}
	cout << solve();
}
