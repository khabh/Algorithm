#include <iostream>
#include <algorithm>

#define INF 987654321

using namespace std;
int graph[101][101];

int main() {
	int n, m;
	
	cin >> n >> m;
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			graph[i][j] = INF;
		}
		graph[i][i] = 0;
	}

		
	while (m--) {
		int a, b, c;
		cin >> a >> b >> c;
		if (c < graph[a][b])
			graph[a][b] = c;
	}
	
	
	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
			}
		}
	}
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (graph[i][j] == INF) 
				cout << "0 ";
			else
				cout << graph[i][j] << " ";
		}
		cout << "\n";
	}
}