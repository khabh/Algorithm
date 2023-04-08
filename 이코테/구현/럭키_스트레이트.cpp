#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	string n;
	cin >> n;
	int mid = (int)(n.length() / 2), first = 0, second = 0;
	
	for (int i = 0; i < mid; i++) {
		first += n[i] - '0';
	}
	for (int i = mid; i < n.length(); i++) {
		second += n[i] - '0';
	}
	
	if (first == second) {
		cout << "LUCKY";
		return 0;
	}
	cout << "READY";
}