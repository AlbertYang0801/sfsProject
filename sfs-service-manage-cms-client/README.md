
# cms系统消费者工程

####1.主要功能:
该工程主要是在页面发布完成后，接收发送消息
到RabbitMQ的消息，然后根据消息内容到GridFS文件系统
中下载发布后的页面到本地，实现本地文件的替换。
####2。项目端口为31000
