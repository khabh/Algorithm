#include <string>
#include <vector>

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
    long sum1 = 0, sum2 = 0, count = 0, i1 = 0, i2 = 0, max_count = queue2.size() * 3;
    for (int i = 0; i < queue1.size(); i++) {
        sum1 += queue1[i];
        sum2 += queue2[i];
    }
    
    if ((sum1 + sum2) % 2)
        return -1;
    
    int temp;
    while (sum1 != sum2) {
        count++;
        if (sum1 > sum2) {
            temp = queue1[i1++];
            sum1 -= temp;
            sum2 += temp;
            queue2.push_back(temp);
        }
        else {
            temp = queue2[i2++];
            sum2 -= temp;
            sum1 += temp;
            queue1.push_back(temp);
        }
        if (count > max_count)
            return -1;
    }
    
    return count;
}