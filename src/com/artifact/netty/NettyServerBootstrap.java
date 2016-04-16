package com.artifact.netty;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.artifact.model.BaseMsg;
import com.artifact.model.BaseMsg.MsgType;
import com.artifact.model.User;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by yaozb on 15-4-11.
 */
public class NettyServerBootstrap
{
    private int port;
    private SocketChannel socketChannel;

//    public static void main(String[] args) throws InterruptedException
//    {
//    	HashMap<String, User> sHashMap = new HashMap<String, User>();
//    	for (int i = 0; i < 10000000; i++) {
//    		User user = new User();
//    		user.setId(i);
//    		user.setPassword("ds"+i);
//    		user.setUserid("id"+i);
//    		sHashMap.put(""+i, new User());
//		}
//    	System.out.println("开始");
//    	long beginTime = System.currentTimeMillis();
//    	for (int i = 0; i < 50; i++) {
//    		System.out.println(sHashMap.get("97988"+i));
//		}
//    	System.out.println("执行耗时 : " + (System.currentTimeMillis() - beginTime) / 1000f + " 秒 ");
//        NettyServerBootstrap bootstrap = new NettyServerBootstrap(9999);
//        while (true)
//        {
//            SocketChannel channel = NettyChannelMap.get("864381832@qq.com");
//            if (channel != null)
//            {
//                BaseMsg askMsg = new BaseMsg();
//                askMsg.setType(MsgType.LOGIN);
//                channel.writeAndFlush(askMsg);
//            }
//            TimeUnit.SECONDS.sleep(10);
//        }
//    }

    public NettyServerBootstrap(int port) throws InterruptedException
    {
        this.port = port;
        bind();
    }

    private void bind() throws InterruptedException
    {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception
            {
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                p.addLast(new NettyServerHandler());
            }
        });
        ChannelFuture f = bootstrap.bind(port).sync();
        if (f.isSuccess())
        {
            System.out.println("服务器启动成功");
        }
    }

}
