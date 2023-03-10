#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	long a, b;
	cin >> a >> b;
	if (a > b) {
		swap(a, b);
	}
	if (a == b)
		cout << 0;
	else
		cout << b - a - 1 << "\n";
	for (long i = a + 1; i < b; i++) {
		cout << i << " ";
	}
}
