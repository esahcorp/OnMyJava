# DFA 算法

敏感词过滤

为什么不用 KMP？
这个问题可以理解为为什么不用匹配算法，匹配算法是完整词条匹配，但是敏感词数量一般比较多，会导致遍历匹配的效率低下。

正确方式是类似 KMP 的状态表的思路，用一个 Trie 树存储所有的敏感词，按前缀匹配。

前置知识：用 HashMap 构建 Trie 树

敏感词过滤算法分为两步：

1. 根据敏感词库构建 Trie 树
2. 利用 Trie 树对文本进行过滤