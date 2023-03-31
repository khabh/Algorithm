#include <iostream>
#include <queue>

using namespace std;

int n, m;
int ice_count = 0;
int ice[300][300];
bool visit[300][300];
int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};

bool out_of_bound(int x, int y) {
	return x < 0 || y < 0 || x >= n || y >= m;
}

void bfs(int a, int b) {
	queue<pair<int, int>> q;
	q.push({a, b});
	visit[a][b] = true;
	
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (out_of_bound(nx, ny) || visit[nx][ny] || !ice[nx][ny])
				continue;
			q.push({nx, ny});
			visit[nx][ny] = true;
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	cin >> n >> m;
	int year = 1;
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int state;
			cin >> state;
			if (state) 
				ice_count++;
			else 
				visit[i][j] = true;
			ice[i][j] = state;
		}
	}
	
	while (ice_count) {
		int temp = 0;
		int temp_ice[300][300];
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < m; j++)
				temp_ice[i][j] = ice[i][j];
			
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ice[i][j]) {
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (out_of_bound(nx, ny) || temp_ice[nx][ny])
							continue;
						ice[i][j]--;
					}
					if (ice[i][j] <= 0) {
						ice[i][j] = 0;
						ice_count--;
					}	
					else
						visit[i][j] = false;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ice[i][j] && !visit[i][j]) {
					temp++;
					bfs(i, j);	
				}
			}
		}
		
		if (temp >= 2) {
			cout << year;
			return 0;
		}
		year++;
	}
	
	cout << "0";
}