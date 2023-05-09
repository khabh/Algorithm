#include <iostream>

using namespace std;
int n, x, numbers[1000000];

int last(int start, int end) {
	int mid = (start + end) / 2;
	if ((mid == (n - 1) || x < numbers[mid + 1]) && numbers[mid] == x)
		return mid;
	if (numbers[mid] > x)
		return last(start, mid - 1);
	return last(mid + 1, end);
}

int first(int start, int end) {
	if (start > end)
		return -1;
	int mid = (start + end) / 2;
	if ((mid == 0 || x > numbers[mid - 1]) && numbers[mid] == x)
		return mid;
	if (numbers[mid] >= x)
		return first(start, mid - 1);
	return first(mid + 1, end);
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	cin >> n >> x;
	for (int i = 0; i < n; i++)
		cin >> numbers[i];
	int f = first(0, n - 1);
	if (f == -1) {
		cout << "-1";
		return 0;
	}
	cout << last(f, n - 1) - f + 1;
}