#include <iostream>
#include <queue>

using namespace std;
bool visit[200001];
deque<pair<int, int>> q;

int main() {
	int start, target;
	
	cin >> start >> target;
	if (start == target) {
		cout << "0";
		return 0;
	}
	q.push_back({start, 0});
	visit[start] = 1;
	int max_dist = target * 2;
	
	while (!q.empty()) {
		int dist = q.front().first;
		int time = q.front().second;
		q.pop_front();
		
		if (dist * 2 == target) {
			cout << time;
			break;
		}
		if (dist + 1 == target || dist -1 == target) {
			cout << time + 1;
			break;
		}
		int temp = dist * 2;
		if (temp < max_dist && !visit[temp]) {
			visit[temp] = true;
			q.push_front({temp, time});
		}
		temp = dist + 1;
		if (temp < target && !visit[temp]) {
			visit[temp] = true;
			q.push_back({temp, time + 1});
		}
		temp = dist - 1;
		if (temp >= 0 && !visit[temp]) {
			visit[temp] = true;
			q.push_back({temp, time + 1});
		}
	}
	
}