// 내 풀이 
#include <iostream>
using namespace std;

int main() {
	int n, x;
	cin >> n >> x;
	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		if (num < x) {
			cout << num << ' ';
		}
	} 
}

// 수정된 풀이 
#include <iostream>
using namespace std;

int n, x, a[10005];
int main(void) {
	ios::sync_with_stdio(0);
	cin.tie();
	cin >> n >> x;
	
	for (int i = 0; i < n; i++)
		cin >> a[i];
	for (int i = 0; i < n; i++) {
		if (a[i] < x)
			cout << a[i] << ' ';
	}
}
