In the name of Allah

-----------------------------
WordClustering version 1.0
      9 January 2013
-----------------------------

This is the README for the "WordClustering" package that used for adding 
unsupervised features to dependency parser. This package has been developed by 
Mojtaba Khallash (mkhallash@gmail.com) from Iran University of Science and 
Technology (IUST).

The home page for the project is:
	http://nlp.iust.ac.ir

The code is available on Github at:
	https://github.com/mojtaba-khallash/word-clustering
	
If you want to use this software for research, please refer to this web address 
in your papers.

-------------------------

The package can be used freely for non-commercial research and educational 
purposes. It comes with no  warranty, but we welcome all comments, bug reports, 
and suggestions for improvements.

------------------
Table of contents
------------------

1. Compiling

2. Example of usage

3. Running the package

4. References

----------------
1. Compiling
----------------

Requirements:
* Version 1.7 or later of the Java 2 SDK (http://java.sun.com)
  You must add java binary file to system path. In linux, your
  can open ~/.bashrc file and append this line:
    PATH=$PATH:/<address-of-bin-folder-of-JRE>

To compile the code, first decompress the package:

in linux:
$ tar -xvzf WordClustering.tgz
$ cd WordClustering
$ sh compile_all.sh

in windows:
- decompress the WordClustering.zip
- compile.bat

You can open the all projects in NetBeans 7.1 (or maybe later) too.

---------------------
2. Example of Usage
---------------------

By using "Brown Clustering" algorithm [1] we have clustered all words in Persian 
treebank (http://dadegan.ir/en) with 50 and 100 clusters count and also by using 
wordform and lemma. result of word clustering put in "Source" folder. this 
package read "lemm_paths.txt" and "word_paths.txt" that place in folder named 
with cluster count (currently "50" and "100"). You can put word clustering 
results of X cluster count in folder with name X.

-------------------------
3. Running the package
-------------------------

This package run in two mode: 

* gui [default mode]
Simply double click on jar file or run the following command:

> java -jar WordClustering.jar

* command-line
In order to running package in command-line mode must be set -v flag (visible) 
to 0:

> java -jar WordClustering.jar -v 0 -i <input-file> -o <output-file> \
  -c <number-of-cluster(50|100)> -m <mode> -p <prefix-length> 

-i <input conll file>
	intput CoNLL file that you want to add word cluster ids to FEATS column.
	
-o <output conll file>
	name of output CoNLL file after adding feature
	
-c <number-of-cluster(50|100|X) [default: 50]>
	number of cluster that use in brown clustering. this number refered to 
	folder in "Source". currently two folder "50" and "100" exits but you 
	can add result of X cluster count in folder with name X.
	
-m <mode (word|lemm) [default word])>
	type of input that brown clustering received. this mode refered to file 
	in "Source > (number-of-cluster)" folder.
            word -> "word_paths.txt"
            lemm -> "lemm_paths.txt"
	
-p <prefix length (default is -1 which means full bit length)>
	length of prefix bit for truncating each bit-string associated with 
	words in "Source > (number-of-cluster) > (mode)" file. for disable 
	truncating you can omit this parameter or send -1 to use full bit string.
	(By using prefixes of various lengths, we can cluster with 
	different granularities [2].)

For example:

> java -jar WordClustering.jar -v 0 -i input.conll -o output.conll -c 100 \
  -m lemm -p 10

** For running word clustering algorithm you can used the Liang [3] 
   (http://cs.stanford.edu/~pliang/software/) implementation of  the Brown 
   algorithm to obtain the necessary word clusters.

--------------
4. References
--------------
[1]	P. F. Brown, et al., "Class-based n-gram models of natural language", 
    Computational Linguistics, vol. 18, pp. 467-479, 1992.

[2]	S. Miller, et al., "Name tagging with word clusters and discriminative 
    training", in In Proceedings of 2004 Human Language Technology conference -
    North American chapter of the Association for Computational Linguistics 
    annual meeting (HLT-NAACL 2004), Boston, Massachusetts,  USA, pp. 337-342, 
    2004.
	
[3]	P. Liang, "Semi-supervised learning for natural language", Massachusetts 
    Institute of Technology. Dept. of Electrical Engineering and Computer  
    Science, 2005.