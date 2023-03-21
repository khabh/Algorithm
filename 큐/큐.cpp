#include <iostream>
#include <queue>
using namespace std;

int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	
	queue<int> q;
	int n;
	cin >> n;
	while (n--) {
		string cmd;
		cin >> cmd;	
		if (cmd == "push") {
			int num;
			cin >> num;
			q.push(num);
			continue;
		}
		if (cmd == "size") {
			cout << q.size() << "\n";
			continue;
		}
		if (cmd == "empty") {
			if (q.empty()) {
				cout << "1\n";
				continue;
			}
			cout << "0\n";
			continue;
		}
		if (cmd == "front") {
			if (q.empty()) {
				cout << "-1\n";
				continue;
			}
			cout << q.front() << "\n";
			continue;
		}
		if (cmd == "back") {
			if (q.empty()) {
				cout << "-1\n";
				continue;
			}
			cout << q.back() << "\n";
			continue;
		}
		if (q.empty()) {
			cout << "-1\n";
			continue;
		}
		cout << q.front() << "\n";
		q.pop(); 
	} 
}
