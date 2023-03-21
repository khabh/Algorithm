#include <iostream>
#include <deque>

using namespace std;
deque<int> d;

void run_command(string cmd) {
	if (cmd == "push_front") {
		int num;
		cin >> num;
		d.push_front(num);
		return;
	}
	if (cmd == "push_back") {
		int num;
		cin >> num;
		d.push_back(num);
		return;
	}
	if (cmd == "size") {
		cout << d.size() << "\n";
		return;
	}
	if (cmd == "empty") {
		if (d.empty()) {
			cout << "1\n";
			return;
		}
		cout << "0\n";
		return;
	}
	if (d.empty()) {
			cout << "-1\n";
			return;
	}
	if (cmd == "front") {
		cout << d.front() << "\n";
		return;
	}
	if (cmd == "back") {
		cout << d.back() << "\n";
		return;
	}
	if (cmd == "pop_front") {
		cout << d.front() << "\n";
		d.pop_front();
		return;
	}
	cout << d.back() << "\n";
	d.pop_back();
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n;
	cin >> n;
	while (n--) {
		string cmd;
		cin >> cmd;
		run_command(cmd);
	}
}
