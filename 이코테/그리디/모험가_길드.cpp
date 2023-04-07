#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, fear[100000], count = 0, index = 0, result = 0;
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> fear[i];
	}
	
	sort(fear, fear + n);
	
	while (index < n) {
		count++;
		if (count >= fear[index]) {
			result++;
			count = 0;
			
		}
		index++;
	}
	
	cout << result;
}