#include <iostream>
#include <stack>

using namespace std;

string solve(string input) {
	stack<char> s;
	
	for (auto c: input) {
		if (c == '(' || c == '[') {
			s.push(c);
			continue;
		}
		if (c == ']') {
			if (!s.empty() && s.top() == '[') {
				s.pop();
				continue;
			}
			return "no\n";
		}
		if (c == ')') {
			if (!s.empty() && s.top() == '(')
				s.pop();
			else
				return "no\n";
		}
	}
	
	if (s.empty())
		return "yes\n";
	return "no\n";
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	while (1) {
		string input;
		getline(cin, input);
		
		if (!input.compare(".")) {
			break;
		}		
		cout << solve(input);
	}
}
