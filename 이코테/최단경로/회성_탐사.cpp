#include <iostream>
#include <queue>
#include <tuple>
#include <vector>

#define INF 2147483647

using namespace std;
int board[125][125], dist[125][125], dx[4] = {1, -1, 0, 0}, dy[4] = {0, 0, 1, -1};

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int t;
	
	cin >> t;
	while (t--) {
		priority_queue<tuple<int, int, int>> q;
		int n, distance, x, y;
		
		cin >> n;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> board[i][j];
				dist[i][j] = INF;
			}	
		}
		
		dist[0][0] = board[0][0];
		q.push(make_tuple(-dist[0][0], 0, 0));
		
		while (!q.empty()) {
			tie(distance, x, y) = q.top();
			distance *= -1;
			q.pop();
			
			if (dist[x][y] < distance)
				continue;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				int cost = distance + board[nx][ny];
				if (cost < dist[nx][ny]) {
					dist[nx][ny] = cost;
					q.push(make_tuple(-cost, nx, ny));
				}
			}
		}
		
		cout << dist[n - 1][n - 1] << "\n";
	}
	
}