#include <iostream>
using namespace std;

int main() {
	int order, num;
	int max_num = 0;
	for (int i = 1; i < 10; i++) {
		cin >> num;
		if (num > max_num) {
			max_num = num;
			order = i;
		}
	}
	cout << max_num << "\n" << order;
}
