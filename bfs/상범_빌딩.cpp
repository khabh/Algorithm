#include <iostream>
#include <queue>
#define X first
#define Y second
#define Z first
#define TIME second

using namespace std;

int height, a, b;
char building[30][30][30];
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
int startX, startY, startZ, targetX, targetY, targetZ;

void bfs() {
	queue<pair<pair<int, int>, pair<int, int>>> q;
	building[startZ][startX][startY] = '.';
	q.push({{startX, startY}, {startZ, 0}});
	
	while (!q.empty()) {
		int x = q.front().first.X;
		int y = q.front().first.Y;
		int z = q.front().second.Z;
		int t = q.front().second.TIME;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx == targetX && ny == targetY && targetZ == z) {
				cout << "Escaped in " <<  t + 1 << " minute(s).\n";
				return;
			}	
			if (nx < 0 || ny < 0 || nx >= a || ny >= b || building[z][nx][ny] != '.')
				continue;
			building[z][nx][ny] = '#';
			q.push({{nx, ny}, {z, t + 1}});
		}
		if (x == targetX && y == targetY && (z - 1 == targetZ || z + 1 == targetZ)) {
			cout << "Escaped in " <<  t + 1 << " minute(s).\n";
			return;
		}
			
		if (z > 0 && building[z - 1][x][y] == '.') {
			building[z - 1][x][y] = '#';
			q.push({{x, y}, {z - 1, t + 1}});
		}
		if (z + 1 < height && building[z + 1][x][y] == '.') {
			building[z + 1][x][y] = '#';
			q.push({{x, y}, {z + 1, t + 1}});
		}
	}
	
	cout << "Trapped!\n";
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	while (1) {
		cin >> height >> a >> b;
		if (!height && !a && !b)
			return 0; 
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < a; j++) {
				string s;
				cin >> s;
				for (int k = 0; k < b; k++) {
					building[i][j][k] = s[k];
					if (s[k] == 'S') {
						startX = j;
						startY = k;
						startZ = i;
					}
					else if (s[k] == 'E') {
						targetX = j;
						targetY = k;
						targetZ = i;
					}
				}
			}
		}
		bfs();
	}
}
