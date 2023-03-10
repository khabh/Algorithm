#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	int num[21];
	for (int i = 1; i < 21; i++)
		num[i] = i;
	for (int i = 0; i < 10; i ++) {
		int a, b;
		cin >> a >> b;
		reverse(num + a, num + b + 1);
	}
	for (int i = 1; i < 21; i++)
		cout << num[i] << ' ';
}
