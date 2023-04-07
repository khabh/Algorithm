#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, coin[1000];
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> coin[i];
	}
	
	sort(coin, coin + n);
	int prev = coin[0];
	if (prev != 1) {
		cout << 1;
		return 0;
	}
	for (int i = 1; i < n; i++) {
		if (coin[i] > prev) {
			cout << prev + 1;
			break;
		}
		prev += coin[i];
	}
}