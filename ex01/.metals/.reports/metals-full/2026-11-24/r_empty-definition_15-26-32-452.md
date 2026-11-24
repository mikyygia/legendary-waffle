error id: file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala:Expr#
file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -Expr.Expr#
	 -Expr#
	 -scala/Predef.Expr#
offset: 98
uri: file://<WORKSPACE>/src/main/scala/kuplrg/Implementation.scala
text:
```scala
package kuplrg

object Implementation extends Template {

  import Expr.*

  def interp(expr: Expr@@): Value = 
    case Num(n: Int) => n;

  def countNums(expr: Expr): Int = ???
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 