package scala.virtualization.lms
package common

import scala.virtualization.lms.common._

/**
 * IR: this is the front-end that defines what operations are available in user applications. Backends can share
 *     packages if they choose.
 */
trait ScalaOpsPkg extends Base
    with ImplicitOps with NumericOps with FractionalOps with OrderingOps with StringOps
    with RangeOps with IOOps with ArrayOps with BooleanOps with PrimitiveOps with MiscOps
    with Equal with IfThenElse with Variables with While

trait ScalaOpsPkgExp extends ScalaOpsPkg
    with ImplicitOpsExp with NumericOpsExp with FractionalOpsExp with OrderingOpsExp with StringOpsExp
    with RangeOpsExp with IOOpsExp with ArrayOpsExp with BooleanOpsExp with PrimitiveOpsExp with MiscOpsExp
    with FunctionsExp with EqualExp with IfThenElseExp with VariablesExp with WhileExp
    with DSLOpsExp



/**
 * Code gen: each target must define a code generator package.
 */


/////////
// Scala
trait ScalaCodeGenPkg extends ScalaGenImplicitOps with ScalaGenNumericOps with ScalaGenFractionalOps with ScalaGenOrderingOps
    with ScalaGenStringOps with ScalaGenRangeOps with ScalaGenIOOps with ScalaGenArrayOps with ScalaGenBooleanOps
    with ScalaGenPrimitiveOps with ScalaGenMiscOps with ScalaGenFunctions with ScalaGenEqual with ScalaGenIfThenElse
    with ScalaGenVariables with ScalaGenWhile with ScalaGenDSLOps { val IR: ScalaOpsPkgExp  }


/////
// C
trait CCodeGenPkg extends CGenMiscOps

///////
// Cuda
//TODO: CudaGenStringOps, CudaGenIOOps, CudaGenWhile not included
trait CudaCodeGenPkg extends CudaGenImplicitOps with CudaGenNumericOps with CudaGenFractionalOps with CudaGenOrderingOps
    with CudaGenRangeOps with CudaGenArrayOps with CudaGenBooleanOps
    with CudaGenPrimitiveOps with CudaGenMiscOps with CudaGenFunctions with CudaGenEqual with CudaGenIfThenElse
    with CudaGenVariables with CudaGenDSLOps { val IR: ScalaOpsPkgExp  }     

//trait CudaCodeGenPkg extends CudaGenNumericOps with CudaGenRangeOps with CudaGenFractionalOps
//    with CudaGenMiscOps with CudaGenFunctions with CudaGenVariables with CudaGenDSLOps with CudaGenImplicitOps { val IR: ScalaOpsPkgExp  }