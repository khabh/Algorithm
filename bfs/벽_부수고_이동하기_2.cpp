#include <iostream>
#include <queue>
#include <tuple>

using namespace std;
int n, m, k, time[11][1000][1000];
int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};
char board[1000][1000];
queue<tuple<int, int, int>> q;

int move(int x, int y, int count) {
	int t = time[count][x][y] + 1;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx == n && ny == m)
			return t;
 		if (nx < 0 || ny < 0 || nx > n || ny > m || board[nx][ny] == '1' || time[count][nx][ny])
 			continue;
 		time[count][nx][ny] = t;
 		q.push({nx, ny, count});
	}
	
	return 0;
}

int move_or_break(int x, int y, int count) {
	int t = time[count][x][y] + 1;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx == n && ny == m)
			return t;
		if (nx < 0 || ny < 0 || nx > n || ny > m)
 			continue;
 		if (board[nx][ny] == '1' && !time[count + 1][nx][ny]) {
 			time[count + 1][nx][ny] = t;
 			q.push({nx, ny, count + 1});
		}
		if (board[nx][ny] == '0' && !time[count][nx][ny]) {
			time[count][nx][ny] = t;
 			q.push({nx, ny, count});	
		}
	}
	
	return 0;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> m >> k;
	
	if (n == 1 && m == 1) {
		cout << 1;
		return 0;
	}
	
	n--; m--;
	time[0][0][0] = 1;
	q.push({0, 0, 0});
	for (int i = 0; i <= n; i++) 
		for (int j = 0; j <= m; j++)
			cin >> board[i][j];
	
	while (!q.empty()) {
		int x, y, count, result;
		tie(x, y, count) = q.front();
		q.pop();
		if (count >= k)
			result = move(x, y, count);
		else 
			result = move_or_break(x, y, count);
		if (result) {
			cout << result;
			return 0;
		}
	}
	
	cout << "-1";
}