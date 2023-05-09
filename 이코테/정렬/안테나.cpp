#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int n, house[200001];
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> house[i];
	}
	sort(house, house + n);
	cout << house[(n - 1) / 2];
}