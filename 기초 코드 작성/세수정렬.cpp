// 첫 번째 풀이 
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	int arr[3];
	for (int i = 0; i < 3; i++) {
		cin >> arr[i];
	}
	sort(arr, arr + 3);
	for (int i = 0; i < 3; i++) {
		cout << arr[i] << ' ';
	}
}


// 두 번째 풀이 

#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie();
	int a, b, c;
	cin >> a >> b >> c;
	int max_num = max({a, b, c});
	int min_num = min({a, b, c});
	
	cout << min_num << ' ' << a + b + c - max_num - min_num << ' ' << max_num;
}
