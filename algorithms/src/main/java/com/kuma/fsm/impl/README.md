# FSM 的四种实现方式

* while{switch} 循环和条件语句
* 状态模式
* 状态表
* 枚举

### Test Case

T01

> Given：一个Locked的进站闸口  
> When: 投入硬币  
> Then：打开闸口

T02

> Given：一个Locked的进站闸口  
> When: 通过闸口  
> Then：警告提示

T03

> Given：一个Unocked的进站闸口  
> When: 通过闸口  
> Then：闸口关闭

T04

> Given：一个Unlocked的进站闸口  
> When: 投入硬币  
> Then：退还硬币

T05

> Given：一个闸机口  
> When: 非法操作  
> Then：操作失败


[参考文章](https://zhuanlan.zhihu.com/p/97442825)