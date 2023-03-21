#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n;
	vector<int> result(1000000, -1);
	stack<pair<int, int>> s;
	cin >> n;
	for(int i = 0; i < n; i++) {
		int num;
		cin >> num;
		while (!s.empty() && s.top().first < num) {
			result[s.top().second]= num;
			s.pop();
		}
		s.push({num, i});
	}
	for (int i = 0; i < n; i++)
		cout << result[i] << " ";
}
