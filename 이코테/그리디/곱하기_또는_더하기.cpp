#include <iostream>
#include <string>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	string n;
	int result = 0;
	cin >> n;
	for (char num : n) {
		int a = num - '0';
		if (result * a > result + a) {
			result *= a;
			continue;
		}
		result += a;
	}
	cout << result;
}