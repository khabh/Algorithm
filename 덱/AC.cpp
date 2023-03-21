#include <iostream>
#include <deque>
#include <sstream>
#include <string>
using namespace std;

string solve() {
	string cmd, x, result = "";
	int n, start = 1;
	bool front = true;
	deque<string> d;
	cin >> cmd >> n >> x;
	while (n--) {
		int count = 0;
		while (isdigit(x[start + count])) {
			count++;
		}
		d.push_back(x.substr(start, count));
		start += ++count;
	}
	for (char c : cmd) {
		if (c == 'R') {
			front = !front;
			continue;
		}
		if (d.empty()) {
			return "error";
		}
		if (front) {
			d.pop_front();
			continue;
		}
		d.pop_back();
	}
	int size = d.size();
	if (front) {
		for (int i = 1; i < size; i++) {
			result += (d.front() + ",");
			d.pop_front();
		}
	}
	else {
		for (int i = 1; i < size; i++) {
			result += (d.back() + ",");
			d.pop_back();
		}
	}
	if (!d.empty()) {
		result += d.front();
	}
	return "[" + result + "]";
}

int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	int t;
	cin >> t;
	while(t--) {
		cout << solve() << "\n";
	}
}
