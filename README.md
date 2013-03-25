In the name of Allah

WordClustering version 1.0
===================
      9 January 2013

This is the README for the *"WordClustering" package* that used for adding 
unsupervised features to dependency parser. This package has been developed by 
[Mojtaba Khallash] (mailto: mkhallash@gmail.com) from _Iran University of Science and 
Technology (IUST)_.

The home page for the project is:
  http://nlp.iust.ac.ir
	
If you want to use this software for research, please refer to this web address 
in your papers.

The package can be used freely for non-commercial research and educational 
purposes. It comes with no  warranty, but we welcome all comments, bug reports, 
and suggestions for improvements.

Table of contents
------------------

1. Compiling
2. Example of usage
3. Running the package
4. References

1. Compiling
----------------

Requirements:
* Version 1.7 or later of the [Java 2 SDK] (http://java.sun.com)
You must add java binary file to system path. <br/>In linux, your
can open `~/.bashrc` file and append this line:
`PATH=$PATH:/<address-of-bin-folder-of-JRE>`

To compile the code, first decompress the package:

in linux:
> tar -xvzf WordClustering.tgz
> cd WordClustering
> sh compile_all.sh

in windows:
> decompress the WordClustering.zip
> compile.bat

You can open the all projects in NetBeans 7.1 (or maybe later) too.

2. Example of Usage
---------------------

By using "Brown Clustering" algorithm [1] we have clustered all words in [Persian 
treebank] (http://dadegan.ir/en) with 50 and 100 clusters count and also by using 
wordform and lemma. result of word clustering put in "Source" folder. this 
package read `lemm_paths.txt` and `word_paths.txt` that place in folder named 
with cluster count (currently "50" and "100"). You can put word clustering 
results of X cluster count in folder with name X.

3. Running the package
-------------------------

This package run in two mode: 

* gui [default mode]<br/>
Simply double click on jar file or run the following command:

> java -jar WordClustering.jar

* command-line<br/>
In order to running package in command-line mode must be set -v flag (visible) 
to 0:

> java -jar WordClustering.jar -v 0 -i <input-file> -o <output-file> -c <number-of-cluster(50|100)> -m <mode> -p <prefix-length> 

<table>
<tr><td>-i &lt;input conll file&gt;</td><td>intput CoNLL file that you want to add word cluster ids to FEATS column.</td></tr>
<tr><td>-o &lt;output conll file&gt;</td><td>name of output CoNLL file after adding feature</td></tr>
<tr><td>-c &lt;number-of-cluster(50|100|X) [default: 50]&gt;</td><td>number of cluster that use in brown clustering. this number refered to folder in "Source". currently two folder "50" and "100" exits but you can add result of X cluster count in folder with name X.</td></tr>
<tr><td>-m &lt;mode (word|lemm) [default word])&gt;</td><td>type of input that brown clustering received. this mode refered to file in "Source > (number-of-cluster)" folder.<br/>
        <ul>
          <li>word -> "word_paths.txt"</li>
          <li>lemm -> "lemm_paths.txt"</li>
        </ul>
</td></tr>
<tr><td>-p &lt;prefix length (default is -1 which means full bit length)&gt;</td><td>
length of prefix bit for truncating each bit-string associated with words in "Source > (number-of-cluster) > (mode)" file. for disable truncating you can omit this parameter or send -1 to use full bit string.
(By using prefixes of various lengths, we can cluster with different granularities [2].)
</td></tr>
</table>


For example:

> java -jar WordClustering.jar -v 0 -i input.conll -o output.conll -c 100 -m lemm -p 10

For running word clustering algorithm you can used the [Liang implementation] (http://cs.stanford.edu/~pliang/software/) [3] 
of  the Brown algorithm to obtain the necessary word clusters.

4. References
------------
[1]	P. F. Brown, et al., "Class-based n-gram models of natural language", 
Computational Linguistics, vol. 18, pp. 467-479, 1992.

[2]	S. Miller, et al., "Name tagging with word clusters and discriminative training", 
in In Proceedings of 2004 Human Language Technology conference - North American chapter 
of the Association for Computational Linguistics annual meeting (HLT-NAACL 2004), Boston, 
Massachusetts,  USA, pp. 337-342, 2004.
	
[3]	P. Liang, "Semi-supervised learning for natural language", Massachusetts 
Institute of Technology. Dept. of Electrical Engineering and Computer Science, 2005.
