#include <iostream>
#include <queue>
#include <vector>
#include <string.h>

using namespace std;
int degree[501];
bool teams[501][501];

void solve() {
	fill(degree, degree + 501, 0);
    for (int i = 0; i < 501; i++) {
        fill(teams[i], teams[i] + 501, false);
    }
	queue<int> q;
	vector<int> result;
	int n, m, prev[500];
	
	cin >> n;
		
	for (int i = 0; i < n; i++)
		cin >> prev[i];
		
	for (int i = 0; i < n; i++) {
		int a = prev[i];
		degree[a] = i;
		for (int j = i + 1; j < n; j++) {
			teams[a][prev[j]] = true;
		}
	}
		
	cin >> m;
	while (m--) {
		int a, b;
		cin >> a >> b;
		if (!teams[b][a]) {
			int temp = a;
			a = b;
			b = temp;
		}	
		teams[b][a] = false;
		teams[a][b] = true;
		degree[a]--;
		degree[b]++;
	}
	
	for (int i = 1; i <= n; i++) {
		if (!degree[i])
			q.push(i);
	}
	
	for (int i = 0; i < n; i++) {
		if (q.size() == 0) {
			cout << "IMPOSSIBLE\n";
			return;
		}
		if (q.size() >= 2) {
			cout << "?\n";
			return;
		}
		int current = q.front();
		result.push_back(current);
		q.pop();
		for (int j = 1; j <= n; j++) {
			if (teams[current][j]) {
				degree[j]--;
				if (!degree[j])
					q.push(j);
			}	
		}
	}
	
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int t;
	cin >> t;
	
	while (t--) {
		solve();
	}
}