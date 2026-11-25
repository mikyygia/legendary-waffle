package kuplrg

object Implementation extends Template {

  import Expr.*
  import Value.*

  def interp(expr: Expr, env: Env): Value = expr match
    case Num (n) => NumV(n)
    case Add (l, r) => (interp(l, env), interp(r, env)) match
      case (NumV(l), NumV(r)) => NumV(l + r)
      case (l, r) => error(s"invalid operation: ${l.str} + ${r.str}") 
    case Mul (l, r) => (interp(l, env), interp(r, env)) match
      case (NumV(l), NumV(r)) => NumV(l * r)
      case (l, r) => error(s"invalid operation: ${l.str} * ${r.str}") 
    case Val (x, e, b) => interp(b, env + (x -> interp(e, env)))  
    case Id (x) => env.getOrElse(x, error(s"free identifier: $x"))
    case Fun (p, b) => CloV(p, b, env)
    case App (f, e) => interp(f, env) match
      case CloV(p, b, fenv) => interp(b, fenv + (p -> interp(e, env)))
      case v => error(s"not a function ${v.str}")

  def interpDS(expr: Expr, env: Env): Value = expr match
    case Num (n) => NumV(n)
    case Add (l, r) => (interpDS(l, env), interpDS(r, env)) match
      case (NumV(l), NumV(r)) => NumV(l + r)
      case (l, r) => error(s"invalid operation: ${l.str} + ${r.str}") 
    case Mul (l, r) => (interpDS(l, env), interpDS(r, env)) match
      case (NumV(l), NumV(r)) => NumV(l * r)
      case (l, r) => error(s"invalid operation: ${l.str} * ${r.str}") 
    case Val (x, e, b) => interpDS(b, env + (x -> interpDS(e, env)))  
    case Id (x) => env.getOrElse(x, error(s"free identifier: $x"))
    case Fun (p, b) => CloV(p, b, env)
    case App (f, e) => interpDS(f, env) match
      case CloV(p, b, fenv) => interpDS(b, env + (p -> interpDS(e, env)))
      case v => error(s"not a function ${v.str}")
}
