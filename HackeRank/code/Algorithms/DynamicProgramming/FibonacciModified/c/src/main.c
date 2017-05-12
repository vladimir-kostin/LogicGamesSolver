/**
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */

#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

#define LEN 104000 // 103592 / 9 = 11511
#define RANKS 9

typedef long rank;
rank IN_RANK;
char FMT[200];

typedef struct {
	rank data[LEN];
	int x;
} bigint;

bigint* newBigInt() {
	bigint* p = calloc(1, sizeof(bigint));
	p->x = 1;
	memset(p->data, 0, LEN);
	return p;
}

bigint* fromInt(rank a) {
	bigint* z = newBigInt();
	memset(z->data, 0, LEN);
	z->data[0] = a;
	z->x = 1;
	return z;
}

bigint* copy(bigint* a) {
	bigint* p = newBigInt();
	p->x = a->x;
	memcpy(p->data, a->data, sizeof(rank)*(a->x));
	return p;
}

void addCarryAndDivide(rank* numerator, int denominator, rank* carry) {
	*numerator += *carry;
	ldiv_t divResult = ldiv(*numerator, denominator);
	*numerator = divResult.rem;
	*carry = divResult.quot;
}

void dealWithCarry(rank carry, bigint* a) {
	if(0 < carry) {
		a->data[a->x] = carry;
		a->x++;
	}
}

bigint* add(bigint* a, bigint* b) {
	bigint* z = copy(a);
	//
	int max = (z->x > b->x) ? z->x : b->x;
	rank c=0;
	for( int j=0; j<max; j++) {
		z->data[j] += b->data[j];
		addCarryAndDivide(&z->data[j], IN_RANK, &c);
	}
	z->x = max;
	dealWithCarry(c, z);
	return z;
}

bigint* multiplyInRank(bigint* a, rank mult, int shift) {
	if(0 > mult || IN_RANK <= mult) {
		return 0;
	}

	bigint* z = newBigInt();
	if(0==mult) {
		return z;
	}
	memcpy(z->data+shift, a->data, sizeof(rank)*(a->x));
	z->x = a->x + shift;

	rank c=0;
	for(int j=0; j<z->x; j++) {
		z->data[j] *= mult;
		addCarryAndDivide(&z->data[j], IN_RANK, &c);
	}
	dealWithCarry(c, z);
	return z;
}

bigint* square(bigint* a) {
	bigint* z = newBigInt();
	for(int j=0; j<a->x; j++) {
		bigint* m = multiplyInRank(a, a->data[j], j);
		bigint* d = add(z, m);
		free(z);
		free(m);
		z = d;
	}
	return z;
}

void printBigInt(bigint* a) {
	printf("%ld", a->data[a->x-1]);
	for(int j=a->x-2; 0<=j; j--) {
		printf(FMT, a->data[j]);
	}
}

int t1, t2, n;
bigint* r;

void readInput() {
	scanf("%d %d %d", &t1, &t2, &n);
}

void testInput() {
	printf("%d %d %d\n", t1, t2, n);
}

void p(int j, bigint* a) {
	printf("%d: ", j);
	printBigInt(a);
	printf("\n");
}

void calc() {
	bigint* r1 = fromInt(t1);
	bigint* r2 = fromInt(t2);
	bigint* r3 = 0;
//	p(1, r1); p(2, r2);
	for(int j=3; j<=n; j++) {
		r3 = add(r1, square(r2));
//		p(j, r3);
		free(r1);
		r1 = r2;
		r2 = r3;
	}
	r = r3;
}

void printResult() {
	printBigInt(r);
}

void initGlobals() {
	IN_RANK = 10;
	for(int j=1; j<RANKS; j++) { IN_RANK *= 10; }
	sprintf(FMT, "%%0%dld", RANKS);
}

int main() {
	initGlobals();
	readInput();
//	t1=0;t2=1;n=13;
//	testInput();
	calc();
	printResult();

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    return 0;
}
