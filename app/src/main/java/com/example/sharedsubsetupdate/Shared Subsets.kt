package com.example.sharedsubsetupdate

/**
 * @author Hanna Górecka
 * Funkcja sortuje listę algorytmem sortowania bąbelkowego w sposób rosnący
 * @param list1 lista nieposortowanych elementów
 *
 * Wspomogłam się algorymem sortowania bąbelkowego z: https://www.javaguides.net/2022/11/bubble-sort-algorithm-in-kotlin.html
 */
fun sort (list1: MutableList<Int>)
{
    val n = list1.size
    for (i in 0 .. n - 2) {
        for (j in 0 .. n - i - 2) {
            if (list1[j] > list1[j + 1]) {
                val temp = list1[j]
                list1[j] = list1[j + 1]
                list1[j + 1] = temp
            }
        }
    }
}

/**
 * @author Hanna Górecka
 * Funkcja zwraca indeks, na którym w podanej liście znajduje się następny element o innej wartości niż początkowa
 * @param list1 lista, której elementy sprawdzamy
 * @param i początkowy indeks
 * @param max maksymalny indeks
 * @throws Exception jeśli iterator jest ujemny lub większy od maksymalnego indeksu
 * @throws Exception jeśli maksymalny indeks jest ujemny
 *
 */
fun switchToNext (list1: MutableList<Int>, i: Int, max: Int): Int
{
    if(i<0) throw Exception ("Niepoprawny iterator")
    if(i>max) throw Exception ("Iterator przekracza zakres")
    if(max<0) throw Exception ("Niepoprawny zakres")
    var temp1=i
    val temp = list1[i]
    while(list1[temp1]==temp&&temp1<max)
    {
        temp1++
    }
    return temp1
}
/**
 * @author Hanna Górecka
 * Funkcja zwraca listę wspólnych elementów z dwóch podanych jako argumenty list, jeśli nie ma wspólnych elementów wyrzuca błąd
 * @param list1 pierwsza lista
 * @param i druga lista
 * @return listę wspólnych elementów
 * @throws Exception kiedy któraś z list jest pusta
 * @throws Exception kiedy brak wspólnych elementów
 *
 */
fun shared (list1: MutableList<Int>, list2: MutableList<Int>): List<Int>
{
    if(list1.isEmpty()||list2.isEmpty()) throw Exception("Błędne argumenty, listy nie mogą być puste")
    val list3 = mutableListOf<Int> ()
    sort(list1)
    sort(list2)
    val n = list1.size
    val m = list2.size
    var j = 0
    var k = 0
    while(j < n&&k<m)
    {
        if(list1[j]==list2[k])
        {
            list3.add(list3.size, list1[j])
            j++
            k++
        }
        else if(list1[j]>list2[k]&&k<m-1)
        {
            k=switchToNext(list2, k, m)
        }
        else if(list2[k]>list1[j]&&j<n-1)
        {
            j=switchToNext(list1, j, n)
        }
        else
        {
            j++
            k++
        }
    }

    if (list3.size<=0) throw  Exception("No shared elements")
    return list3
}

/**
 * Funkcja testująca funkcji sort(list1)
 */
fun testSort()
{
    var list = mutableListOf(3,2,1)
    val list2 = mutableListOf<Int>(1,2,3)
    sort(list)
    assert(list==list2, {"Test funkcji sortującej zakończony niepowodzeniem "})
    println("Test sortowania zakonczony pomyslnie")
}
/**
 * Funkcja testująca funkcji switchToNext(list1, i, max)
 *
 */
fun testSwitchToNext()
{
    var list = mutableListOf<Int>(1,2,2,2,3)
    val i = switchToNext(list, 2, list.size)
    assert(i==4, {"Test funkcji przesuwającej indeks zakończony niepowodzeniem "})
    println("Test funkcji przesuwającej indeks zakończony powodzeniem")

}

/* Funkcja testująca funkcji shared(list1, list2)
*/
fun testShared()
{
    var list = mutableListOf<Int>(1,2,2,2,3)
    val list2 = mutableListOf<Int>(1,2,3)
    assert(shared(list, list2)==list2, {"Test funkcji zwracającej wspólny podzbiór zakończony niepowodzeniem "})
    println("Test funkcji zwracającej wspólny podzbiór zakończony powodzeniem")
}
fun main()
{
    testSort()
    testSwitchToNext()
    testShared()
}