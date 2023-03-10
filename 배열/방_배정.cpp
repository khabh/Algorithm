#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, k, result = 0;
	int students[12] = {};
	cin >> n >> k;
	for (int i = 0; i < n; i++) {
		int s, y;
		cin >> s >> y;
		students[(y - 1) * 2 + s]++;
	}
	for (int i = 0; i < 12; i++) { 
		int temp = students[i] / k;
		if (students[i] % k) {
			temp++;
		}
		result += temp;
	}
	cout << result;
} 
