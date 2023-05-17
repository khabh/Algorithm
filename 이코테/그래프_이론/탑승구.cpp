#include <iostream>

using namespace std;

int gate[100001];

int get_parent(int child) {
	while (gate[child] != child) {
		child = gate[child];
	}
	
	return child;
}


void union_parent(int a, int b) {
	a = get_parent(a);
	b = get_parent(b);
	
	if (a < b) {
		gate[b] = a;
	}
	else {
		gate[a] = b;
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int g, p, result = 0;
	cin >> g >> p;
	for (int i = 1; i <= g; i++) 
		gate[i] = i;
	
	while (p--) {
		int node;
		
		cin >> node;
		int right_node = get_parent(node);
		if (right_node == 0) {
			cout << result;
			return 0;
		}
		union_parent(right_node, right_node -1);
		for (int i = 0; i <= g; i++) {
			cout << gate[i] << " ";
		}
		cout << "\n";
		result++;
		
	}
	
	cout << result;
}