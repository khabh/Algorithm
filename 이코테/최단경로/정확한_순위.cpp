#include <iostream>
#define INF 100000

using namespace std;
int board[501][501];

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n, m, result = 0;
	
	cin >> n >> m;
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			board[i][j] = INF;
		} 
		board[i][i] = 0;
	}
		
	
	while (m--) {
		int a, b;
		cin >> a >> b;
		board[a][b] = 1;
	}
	
	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				board[i][j] = min(board[i][j], board[i][k] + board[k][j]);
			}
		}	
	}
	
	for (int i = 1; i <= n; i++) {
		bool known = true;
		for (int j = 1; j <= n; j++) {
			if (board[i][j] == INF && board[j][i] == INF) {
				known = false;
				break;
			}
		}
		
		if (known) 
			result++;
	}
	
	cout << result;
}


//#include <iostream>
//
//using namespace std;
//int board[501][501];
//int students[501];
//
//int main() {
//	ios::sync_with_stdio(0);
//	cin.tie(0);
//	
//	int n, m, result = 0;
//	
//	cin >> n >> m;
//	
//	while (m--) {
//		int a, b;
//		cin >> a >> b;
//		if (board[a][b])
//			continue;
//		board[a][b] = -1;
//		board[b][a] = 1;
//		students[a]++;
//		students[b]++;
//	}
//	
//	for (int j = 1; j <= n; j++) {
//		for (int i = 1; i <= n; i++) {
//			for (int k = 1; k <= n; k++) {
//				if (!board[i][k] && board[i][j] && board[i][j] == board[j][k]) {
//					board[i][k] = board[j][k];
//					board[k][i] = -board[j][k];
//					students[i]++;
//					students[k]++;
//				}
//			}
//		}	
//	}
//	
//	for (int i = 1; i <= n; i++) {
//		if (students[i] == n - 1)
//			result++;
//	}
//	
//	cout << result;
//}
