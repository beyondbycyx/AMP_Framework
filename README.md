# AMP_Framework



## AMP 模式 ##

> 其实只是取了一个名字，没什么特别含义。
> 最近开了不少的源码，第三方框架，特别是看了Jake Wharton 大神的源代码，才发现大神都是在以面向对象的思维在为自己的类，变量赋予一个更加接近现实的名字。
> 再看下网络上鱼龙混杂的文章，发现文字永远比代码多，代码是写对了，但是却在对一些具有特殊意义的类，如随便用delegate 对象去做一个不符合它身份的事情。
> 真心觉得如何用面向对象去写代码非常的重要。

- 在这里我也写了一个姑且称为模式的MVP模式，用作记录自己探讨的总结。不对外界具有任何的意义，进入正题。
 
参考app的应用<a href = "https://github.com/saulmm/Material-Movies">HackVG </a>

**正题：**  
1. 清除Activity/fragment的冗余代码：
 网上的MVP模式，都是在想分离出一个层次来处理代码，更加重要的是视图由一个Activit与一个View取对应，不希望出现一个Activity与多个View绑定的关系。

2. 寻找View的层的担当类：
 网上有两种的做法，第一个是将View类从Activtiy从抽离出来，独立做出一个View层。第二个是直接将Activity作为View层，在它之下产生一个Presenter层。

**总结：**
- 本人选择了第二个做法，将Actitvity当成了一个View层。这是考虑了谷歌有时将一个Activity弄成ListActivity的样子，觉得Activity与View耦合性很强。

## 模式关系 ##
- Activity： 实现了View 层的接口，初始化View，提供方法，显示各种相应的视图界面

- Presenter：持有Activity，处理返回的数据与用户输入的数据，最后通过调用Activity暴露的方法展示相应的View。

- Worker： 接受到Presenter的请求，开始请求数据(从网络，本地，内存等)，并对回来的数据进行封装处理，利用Bus交给Presnter处理返回的数据。

- Model: 数据层，映射了Json对象类型，并为各种需要的View界面，设置定义(添加)不同的状态值在Model内部，以Model数据的状态值来取代相应的View视图。


## 代码 ##
- 参考了Volley，只是写了4个线程进行网络请求，还没有添加对于图片的缓存机制
- 参考了Otto的Bus，目前的Bus只能在主线程有效，没有添加在多线程里使用的功能。只能由Bus post对象，由@removetArg 接受对象，并调用方法。







 




