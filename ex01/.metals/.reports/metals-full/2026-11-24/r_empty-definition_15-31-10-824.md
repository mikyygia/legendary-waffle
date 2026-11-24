error id: file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala:print.
file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -Expr.print.
	 -Expr.print#
	 -Expr.print().
	 -print.
	 -print#
	 -print().
	 -scala/Predef.print.
	 -scala/Predef.print#
	 -scala/Predef.print().
offset: 367
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

  def countNums(expr: Expr): Int = 
    @@print(expr)
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 