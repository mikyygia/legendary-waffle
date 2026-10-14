error id: file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala:`<none>`.
file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
empty definition using pc, found symbol in pc: `<none>`.
semanticdb not found
empty definition using fallback
non-local guesses:
	 -Tree.l.
	 -BE.l.
	 -l.
	 -scala/Predef.l.
offset: 1430
uri: file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
text:
```scala
package kuplrg

object Implementation extends Template {

  // ---------------------------------------------------------------------------
  // Basic Data Types
  // ---------------------------------------------------------------------------
  def clamp(lower: Int, x: Int, upper: Int): Int =
    if x >= upper then upper
    else if x <= lower then lower
    else x

  def validName(name: String): Boolean =
    if (
      name.length > 0
      && name.length <= 10
      && name(0).toInt >= 'A'.toInt
      && name(0).toInt <= 'Z'.toInt
    ) true
    else false

  // ---------------------------------------------------------------------------
  // Functions
  // ---------------------------------------------------------------------------
  def collatzLength(n: Int): Int =
    if (n == 1) then 1
    else if (n % 2 == 0) then collatzLength(n / 2) + 1
    else if (n % 2 == 1 && n > 1) then collatzLength(3 * n + 1) + 1
    else 1

  def fixpoint(f: Int => Int): Int => Int = n =>
    if f(n) == n then n else fixpoint(f)(f(n))

  def applyK(f: Int => Int, k: Int): Int => Int =
    if k != 0 then n => applyK(f, k - 1)(f(n)) else n => n

  // ---------------------------------------------------------------------------
  // Collections
  // ---------------------------------------------------------------------------
  def sumEven(l: List[Int]): Int =
    if l.length == 0 then 0
    else if l(l.length - 1) % 2 == 0 
    if @@l.length == 1 then l(0)
    else sumEven(l.slice(0, l.length - 1)) + l(l.length - 1)

  def double(l: List[Int]): List[Int] = ???

  def generate(f: Int => Int): Int => List[Int] = ???

  def join(l: Map[String, Int], r: Map[String, Int]): Map[String, Int] = ???

  def subsets(set: Set[Int]): List[Set[Int]] = ???

  // ---------------------------------------------------------------------------
  // Trees
  // ---------------------------------------------------------------------------
  import Tree.*

  def heightOf(t: Tree): Int = ???

  def max(t: Tree): Int = ???

  def postorder(t: Tree): List[Int] = ???

  def count(t: Tree, f: Int => Boolean): Int = ???

  def merge(left: Tree, right: Tree): Tree = ???

  // ---------------------------------------------------------------------------
  // Boolean Expressions
  // ---------------------------------------------------------------------------
  import BE.*

  def isImply(expr: BE): Boolean = ???

  def noAnd(expr: BE): Boolean = ???

  def subExprs(expr: BE): Set[BE] = ???

  def getString(expr: BE): String = ???

  def eval(expr: BE, env: Map[String, Boolean]): Boolean = ???
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: `<none>`.