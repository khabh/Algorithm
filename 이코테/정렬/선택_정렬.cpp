#include <iostream>

using namespace std;

int main() {
	int numbers[10] = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
	
	for (int i = 0; i < 9; i++) {
		int min_index = i;
		for (int j = i + 1; j < 10; j++) {
			if (numbers[j] < numbers[min_index]) 
				min_index = j;
		}
		int temp = numbers[i];
		numbers[i] = numbers[min_index];
		numbers[min_index] = temp;
	}
	
	for (int number : numbers) {
		cout << number << " ";
	}
}