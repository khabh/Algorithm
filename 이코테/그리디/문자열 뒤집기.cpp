#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	int one = 0, zero = 0;
	
	string n;
	cin >> n;
	char prev = n[0];
	for (char num : n) {
		if (num != prev) {
			if (prev == '1')
				one++;
			else
				zero++;
			prev = num;
		}
	}
	
	if (prev == '1')
		one++;
	else 
		zero++;
	
	cout << min(zero, one);
}