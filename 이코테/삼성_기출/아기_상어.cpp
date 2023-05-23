#include <iostream>
#include <string.h>
#include <queue>
#include <vector>

using namespace std;
int n, board[20][20], dy[4] = {0, -1, 1, 0}, dx[4] = {-1, 0, 0, 1}, x = -1, y;
bool visit[20][20];

struct compare {
	bool operator()(pair<int, int> a, pair<int, int> b) {
		if (a.first != b.first)
			return a.first > b.first;
		return a.second > b.second;
	}
};

int bfs(int size) {
	memset(visit, false, sizeof(visit));
	int t = 0;
	priority_queue<pair<int, int>, vector<pair<int, int>>, compare> q;
	q.push({x, y});
	visit[x][y] = true;
	
	while (!q.empty()) {
		priority_queue<pair<int, int>, vector<pair<int, int>>, compare> tmp;
		
		while (!q.empty()) {
			int a = q.top().first, b = q.top().second;
			if (board[a][b] && board[a][b] < size) {
				x = a;
				y = b;
				board[a][b] = 0;
				return t;
			}
			q.pop();
		
			for (int i = 0; i < 4; i++) {
				int nx = a + dx[i], ny = b + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n || visit[nx][ny] || board[nx][ny] > size)
					continue;
				tmp.push({nx, ny});
				visit[nx][ny] = true;
			}
		}
		t++;
		q = tmp;
	}
	
	return 0;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int size = 2, eaten = 0, time = 0;
	
	cin >> n;
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < n; j++) 
			cin >> board[i][j];
	
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (board[i][j] == 9) {
				x = i;
				y = j;
				board[x][y] = 0;
				break;
			}
		}
		if (x != -1)
			break;
	}
	
	while (x > -1) {
		int result = bfs(size);
		if (!result) 
			break;
		time += result;
		eaten++;
		if (eaten == size) {
			size++;
			eaten = 0;
		}
	}
		
	cout << time;
}