#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	vector<char> result;
	int count = 0;
	string s;
	
	cin >> s;
	
	for (char n : s) {
		if (isdigit(n)) {
			count += (n - '0');
			continue;
		}
		result.push_back(n);
	}
	sort(result.begin(), result.end());
	for (char n : result) {
		cout << n;
	}
	cout << count;
}