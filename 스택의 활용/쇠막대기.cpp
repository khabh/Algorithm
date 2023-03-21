#include <iostream>
#include <stack>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	string input;
	stack<char> s;
	int result = 0;
	char prev;
	cin >> input;
	
	for (char w : input) {
		if (w == '(') {
			s.push('(');
		}
		else {
			s.pop();
			if (prev == '(')
				result += s.size();
			else
				result++;
		}
		prev = w;
	}
	
	cout << result;
}
