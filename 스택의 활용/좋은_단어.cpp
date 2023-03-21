#include <iostream>
#include <stack>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int n, result = 0;
	cin >> n;
	while (n--) {
		stack<char> s;
		string word;
		cin >> word;
		
		for (char w : word) {
			if (s.empty()) {
				s.push(w);
				continue;
			}
			if (s.top() == w) {
				s.pop();
				continue;
			}
			s.push(w);
		}
		
		if (s.empty())
			result++;
	}
	
	cout << result;
}
