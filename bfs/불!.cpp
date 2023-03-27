#include <iostream>
#include <queue>
using namespace std;

int r, c;
char maze[1001][1001];
int time = 0;
queue<pair<pair<int, int>, int>> q;
queue<pair<int, int>> fire;

int deltaX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};

void bfs_fire() {
	queue<pair<int, int>> temp;
	while (!fire.empty()) {
		auto site = fire.front();
		fire.pop();
		for (int i = 0; i < 4; i++) {
			int X = deltaX[i] + site.first;
			int Y = deltaY[i] + site.second;
			if (0 > X || X >= r || 0 > Y || Y >= c || maze[X][Y] == 'F' || maze[X][Y] == '#')
				continue;
			maze[X][Y] = 'F';
			temp.push({X, Y});
		}
	}
	fire = temp;
}

int bfs() {
	while(!q.empty()) {
		int t = q.front().second;
		auto site = q.front().first;
		if (time == t) {
			bfs_fire();
			time++;
		}
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int X = deltaX[i] + site.first;
			int Y = deltaY[i] + site.second;
			if (0 > X || X >= r || 0 > Y || Y >= c)
				return t + 1;
			if (maze[X][Y] == '.') {
				maze[X][Y] = 'J';
				q.push({{X, Y}, t + 1});
			}
		}
	}
	
	return -1;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	cin >> r >> c;
	for (int i = 0; i < r; i++) {
		string input;
		cin >> input;
		for (int j = 0; j < c; j++) {
			maze[i][j] = input[j];
			if (input[j] == 'F') {
				fire.push({i, j});
				continue;	
			}
			if (input[j] == 'J') {
				q.push({{i, j}, 0});
			}
		}
	}
	int result = bfs();
	if (result == -1)
		cout << "IMPOSSIBLE";
	else 
		cout << result;
}
