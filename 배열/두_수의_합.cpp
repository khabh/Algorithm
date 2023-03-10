#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0); 
	int n, x, end, start, result = 0;
	int num[100000];
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> num[i];
	cin >> x;
	sort(num, num + n);
	end = n - 1;
	start = 0;
	while (end != start) {
		int temp = num[end] + num[start];
		if (temp == x) {
			result++;
			start++;
		}
		else if (temp < x)
			start++;
		else
			end--;
	}
	cout << result;
}
