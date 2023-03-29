#include <iostream>
#include <queue>
#include <vector>
#include<algorithm>

using namespace std;

int n;
char house[25][25];
int deltaX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};

int bfs(int a, int b) {
	house[a][b] = '0';
	queue<pair<int, int>> q;
	int count = 1;
	q.push({a, b});
	
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int X = deltaX[i] + x;
			int Y = deltaY[i] + y;
			if (X < 0 || Y < 0 || X >= n || Y >= n || house[X][Y] == '0')
				continue;
			house[X][Y] = '0';
			q.push({X, Y});
			count++;
		}
	}
	
	return count;
}

int main() {
	cin >> n;
	vector<int> result;
	
	for (int i = 0; i < n; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < n; j++)
			house[i][j] = s[j];
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (house[i][j] == '1')
				result.push_back(bfs(i, j));
		}
	}

	cout << result.size() << "\n";
	sort(result.begin(), result.end());
	for (auto& count : result)
		cout << count << "\n";
}
