# jpa-hibernate

The example project to check how hibernate works.

### N+1 problem
Launch tests and you see:
* *findOneProblemN1* test uses standart function causes N+1 problem
* *findOneProblemN1Solved* test uses function that uses join fetch and shows only one access to db
