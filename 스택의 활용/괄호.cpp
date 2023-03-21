#include <iostream>
#include <stack>
using namespace std;

string solve() {
	string input;
	stack<char> s;
	cin >> input;
		
	for (char w : input) {
		if (w == '(') {
			s.push(w);
			continue;
		}
		if (s.empty() || s.top() != '(') {
			return "NO\n";
		}
		s.pop();			
	}
	
	if (s.empty()) {
		return "YES\n";
	}
	return "NO\n";
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int t;
	cin >> t;
	while (t--) {
		cout << solve();
	}
}
