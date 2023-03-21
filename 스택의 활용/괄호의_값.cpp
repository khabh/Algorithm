#include <iostream>
#include <stack>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	stack<char> s;
	int num = 1, sum = 0;
	char prev;
	string input;
	cin >> input;
	
	for (char c : input) {
		if (c == '(') {
			num *= 2;
			s.push(c);
		}
		else if (c == '[') {
			num *= 3;
			s.push(c);
		}
		else if (c == ')') {
			num /= 2;
			if (!s.empty() && s.top() == '(') {
				s.pop();
				if (prev == '(')
					sum += (num * 2);
			}
			else {
				cout << 0;
				return 0;
			}
		}
		else {
			num /= 3;
			if (!s.empty() && s.top() == '[') {
				s.pop();
				if (prev == '[')
					sum += (num * 3);
			}
			else {
				cout << 0;
				return 0;
			}
		}
		prev = c;
	}
	
	if (!s.empty()) {
		cout << 0;
		return 0;
	}
	cout << sum;
} 
