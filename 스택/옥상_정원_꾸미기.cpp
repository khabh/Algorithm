#include <bits/stdc++.h>
using namespace std;

int main() {
	int n;
	long long result = 0;
	stack<long long> s;
	cin >> n;
	for (int i = 0; i < n; i++) {
		long long height;
		cin >> height;
		while (!s.empty() && s.top() <= height) {
			s.pop();
		}
		result += s.size();
		s.push(height);
	}
	cout << result;
}
