#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, pos = 0, stack[10001];
	cin >> n;
	for (int i = 0; i < n; i++) {
		string command;
		cin >> command;
		if (command == "push") {
			int num;
			cin >> num;
			stack[pos++] = num;
			continue;
		} 
		if (command == "top") {
			if (!pos)
				cout << "-1\n";
			else
				cout << stack[pos - 1] << "\n";
			continue;
		}
		if (command == "size") {
			cout << pos << "\n";
			continue;
		}
		if (command == "empty") {
			if (!pos)
				cout << "1\n";
			else
				cout << "0\n";
			continue;
		}
		if (!pos)
			cout << "-1\n";
		else {
			cout << stack[pos - 1] << "\n";
			pos--;
		}
	}
}
