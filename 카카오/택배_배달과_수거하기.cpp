#include <string>
#include <vector>

using namespace std;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {
    long long distance = 0;
    int pi = n - 1, di = n - 1;
    
    while (pi > -1 || di > - 1) {
        while (di > -1 && !deliveries[di])
            di--;
        while (pi > -1 && !pickups[pi])
            pi--;
        
        distance += (max(pi, di) + 1) * 2;
        
        if (di > -1) {
            int delivery = 0;
            while (di > -1 && delivery + deliveries[di] <= cap) {
                delivery += deliveries[di];
                di--;
            }
            if (delivery < cap && di > -1) 
                deliveries[di] -= (cap - delivery);
        }
        if (pi > - 1) {
            int pickup = 0;
            while (pi > -1 && pickup + pickups[pi] <= cap) {
                pickup += pickups[pi];
                pi--;
            }
            if (pickup < cap && pi > -1)
                pickups[pi] -= (cap - pickup);
        }
    }
    
    return distance;
}