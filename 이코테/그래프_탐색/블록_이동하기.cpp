#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;
int dx[4] = {1, 0, -1, 0}, dy[4] = {0, 1, 0, -1}, n;
queue<tuple<int, int, int>> q;
bool visit[4][100][100] = {false};

bool turn(int dir, int x, int y, vector<vector<int>>& board, queue<tuple<int, int, int>> &next_q) {
	for (int i = -1; i <= 1; i++) {
			int next_dir = dir + i;
			if (next_dir == -1)
				next_dir = 3;
			else if (next_dir == 4)
				next_dir = 0;
			int x1 = x + dx[next_dir], y1 = y + dy[next_dir];
			if (x1 < 0 || y1 < 0 || x1 >= n || y1 >= n || board[x1][y1] == 1 || visit[next_dir][x][y] || visit[(next_dir + 2) % 4][x1][y1])
				continue;
			int x2 = x1 + dx[dir], y2 = y1 + dy[dir];
			if (board[x2][y2] == 1)
				continue;
			if (x1 == n - 1 && y1 == n - 1)
				return true;
			visit[next_dir][x][y] = true;
			next_q.push(make_tuple(next_dir, x, y));
	}
	return false;
}

bool bfs(vector<vector<int>>& board) {
	queue<tuple<int, int, int>> next_q;
	while (!q.empty()) {
		int dir, x, y;
		
		tie(dir, x, y) = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int x1 = x + dx[i], y1 = y + dy[i];
			if (x1 < 0 || y1 < 0 || x1 >= n || y1 >= n || board[x1][y1] == 1 || visit[dir][x1][y1])
				continue;
			int x2 = x1 + dx[dir], y2 = y1 + dy[dir];
			if (x2 < 0 || y2 < 0 || x2 >= n || y2 >= n || board[x2][y2] == 1 || visit[(dir + 2) % 4][x2][y2])
				continue;
			if ((x1 == n - 1 && y1 == n - 1) || (x2 == n - 1 && y2 == n - 1))
				return true;
			visit[dir][x1][y1] = true;
			next_q.push(make_tuple(dir, x1, y1));
		}
	
		if (turn(dir, x, y, board, next_q))
			return true;
			
		x += dx[dir];
		y += dy[dir];
		dir = (dir + 2) % 4;
		if (x < 0 || y < 0 || x >= n || y >= n)
			continue;
		if (x == n - 1 && y == n - 1)
			return true;
		if (turn(dir, x, y, board, next_q))
			return true;
		
	}
	
	q = next_q;
	
	return false;
}

int solution(vector<vector<int>> board) {
	int answer = 1;
	q.push(make_tuple(0, 0, 0));
	n = board.size();
	visit[0][0][0] = true;
	while (!bfs(board)) {
		answer++;
	}
	
    return answer;
}

int main() {
	cout << solution({{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}});
}