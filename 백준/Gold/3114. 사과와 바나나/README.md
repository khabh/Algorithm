# [Gold II] 사과와 바나나 - 3114 

[문제 링크](https://www.acmicpc.net/problem/3114) 

### 성능 요약

메모리: 221548 KB, 시간: 784 ms

### 분류

다이나믹 프로그래밍, 누적 합

### 제출 일자

2024년 11월 16일 17:54:03

### 문제 설명

<p>A나라와 B나라가 국경선을 놓고 몇 년째 싸우고 있다. 분쟁 지역의 크기는 직사각형이고, R×C 개의 칸으로 나누어져 있다. 모든 칸에는 사과 나무 또는 바나나 나무가 심어져 있다.</p>

<p>방금, 기나긴 영토 분쟁을 끝내기 위해 중립국에서 협상가 김상근이 도착했다. 상근이는 불도저를 이용해 일부 칸에 있는 나무를 모두 제거하고, 그러한 칸을 국경선으로 이용하려고 한다. 불도저는 가장 왼쪽 윗칸에서 출발하며, 한 칸 아래, 오른쪽, 오른쪽 아래 대각선으로 이동할 수 있다. 불도저는 오른쪽 아랫칸에 도착할 때까지 이동한다.</p>

<p>A는 불도저가 지나간 길의 아래쪽을 가지게 되고, B는 위쪽을 가지게 된다. 따라서, 땅을 하나도 받지 못하는 경우가 생길 수도 있다.</p>

<p>김상근은 A에 사는 사람들은 사과를 좋아하고, B에 사는 사람들은 바나나를 좋아한다는 사실을 알고 있다. 따라서, 불도저가 지나간 길의 아래쪽에 있는 사과 나무의 개수와 위쪽에 있는 바나나 나무 개수의 합을 크게 만들려고 한다.</p>

<p>가능한 합 중 가장 큰 합을 구하는 프로그램을 작성하시오. </p>

### 입력 

 <p>첫째 줄에 땅의 크기 R과 C가 주어진다. (2 ≤ R,C ≤ 1500)</p>

<p>다음 R개 줄에는 각 칸에 심어져있는 나무와 개수가 주어진다. 사과는 A, 바나나는 B이고, 각 칸에 심어져 있는 나무의 개수는 1보다 크거나 같고, 99보다 작거나 같다.</p>

### 출력 

 <p>가능한 합 중 가장 큰 것을 출력한다. </p>

