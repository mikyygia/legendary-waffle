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
    case Add(l, r) => countNums(l) + countNums(r)
    case Mul(l, r) => countNums(l) + countNums(r)
}
