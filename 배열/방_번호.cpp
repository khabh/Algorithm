#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	int num[10] = {};
	string s;
	cin >> s;
	for (auto n: s)
		num[n - '0']++;
	int result = (num[6] + num[9] + 1) / 2;
	for (int i = 0; i < 10; i++) {
		if (i == 6 || i == 9) continue;
		result = max({result, num[i]});
	}
	cout << result;
}
