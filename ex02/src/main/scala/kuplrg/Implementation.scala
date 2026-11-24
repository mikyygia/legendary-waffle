package kuplrg

object Implementation extends Template {

  import Expr.*

  def interp(expr: Expr, env: Env): Value = expr match
    case Num(n) => n
    case Add(l, r) => interp(l, env) + interp(r, env)
    case Mul(l, r) => interp(l, env) * interp(r, env)
    case Val(x, e, b) => interp(b, env + (x -> interp(e, env)))
    case Id(x) => env.getOrElse(x, error(s"free identifier: $x"))

  // not defined
  def freeIds(expr: Expr): Set[String] = expr match
    case Num(n) => Set()
    case Add(l, r) => freeIds(l) ++ freeIds(r)
    case Mul(l, r) => freeIds(l) ++ freeIds(r)
    // find freeid in assignment expr & body . basically:
      // Val("x", Id("a"), Add(Id("x"), Id("b")))
      // freeIds(e) => Set("a")
      // freeIds(b) => Set("x", "b")
    case Val(x, e, b) => freeIds(e) ++ (freeIds(b) - x) 
    case Id(x) => Set(x)

  // defined
  def bindingIds(expr: Expr): Set[String] = expr match
    case Num(n) => Set()
    case Add(l, r) => bindingIds(l) ++ bindingIds(r)
    case Mul(l, r) => bindingIds(l) ++ bindingIds(r)
    case Val(x, e, b) => Set(x) ++ (bindingIds(b) ++ bindingIds(e))
    case Id(x) => Set()
  
  // used
  def boundIds(expr: Expr): Set[String] = expr match
    case Num(n) => Set()
    case Add(l, r) => boundIds(l) ++ boundIds(r)
    case Mul(l, r) => boundIds(l) ++ boundIds(r)
    case Val(x, e, b) => boundIds(e) ++ boundIds(b) ++ (if (freeIds(b).contains(x)) Set(x) else Set())
    case Id(x) => if (bindingIds(expr).contains(x)) Set(x) else Set()

  // overwritten
  def shadowedIds(expr: Expr): Set[String] = expr match
    case Num(n) => Set()
    case Add(l, r) => shadowedIds(l) ++ shadowedIds(r)
    case Mul(l, r) =>  shadowedIds(l) ++ shadowedIds(r)
    case Val(x, e, b) => shadowedIds(e) ++ shadowedIds(b) ++ (if (bindingIds(e).contains(x)) Set(x) else Set())
    case Id(x) => if (bindingIds(expr).contains(x)) Set(x) else Set()
}
