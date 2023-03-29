#include <bits/stdc++.h>
using namespace std;

int v[1000001];
queue<int> q;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n, start, target, up, down;
	
	cin >> n >> start >> target >> up >> down;
	memset(v, -1, sizeof(v));
	v[start] = 0;
	q.push(start);
	if (target == start) {
		cout << "0";
		return 0;
	}
	while(!q.empty()) {
		int floor = q.front();
		q.pop();
		int count = v[floor] + 1;
		if (floor + up == target || floor - down == target) {
			cout << count;
			return 0;
		}
		
		int temp = floor + up;
		if (temp <= n && v[temp] == -1) {
			v[temp] = count;
			q.push(temp);
		}
		temp = floor - down;
		if (temp > 0 && v[temp] == -1) {
			v[temp] = count;
			q.push(temp);
		}
	}
	
	cout << "use the stairs";
}
