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
        (if f(value) then 1 else 0) + count(left, f) + count(right, f)

  def merge(left: Tree, right: Tree): Tree =
    (left, right) match
      case (Tree.Leaf(x), Tree.Leaf(y))                    => Leaf(x + y) // Leaf + Leaf
      case (Tree.Leaf(x), Tree.Branch(left, value, right)) => Leaf(x + value) // Leaf + Branch
      case (Tree.Branch(left, value, right), Tree.Leaf(x)) => Leaf(x + value) // Branch + Leaf
      case (Tree.Branch(l1, v1, r1), Tree.Branch(l2, v2, r2)) => Branch(merge(l1, l2), v1 + v2, merge(r1, r2)) // Branch + Branch

  // ---------------------------------------------------------------------------
  // Boolean Expressions
  // ---------------------------------------------------------------------------
  import BE.*

  def isImply(expr: BE): Boolean =
    expr match
      case BE.Imply(l, r) => true
      case _ => false

  def noAnd(expr: BE): Boolean = 
    expr match
      case BE.And(l, r) => false
      case BE.Literal(b) => true
      case BE.Variable(s) => true
      case BE.Or(l, r) => noAnd(l) && noAnd(r)
      case BE.Imply(l, r) => noAnd(l) && noAnd(r)
      case BE.Not(inner) => noAnd(inner)

  def subExprs(expr: BE): Set[BE] =
    expr match
      case BE.Literal(x) => Set(expr)
      case BE.Variable(s) => Set(expr)
      case BE.And(l, r) => subExprs(l) ++ subExprs(r) ++ Set(expr)
      case BE.Imply(l, r) => subExprs(l) ++ subExprs(r) ++ Set(expr)
      case BE.Or(l, r) => subExprs(l) ++ subExprs(r) ++ Set(expr)
      case BE.Not(inner) => subExprs(inner) ++ Set(expr)

  def getString(expr: BE): String =
    expr match
      case BE.Literal(x) => if x then "#t" else "#f"
      case BE.Variable(name) => name
      case BE.And(l, r) => s"(${getString(l)} && ${getString(r)})"
      case BE.Or(l, r) =>s"(${getString(l)} || ${getString(r)})"
      case BE.Imply(l, r) => s"(${getString(l)} => ${getString(r)})"
      case BE.Not(inner) => "!" + getString(inner)

  
  def eval(expr: BE, env: Map[String, Boolean]): Boolean =
    expr match
      case BE.Literal(x) => x
      case BE.Variable(x) => env.getOrElse(x, false)
      case BE.Imply(l, r) => eval(l, env) == eval(r, env)
      case BE.And(l, r) => eval(l, env) && eval(r, env)
      case BE.Or(l, r) => eval(l, env) | eval(r, env)
      case BE.Not(inner) => eval(inner, env) == false
}
