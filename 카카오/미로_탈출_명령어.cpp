#include <string>
#include <vector>

using namespace std;

void add(int count, string c, string &str) {
    if (count == 0)
        return;
    while (count--) 
        str += c;
}

string solution(int n, int m, int x, int y, int r, int c, int k) {
    string answer = "";
    int distance = abs(x - r) + abs(y - c);
    int d = 0, l = 0, u = 0, right = 0;
    k -= distance;
    if (k < 0 || k % 2 != 0)
        return "impossible";
    
    if (x < r) {
        d = r - x;
        add(d, "d", answer);
    }
    else 
        u = x - r;
    
    d = min(k / 2, n - d - x);
    k -= (d * 2);
    add(d, "d", answer);
    u += d;
    if (y > c) {
        l = y - c;
        add(l, "l", answer);
    }
    else 
        right = c - y;
    
    l = min(k / 2, y - l - 1);
    k -= (l * 2);
    add(l, "l", answer);
    right += l;
    
    add(k / 2, "rl", answer);
    add(right, "r", answer);
    add(u, "u", answer);
    
    return answer;
}