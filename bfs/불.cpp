#include <iostream>
#include <queue>
#define X first
#define Y second

using namespace std;

int deltaX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};
char map[1000][1000];
int w, h, t;
queue<pair<int, int>> fire;

void burn() {
	queue<pair<int, int>> temp;
	while (!fire.empty()) {
		auto site = fire.front();
		fire.pop();
		for (int i = 0; i < 4; i++) {
			int x = site.X + deltaX[i];
			int y = site.Y + deltaY[i];
			if (x < 0 || y < 0 || x >= h || y >= w || map[x][y] == '#' || map[x][y] == '*')
				continue;
			temp.push({x, y});
			map[x][y] = '*';
		}
	}
	
	fire = temp;
}

int bfs() {
	map[1000][1000] = {};
	queue<pair<pair<int, int>, int>> q;
	fire = queue<pair<int, int>>();
	t = 0;
	cin >> w >> h;
	
	for (int i = 0; i < h; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < w; j++) {
			map[i][j] = s[j];
			if (s[j] == '*')
				fire.push({i, j});
			else if (s[j] == '@')
				q.push({{i, j}, 0});
		}
	}
	
	while (!q.empty()) {
		auto site = q.front().first;
		int time = q.front().second;
		q.pop();
		
		if (t == time) {
			burn();
			t++;
		}
		
		for (int i = 0; i < 4; i++) {
			int x = site.X + deltaX[i];
			int y = site.Y + deltaY[i];
			if (x < 0 || y < 0 || x >= h || y >= w)
				return time + 1;
			if (map[x][y] == '.') {
				q.push({{x, y}, time + 1});
				map[x][y] = 'V';
			}
		}
	}
	
	return 0;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int t;
	cin >> t;
	while (t--) {
		int result = bfs();
		if (!result)
			cout << "IMPOSSIBLE\n";
		else 
			cout << result << "\n";
	}
} 
