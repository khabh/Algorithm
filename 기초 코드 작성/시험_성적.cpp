#include <iostream>
using namespace std;

int main() {
	int a;
	ios::sync_with_stdio(0);
	cin.tie();
	cin >> a;
	if (a >= 90) cout << "A";
	else if (a >= 80) cout << "B";
	else if (a >= 70) cout << "C";
	else cout << "D";
}
