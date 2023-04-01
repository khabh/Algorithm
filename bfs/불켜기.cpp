#include <iostream>
#include <queue>
#include <deque>
#include <vector>
#define X first 
#define Y second

using namespace std;
int n, m, count = 1;
vector<pair<int, int>> lights[101][101];
int state[101][101]; 
// 0 - 이동할 수 없고 켜지지도 않은 상태
// 1 - 이동할 수 있고 켜진 상태
// 2 - 이동할 수 없고 켜지기만 한 상태
// 3 - 물리적으로 연결되어 있지만 불이 켜지지 않은 상태

int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
queue<pair<int, int>> q;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> m;
	
	while (m--) {
		int x, y, a, b;
		cin >> x >> y >> a >> b;
		lights[x][y].push_back({a, b});
	}
	
	state[1][1] = 1;
	q.push({1, 1});
	while (!q.empty()) {
		int x = q.front().X;
		int y = q.front().Y;
		q.pop();
		for (auto light : lights[x][y]) {
			int a = light.X;
			int b = light.Y;
			if (!state[a][b]) {
				state[a][b] = 2;
				count++;
				continue;
			}
			if (state[a][b] == 3) {
				state[a][b] = 1;
				count++;
				q.push({a, b});
			}
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 1 || ny < 1 || nx > n || ny > n)
				continue;
			if (!state[nx][ny])
				state[nx][ny] = 3;
			else if (state[nx][ny] == 2) {
				state[nx][ny] = 1;
				q.push({nx, ny});
			}
		}
	}
	
	cout << count;
}