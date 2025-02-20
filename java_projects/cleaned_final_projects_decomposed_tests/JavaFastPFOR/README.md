JavaFastPFOR: A simple integer compression library in Java 
==========================================================
 [![][maven img]][maven] [![][license img]][license] [![docs-badge][]][docs]
[![Java CI](https://github.com/lemire/JavaFastPFOR/actions/workflows/basic.yml/badge.svg)](https://github.com/lemire/JavaFastPFOR/actions/workflows/basic.yml)


License
-------

This code is released under the
Apache License Version 2.0 http://www.apache.org/licenses/.


What does this do?
------------------

It is a library to compress and uncompress arrays of integers 
very fast. The assumption is that most (but not all) values in
your array use much less than 32 bits, or that the gaps between
the integers use much less than 32 bits. These sort of arrays often come up
when using differential coding in databases and information
retrieval (e.g., in inverted indexes or column stores).

Please note that random integers are not compressible, by this
library or by any other means. If you ever had the means of
systematically compressing random integers, you could compress
any data source to nothing, by recursive application of your technique. 

This library can decompress integers at a rate of over 1.2 billions per second
(4.5 GB/s). It is significantly faster than generic codecs (such
as Snappy, LZ4 and so on) when compressing arrays of integers.

The library is used in [LinkedIn Pinot](https://github.com/linkedin/pinot), a realtime distributed OLAP datastore.
Part of this library has been integrated in Parquet (http://parquet.io/).
A modified version of the library is included in the search engine 
Terrier (http://terrier.org/). This libary is used by ClueWeb 
Tools (https://github.com/lintool/clueweb). It is also used by [Apache NiFi](https://nifi.apache.org).

This library inspired a compression scheme used by Apache Lucene and Apache Lucene.NET (e.g., see
http://lucene.apache.org/core/4_6_1/core/org/apache/lucene/util/PForDeltaDocIdSet.html ).

It is a java port of the fastpfor C++ library (https://github.com/lemire/FastPFor). 
There is also a Go port (https://github.com/reducedb/encoding). The C++
library is used by the zsearch engine (http://victorparmar.github.com/zsearch/)
as well as in GMAP and GSNAP (http://research-pub.gene.com/gmap/).


Usage
------

Really simple usage:

```java
        IntegratedIntCompressor iic = new IntegratedIntCompressor();
        int[] data = ... ; // to be compressed
        int[] compressed = iic.compress(data); // compressed array
        int[] recov = iic.uncompress(compressed); // equals to data
```

For more examples, see example.java or the examples folder.

JavaFastPFOR supports compressing and uncompressing data in chunks (e.g., see ``advancedExample`` in [https://github.com/lemire/JavaFastPFOR/blob/master/example.java](example.java)).

Some CODECs ("integrated codecs") assume that the integers are
in sorted orders and use differential coding (they compress deltas). 
They can be found in the package me.lemire.integercompression.differential.
Most others do not.

The Java Team at Intel (R) introduced the vector implementation for FastPFOR
based on the Java Vector API that showed significant gains over the
non-vectorized implementation. For an example usage, see
examples/vector/Example.java. The feature requires JDK 19+ and is currently for 
advanced users.

Maven central repository
------------------------

Using this code in your own project is easy with maven, just add
the following code in your pom.xml file:

```xml
    <dependencies>
         <dependency>
	     <groupId>me.lemire.integercompression</groupId>
	     <artifactId>JavaFastPFOR</artifactId>
	     <version>[0.1,)</version>
         </dependency>
     </dependencies>
```

Naturally, you should replace "version" by the version
you desire.



You can also download JavaFastPFOR from the Maven central repository:
http://repo1.maven.org/maven2/me/lemire/integercompression/JavaFastPFOR/


Why?
----

We found no library that implemented state-of-the-art integer coding techniques
such as Binary Packing, NewPFD, OptPFD, Variable Byte, Simple 9 and so on in Java.
We wrote one. 

Thread safety 
----

Some codecs are thread-safe while others are not.
For this reason, it is best to use one codec per thread.
The memory usage of a codec instance is small in any case.

Nevertheless, if you want to reuse codec instances, 
note that by convention, unless the documentation of a codec specify
that it is not thread-safe, then it can be assumed to be thread-safe.

Authors
-------

Main contributors
* Daniel Lemire, http://lemire.me/en/
* Muraoka Taro, https://github.com/koron

with contributions by 
* the Terrier team (Matteo Catena, Craig Macdonald, Saúl Vargas and Iadh Ounis)
* Di Wu, http://www.facebook.com/diwu1989
* Stefan Ackermann, https://github.com/Stivo
* Samit Roy, https://github.com/roysamit
* Mulugeta Mammo, https://github.com/mulugetam (for VectorFastPFOR)

How does it compare to the Kamikaze PForDelta library?
------------------------------------------------------

In our tests, Kamikaze PForDelta is slower than our implementations. See
the benchmarkresults directory for some results. 

https://github.com/lemire/JavaFastPFOR/blob/master/benchmarkresults/benchmarkresults_icore7_10may2013.txt


Reference:
 http://sna-projects.com/kamikaze/



Requirements
------------

Releases up to 0.1.12 require Java 7 or better.

The current development versions assume JDK 11 or better.



How fast is it?
---------------

Compile the code and execute `me.lemire.integercompression.benchmarktools.Benchmark`.

Speed is always reported in millions of integers per second.


For Maven users
---------------


```
mvn compile
mvn exec:java
```

You may run our examples as follows:

```
mvn package
javac -cp target/classes/:. example.java
java -cp target/classes/:. example
```

For ant users (legacy, currently untested)
-------------

If you use Apache ant, please try this:

    $ ant Benchmark

or:

    $ ant Benchmark -Dbenchmark.target=BenchmarkBitPacking


API Documentation
-----------------

http://www.javadoc.io/doc/me.lemire.integercompression/JavaFastPFOR/

Want to read more?
------------------

This library was a key ingredient in the best paper at ECIR 2014 :

Matteo Catena, Craig Macdonald, Iadh Ounis, On Inverted Index Compression for Search Engine Efficiency,  Lecture Notes in Computer Science 8416 (ECIR 2014), 2014.
http://dx.doi.org/10.1007/978-3-319-06028-6_30

We wrote several research papers documenting many of the CODECs implemented here:

* Daniel Lemire, Nathan Kurz, Christoph Rupp, Stream VByte: Faster Byte-Oriented Integer Compression, Information Processing Letters (to appear) https://arxiv.org/abs/1709.08990
* Daniel Lemire, Leonid Boytsov, Nathan Kurz, SIMD Compression and the Intersection of Sorted Integers, Software Practice & Experience Volume 46, Issue 6, pages 723-749, June 2016 http://arxiv.org/abs/1401.6399
* Daniel Lemire and Leonid Boytsov, Decoding billions of integers per second through vectorization, Software Practice & Experience 45 (1), 2015.  http://arxiv.org/abs/1209.2137 http://onlinelibrary.wiley.com/doi/10.1002/spe.2203/abstract
* Jeff Plaisance, Nathan Kurz, Daniel Lemire, Vectorized VByte Decoding, International Symposium on Web Algorithms 2015, 2015. http://arxiv.org/abs/1503.07387
* Wayne Xin Zhao, Xudong Zhang, Daniel Lemire, Dongdong Shan, Jian-Yun Nie, Hongfei Yan, Ji-Rong Wen, A General SIMD-based Approach to Accelerating Compression Algorithms, ACM Transactions on Information Systems 33 (3), 2015. http://arxiv.org/abs/1502.01916


Ikhtear Sharif wrote his M.Sc. thesis on this library:

Ikhtear Sharif, Performance Evaluation of Fast Integer Compression Techniques Over Tables, M.Sc. thesis, UNB 2013.
https://unbscholar.lib.unb.ca/islandora/object/unbscholar%3A9399/datastream/PDF/view

He also posted his slides online: http://www.slideshare.net/ikhtearSharif/ikhtear-defense

Other recommended libraries
-----------------------------

* Fast integer compression in Go: https://github.com/ronanh/intcomp
* Encoding: Integer Compression Libraries for Go https://github.com/zhenjl/encoding
* CSharpFastPFOR: A C#  integer compression library  https://github.com/Genbox/CSharpFastPFOR
* TurboPFor is a C library that offers lots of interesting optimizations and Java wrappers. Well worth checking! (Uses a GPL license.) https://github.com/powturbo/TurboPFor

Funding
-----------

This work was supported by NSERC grant number 26143.


[maven img]:https://maven-badges.herokuapp.com/maven-central/me.lemire.integercompression/JavaFastPFOR/badge.svg
[maven]:http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22me.lemire.integercompression%22%20

[license]:LICENSE
[license img]:https://img.shields.io/badge/License-Apache%202-blue.svg

[docs-badge]:https://img.shields.io/badge/API-docs-blue.svg?style=flat-square
[docs]:http://www.javadoc.io/doc/me.lemire.integercompression/JavaFastPFOR/
