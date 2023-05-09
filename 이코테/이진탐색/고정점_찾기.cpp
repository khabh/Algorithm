#include <iostream>

using namespace std;
int numbers[1000000], n;

int find_index(int start, int end) {
	if (start > end)
		return -1;
	int mid = (start + end) / 2;
	if (numbers[mid] == mid)
		return mid;
	if (numbers[mid] < mid)
		return find_index(mid + 1, end);
	return find_index(start, mid - 1);
}

int main() {
	cin.tie(0);
	ios::sync_with_stdio(0);
	
	cin >> n;
	for (int i = 0; i < n; i++) 
		cin >> numbers[i];
	cout << find_index(0, n - 1);
}