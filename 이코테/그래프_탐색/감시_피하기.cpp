#include <iostream>
#include <vector>

using namespace std;
int n;
char board[6][6];
vector<pair<int, int>> students;

bool avoided() {
	for (auto student : students) {
		int x = student.first, y = student.second;
		for (int i = x + 1; i < n; ++i) {
			if (board[i][y] == 'O')
				break;
			if (board[i][y] == 'T')
				return false;
		}
		for (int i = x - 1; i >= 0; --i) {
			if (board[i][y] == 'O')
				break;
			if (board[i][y] == 'T')
				return false;
		}
		for (int i = y + 1; i < n; ++i) {
			if (board[x][i] == 'O')
				break;
			if (board[x][i] == 'T')
				return false;
		}
		for (int i = y - 1; i >= 0; --i) {
			if (board[x][i] == 'O')
				break;
			if (board[x][i] == 'T')
				return false;
		}
	}
	return true;
}

bool bfs(int x, int y, int count) {
	if (count == 3) {
		return avoided();
	}
	
	for (int i = y; i < n; i++) {
		if (board[x][i] == 'X') {
			board[x][i] = 'O';
			if (bfs(x, i, count + 1)) 
				return true;
			board[x][i] = 'X';
		}
	}
	
	for (int i = x + 1; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			if (board[i][j] == 'X') {
				board[i][j] = 'O';
				if (bfs(i, j, count + 1))
					return true;
				board[i][j] = 'X';
			}
		}
	}
	
	return false;
}

int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	cin >> n;
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			cin >> board[i][j];
			if (board[i][j] == 'S')
				students.push_back({i, j});	
		}
	}
	bool result = bfs(0, 0, 0);
	if (result)
		cout << "YES";
	else
		cout << "NO";
}