#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
	int n, i2= 0, i3 = 0, i5 = 0, next2 = 2, next3 = 3, next5 = 5;
	vector<int> numbers;
	numbers.push_back(1);

	cin >> n;
	while (numbers.size() < n) {
		int ugly = min(next2, min(next3, next5));
		numbers.push_back(ugly);
		if (ugly == next2) {
			i2++;
			next2 = numbers[i2] * 2;
		}
		if (ugly == next3) {
			i3++;
			next3 = numbers[i3] * 3;
		}
		if (ugly == next5) {
			i5++;
			next5 = numbers[i5] * 5;
		}
	}
	
	cout << numbers[n - 1];
}