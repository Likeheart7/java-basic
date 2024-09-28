package nio;

/*
Java NIO主要有由以下几个核心部分组成
1. Channel
2. Buffer
3. Selector
也包括一些其他组件,如Pipe和FileLock，一般都是这三个核心部分使用的工具类

关于Channel和Buffer
    基本nio中所有的IO都从Channel开始，Channel像流，可以从Channel中将数据读取到Buffer，也可以从Buffer中将数据写到Channel。
Channel的主要实现如下：
FileChannel
DatagramChannel
SocketChannel
ServerSocketChannel
分别是文件、UDP、TCP客户端和TCP服务端的实现。

关于Selector
Selector就是多路复用。意味着一个线程可以通过Selector监听多个Channel，对于多个通道流量都偏低的情况下就会很方便。
使用Selector时，需要向Selector注册Channel，然后调用select方法，方法将阻塞到某个注册的通道有事件就绪，线程可以根据该方法的返回处理具体的事件。
 */
