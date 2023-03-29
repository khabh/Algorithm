//#include <iostream>
//#include <queue>
//#include <tuple>
//
//using namespace std;
//queue<tuple<int, int, int>> q;
//int m, n, h;
//int count = 0;
//int deltaX[4] = {0, 0, 1, -1};
//int deltaY[4] = {1, -1, 0, 0};
//int tomatoes[100][100][100];
//
//void bfs() {
//	queue<tuple<int, int, int>> temp;
//	
//	while (!q.empty()) {
//		int x = get<0>(q.front());
//		int y = get<1>(q.front());
//		int z = get<2>(q.front());
//		
//		q.pop();
//		
//		for (int i = 0; i < 4; i++) {
//			int X = deltaX[i] + x;
//			int Y = deltaY[i] + y;
//			if (X < 0 || Y < 0 || X >= n || Y >= m || tomatoes[z][X][Y] != 0)
//				continue;
//			tomatoes[z][X][Y] = 1;
//			count--;
//			temp.push(make_tuple(X, Y, z));
//		}
//		
//		if (z > 0 && tomatoes[z- 1][x][y] == 0) {
//			tomatoes[z - 1][x][y] = 1;
//			count--;
//			temp.push(make_tuple(x, y, z - 1));
//		}
//		
//		if (z + 1 < h && tomatoes[z + 1][x][y] == 0) {
//			tomatoes[z + 1][x][y] = 1;
//			count--;
//			temp.push(make_tuple(x, y, z + 1));
//		}
//	}
//	
//	q = temp;
//}
//
//int main() {
//	ios::sync_with_stdio(0);
//	cin.tie(0);
//	cout.tie(0);
//	int result = 0;
//	
//	cin >> m >> n >> h;
//	for (int i = 0; i <h; i++) {
//		for (int j = 0; j < n; j++) {
//			for (int k = 0; k < m; k++) {
//				int t;
//				cin >> t;
//				if (!t)
//					count++;
//				else if (t == 1)
//					q.push(make_tuple(j, k, i));
//				tomatoes[i][j][k] = t;	
//			}
//		}
//	}
//	
//	while (!q.empty() && count > 0) {
//		result++;
//		bfs();
//	}
//	
//	if (count > 0)
//		cout << -1;
//	else 
//		cout << result;
//} 

#include <iostream>
#include <queue>

using namespace std;
queue<pair<pair<int, int>, int>> q;
int m, n, h;
int count = 0;
int deltaX[4] = {0, 0, 1, -1};
int deltaY[4] = {1, -1, 0, 0};
int tomatoes[100][100][100];

void bfs() {
	queue<pair<pair<int, int>, int>> temp;
	
	while (!q.empty()) {
		auto site = q.front().first;
		int z = q.front().second;
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int x = deltaX[i] + site.first;
			int y = deltaY[i] + site.second;
			if (x < 0 || y < 0 || x >= n || y >= m || tomatoes[z][x][y] != 0)
				continue;
			tomatoes[z][x][y] = 1;
			count--;
			temp.push({{x, y}, z});
		}
		
		if (z > 0 && tomatoes[z- 1][site.first][site.second] == 0) {
			tomatoes[z - 1][site.first][site.second] = 1;
			count--;
			temp.push({{site.first, site.second}, z - 1});
		}
		
		if (z + 1 < h && tomatoes[z + 1][site.first][site.second] == 0) {
			tomatoes[z + 1][site.first][site.second] = 1;
			count--;
			temp.push({{site.first, site.second}, z + 1});
		}
	}
	
	q = temp;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	int result = 0;
	
	cin >> m >> n >> h;
	for (int i = 0; i <h; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				int t;
				cin >> t;
				if (!t)
					count++;
				else if (t == 1)
					q.push({{j, k}, i});
				tomatoes[i][j][k] = t;	
			}
		}
	}
	
	while (!q.empty() && count > 0) {
		result++;
		bfs();
	}
	
	if (count > 0)
		cout << -1;
	else 
		cout << result;
} 
