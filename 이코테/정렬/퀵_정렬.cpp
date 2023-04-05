#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void quick_sort(vector<int> &numbers, int start, int end) {
	if (start >= end)
		return;
	int pivot = numbers[start];
	int left = start + 1;
	int right = end;
	
	while (left <= right) {
		while (left <= end && numbers[left] <= pivot) {
			left++;
		}
		while (right > start && numbers[right] >= pivot) {
			right--;
		}
		if (left > right)
			swap(numbers[right], numbers[start]);
		else
			swap(numbers[left], numbers[right]);
	}
	
	quick_sort(numbers, start, right - 1);
	quick_sort(numbers, right + 1, end);
}

int main() {
	vector<int> numbers{5, 7, 9, 0, 3, 1, 6, 2, 4, 8};
	quick_sort(numbers, 0, 9);
	
	for (int number : numbers) {
		cout << number << " ";
	}
}