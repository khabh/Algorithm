#include <iostream>
#include <deque>
using namespace std;

int main() {
	int n, m, size, result = 0;
	deque<int> d;
	cin >> n >> m;
	size = n;
	for (int i = 1; i <= n; i++)
		d.push_back(i);
		
	while (m--) {
		int order, index;
		cin >> order;
		for (int i = 0; i < size; i++) {
			if (d[i] == order) {
				index = i;
				break;
			}
		}
		if (index < size - index) {
			result += index;
			while(index--) {
				int temp = d.front();
				d.pop_front();
				d.push_back(temp);
			}
			
		}
		else {
			index = size - index;
			result += index;	
			while (index--) {
				int temp = d.back();
				d.pop_back();
				d.push_front(temp);
			}
		}
		d.pop_front();
		size--;
	}
	cout << result;
}
