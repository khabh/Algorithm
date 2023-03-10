#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, v, result = 0;
	int arr[201] = {};
	cin >> n;
	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		arr[num + 100]++;
	}	
	cin >> v;
	cout << arr[v + 100];
}
