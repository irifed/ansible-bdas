val pagecounts = sc.textFile("/wiki/pagecounts")
//pagecounts.take(10)
//pagecounts.take(10).foreach(println)
pagecounts.count
val enPages = pagecounts.filter(_.split(" ")(1) == "en").cache
enPages.count
//val enTuples = enPages.map(line => line.split(" "))
//val enKeyValuePairs = enTuples.map(line => (line(0).substring(0, 8), line(3).toInt))
//enKeyValuePairs.reduceByKey(_+_, 1).collect
//enPages.map(l => l.split(" ")).map(l => (l(2), l(3).toInt)).reduceByKey(_+_, 40).filter(x => x._2 > 200000).map(x => (x._2, x._1)).collect.foreach(println)


// val sqlContext = new org.apache.spark.sql.SQLContext(sc)
// import sqlContext.createSchemaRDD

println("PASSED")
exit()

