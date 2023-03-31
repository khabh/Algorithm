#include <bits/stdc++.h>
#define X first
#define Y second

using namespace std;
int n;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
int min_size = 20000;
char state[100][100];
deque<deque<pair<int, int>>> bridge_maker;

bool is_coast(int x, int y) {
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (0 > nx || 0 > ny || nx >= n || ny >= n)
			continue;
		if (state[nx][ny] == '0')
			return true;
	}
	return false;			
}

void bfs(int a, int b) {
	queue<pair<int, int>> q;
	deque<pair<int, int>> coast;
	q.push({a, b});
	state[a][b] = '2';
	if (is_coast(a, b)) 
		coast.push_back({a, b});
	
	while (!q.empty()) {
		int x = q.front().X;
		int y = q.front().Y;
		
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 > nx || 0 > ny || nx >= n || ny >= n || state[nx][ny] != '1')
				continue;
		
			state[nx][ny] = '2';
			q.push({nx, ny});
			if (is_coast(nx, ny))
				coast.push_back({nx, ny});
		}
	}
	bridge_maker.push_back(coast);
}

void calc_bridge_size(int first, int second) {
	for (auto state1 : bridge_maker[first]) {
		int x1 = state1.X;
		int y1 = state1.Y;
		for (auto state2 : bridge_maker[second]) {
			int x2 = state2.X;
			int y2 = state2.Y;
			int bridge_size = (abs(x1 - x2) + abs(y1 - y2) - 1);
			if (min_size > bridge_size)
				min_size = bridge_size;
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	cin >> n;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			cin >> state[i][j];
	
				
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (state[i][j] == '1') {
				bfs(i, j);
			}
		}
	}

	for (int i = 0; i < bridge_maker.size() - 1; i++) {
		for (int j = i + 1; j < bridge_maker.size(); j++) {
			calc_bridge_size(i, j);
		}
	}
	
	cout << min_size;
}


