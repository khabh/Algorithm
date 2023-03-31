#include <iostream>
#include <queue>
#include <deque>

using namespace std;
int want[100001];
bool visit[100001];
int n;

int bfs(int start) {
	queue<int> q;
	deque<int> temp;
	q.push(start);
	temp.push_back(start);
	visit[start] = true;
	
	while(!q.empty()) {
		int node = q.front();
		q.pop();
		int next_node = want[node];
		if (visit[next_node]) {
			int count = 0;
			while (!temp.empty()) {
				count++;
				if (temp.back() == next_node) {
					return count;
				}
				temp.pop_back();
			}
			return 0;
		}
		visit[next_node] = true;
		q.push(next_node);
		temp.push_back(next_node);
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int t;
	cin >> t;
	
	while(t--) {
		cin >> n;
		int count = n;
		for (int i = 1; i <= n; i++) {
			cin >> want[i];
			visit[i] = false;
		}
		for (int i = 1; i <= n; i++) {
			if (!visit[i]) {
				count -= bfs(i);
			}
		}
		cout << count << "\n";
	}
}