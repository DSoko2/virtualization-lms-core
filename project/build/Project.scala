import sbt._
import java.io.File

class Project(info: ProjectInfo) extends DefaultProject(info)
{
  
  // define a root-level environment file local.properties
  // from which scala.virtualized.home will be read
  lazy val local = new BasicEnvironment {
      def log = Project.this.log
      def envBackingPath = info.projectPath / "local.properties"
      lazy val scalaVirtualizedHome = property[String]
  }
  
  // use the local scala-virtualized compiler and library
  override def localScala =
    defineScala("2.8.x-virtualized-SNAPSHOT", new File(local.scalaVirtualizedHome.get.getOrElse {
      log.error("scala.virtualized.home needs to be defined in local.properties and "+
      "must point to a valid scala-virtualized home directory"); "<undefined>"
    }))::Nil


  // source directory layout
  override def mainScalaSourcePath = "src"
  override def mainResourcesPath = "resources"

  override def testScalaSourcePath = "test-src"
  override def testResourcesPath = "test-resources"

  // target directory layout (standard for now)
  
  // dependencies
  val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test"

  override def testClasspath = super.testClasspath +++ ("scalatest-1.2" / "scalatest-1.2.jar")
//  val scalac = "org.scala-lang" % "scala-compiler" % "2.8.0" % "test"
//  val scala = "org.scala-lang" % "scala-library" % "2.8.0" % "test"


  // compile options
  
//  override def compileOptions = super.compileOptions ++ Seq(Unchecked, Deprecation)

}
