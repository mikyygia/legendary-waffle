error id: file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala:Add.
file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -Expr.Add.
	 -Expr.Add#
	 -Expr.Add().
	 -Add.
	 -Add#
	 -Add().
	 -scala/Predef.Add.
	 -scala/Predef.Add#
	 -scala/Predef.Add().
offset: 404
uri: file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
text:
```scala
package kuplrg

object Implementation extends Template {

  import Expr.*

  // btw there's an existing parser that can recognize what case expr is then passes into interp
  def interp(expr: Expr): Value = expr match
    case Num(n) => n
    case Add(l, r) => interp(l) + interp(r)
    case Mul(l, r) => interp(l) * interp(r)

  def countNums(expr: Expr): Int = expr match
    case Num(n) => 1
    case A@@dd(l, r) => 2
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 