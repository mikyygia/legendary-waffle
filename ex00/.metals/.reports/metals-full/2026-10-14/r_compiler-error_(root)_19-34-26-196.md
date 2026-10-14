error id: 823787819D5698A52FB01DEE92F6063D
file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.



action parameters:
offset: 3075
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
    else if l(l.length - 1) % 2 == 0 && l.length == 1 then l(0)
    else if l(l.length - 1) % 2 == 0 then
      sumEven(l.slice(0, l.length - 1)) + l(l.length - 1)
    else sumEven(l.slice(0, l.length - 1))

  def double(l: List[Int]): List[Int] =
    if l.isEmpty then Nil
    else (l(0) :: l(0) :: Nil) ::: double(l.slice(1, l.length))

  def generate(f: Int => Int): Int => List[Int] = x =>
    if f(x) == x then f(x) :: Nil
    else x :: generate(f)(f(x))

  def join(l: Map[String, Int], r: Map[String, Int]): Map[String, Int] =
    (l.toSeq ++ r.toSeq).groupMapReduce(_._1)(_._2)(_ + _)

  def subsets(set: Set[Int]): List[Set[Int]] =
    set
      .subsets()
      .filter(_.nonEmpty)
      .toList
      .sortBy(
        _.toList.sorted.mkString, // some kind of sort but tbh idk what this is tbh
      )

  // ---------------------------------------------------------------------------
  // Trees
  // ---------------------------------------------------------------------------
  import Tree.*

  def heightOf(t: Tree): Int =
    t match
      case Tree.Leaf(_) => 0
      case Tree.Branch(left, value, right) =>
        1 + heightOf(left).max(heightOf(right))

  def max(t: Tree): Int =
    t match
      case Tree.Leaf(x) => x
      case Tree.Branch(left, value, right) =>
        max(left).max(max(right).max(value))

  def postorder(t: Tree): List[Int] =
    t match
      case Tree.Leaf(x) => List(x)
      case Tree.Branch(left, value, right) =>
        postorder(left) ::: postorder(right) ::: List(value)

  def count(t: Tree, f: Int => Boolean): Int =
    t match
      case Tree.Leaf(x) => if f(x) then 1 else 0
      case Tree.Branch(left, value, right) =>
        if f(@@)
        count(left, f) + count(right, f)

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


presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-9nt3lutaRrOxw8sQ9kDn0Q== [exists ], <HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.12.3/semanticdb-javac-0.12.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/sourcegraph/semanticdb-javac/0.12.3/semanticdb-javac-0.12.3.jar [exists ], <WORKSPACE>/lib/warts.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:
-feature -deprecation -explain -explain-types -language:implicitConversions -Xsemanticdb -sourceroot <WORKSPACE>




#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:425)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0