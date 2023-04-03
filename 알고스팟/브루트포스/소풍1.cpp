#include <iostream>

using namespace std;
int result, n;
bool is_friend[10][10];
bool visit[10];

void bfs() {
	int need_match = -1;
	for (int i = 0; i < n; i++) {
		if (!visit[i]) {
			need_match = i;
			visit[i] = true;
			break;
		}
	}
	
	if (need_match == -1) {
		result++;
		return;
	}
	
	for (int i = need_match + 1; i < n; i++) {
		if (is_friend[need_match][i] && !visit[i]) {
			visit[i] = true;
			bfs();
			visit[i] = false;
		}
	}
	
	visit[need_match] = false;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int c;
	cin >> c;
	
	while (c--) {
		int m;
		result = 0;
		cin >> n >> m;
	
		for (int i = 0; i < n; i++) {
			visit[i] = false;
			for (int j = 0; j < n; j++)
				is_friend[i][j] = false;
		}

		while (m--) {
			int a, b;
			cin >> a >> b;
			is_friend[a][b] = true;
			is_friend[b][a] = true;			
		}
	
		bfs();
		cout << result << "\n";
	}
}