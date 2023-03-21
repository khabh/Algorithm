#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	long result = 0;
	cin >> n;
	stack<pair<int, int>> s;
	for (int i = 0; i < n; i++) {
		int height;
		bool added = false;
		cin >> height;
		while(!s.empty()) {
			if (s.top().first > height) {
				result++;
				break;
			}
			if (s.top().first < height) {
				result += s.top().second;
				s.pop();
				continue;
			}
			result += s.top().second++;
			if (s.size() > 1) {
				result++;
			}
			added = true;
			break;
		}
		if (not added) {
			s.push({height, 1});
		}
	}
	cout << result;
} 
