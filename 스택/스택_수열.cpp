#include <iostream>
#include <stack>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, count = 1;
	stack<int> s;
	string result = "";
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		while (count <= num) {
			s.push(count++);
			result += "+\n";
		}
		if (s.top() != num) {
			cout << "NO";
			return 0;
		} 
		s.pop();
		result += "-\n";
	}
	cout << result;
}
