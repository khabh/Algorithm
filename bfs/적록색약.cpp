#include <iostream>
#include <queue>

using namespace std;

int n;
char colors[101][101];
int deltaX[4] = {0, 0, -1, 1};
int deltaY[4] = {1, -1, 0, 0};

void bfs(int i, int j) {
	queue<pair<int, int>> q;
	char find = colors[i][j];
	char change = 'A'; // R, G
	if (find == 'B') // B
		change = 'C'; 
	else if (find == 'C')
		change = 'D';
	else if (find == 'A')
		change = 'E';
	colors[i][j] = change;
	q.push({i, j});
	while (!q.empty()) {
		auto site = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int x = site.first + deltaX[i];
			int y = site.second + deltaY[i];
			if (x < 0 || y < 0 || x >= n || y >= n || colors[x][y] != find)
				continue;
			colors[x][y] = change;
			q.push({x, y});
		}
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int result1 = 0;
	int result2 = 0;
	cin >> n;
	
	for (int i = 0; i < n; i++){
		string c;
		cin >> c;
		for (int j = 0; j < n; j++) {
			colors[i][j] = c[j];
		}
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (colors[i][j] != 'A' && colors[i][j] != 'C') {
				result1++;
				bfs(i, j);
			}
		}
	}
	
	
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (colors[i][j] != 'D' && colors[i][j] != 'E') {
				result2++;
				bfs(i, j);
			}
		}
	}
	
	cout << result1 << " " << result2;
}
