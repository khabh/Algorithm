#include <iostream>
#include <stack>
using namespace std;

int main() {
	int k, count = 0;
	stack<int> s;
	cin >> k;
	for (int i = 0; i < k; i++) {
		int num;
		cin >> num;
		if (!num) {
			s.pop();
			continue;
		}
		s.push(num);
	}
	while (!s.empty()) {
		count += s.top();
		s.pop();
	}
	cout << count;
}
