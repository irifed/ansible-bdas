// perform some simple operations and check if we did not have an exception

try {
  // do not forget to put this file to HDFS
  val textFile = sc.textFile("/README.md")
  textFile.count()

  val linesWithSpark = textFile.filter(line => line.contains("Spark"))
  linesWithSpark.cache()
  linesWithSpark.count()

  println("PASSED")
} catch {
  case  _: Throwable => { println("FAILED") }
}

exit()

