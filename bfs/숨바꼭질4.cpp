#include <iostream>
#include <queue>
#include <stack>

using namespace std;

int prev_dist[200001];
int start, target;
queue<pair<int, int>> q;

void print_result() {
	stack<int> result;
	int dist = target;
	while (dist != -2) {
		result.push(dist);
		dist = prev_dist[dist];
	}
	while (!result.empty()) {
		cout << result.top() << " ";
		result.pop();
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> start >> target;
	
	if (start == target) {
		cout << "0\n" << start;
		return 0;
	}
	for (int i = 0; i < 200001; i++) 
		prev_dist[i] = -1;
	q.push({start, 0});
	prev_dist[start] = -2;
	
	while (!q.empty()) {
		int dist = q.front().first;
		int time = q.front().second + 1;
		q.pop();
		if (dist - 1 == target || dist + 1 == target || dist * 2 == target) {
			prev_dist[target] = dist;
			cout << time << "\n";
			print_result();
			return 0;
		}
		int temp = dist * 2;
		if (temp <= 200001 && prev_dist[temp] == -1) {
			prev_dist[temp] = dist;
			q.push({temp, time});
		}
		temp = dist + 1;
		if (temp <= 200001 && prev_dist[temp] == -1) {
			prev_dist[temp] = dist;
			q.push({temp, time});
		}
		temp = dist - 1;
		if (temp >= 0 && prev_dist[temp] == -1) {
			prev_dist[temp] = dist;
			q.push({temp, time});
		}
	}
}