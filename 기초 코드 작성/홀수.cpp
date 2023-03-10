#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int sum = 0, num;
	int min_num = 101;
	for (int i = 0; i < 7; i++) {
		cin >> num;
		if (num & 1) {
			sum += num;
			if (num < min_num) {
				min_num = num;
			}
		}
	}
	if (min_num == 101)
		cout << -1;
	else
		cout << sum << "\n" << min_num; 
} 
