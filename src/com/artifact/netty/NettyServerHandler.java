package com.artifact.netty;

import java.util.HashMap;

import com.artifact.model.BaseMsg;
import com.artifact.netty.server.UserServices;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by yaozb on 15-4-11.
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyChannelMap.remove((SocketChannel) ctx.channel());
	}

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
		System.out.println(baseMsg.getClientId()+":"+baseMsg.getData("userid")+":"+baseMsg.getData("password"));
		if (BaseMsg.MsgType.LOGIN.equals(baseMsg.getType())) {
			HashMap<String, String> userData = baseMsg.getData();
//			if (UserServices.getUserServices().login(userData.get("userid"), userData.get("password"),
//					userData.get("isLogin"))) {
				NettyChannelMap.add(baseMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
				System.out.println("client" + baseMsg.getClientId() + "登录成功");
//			}
		} else {
			if (NettyChannelMap.get(baseMsg.getClientId()) == null) {
				// 说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
				baseMsg.setType(BaseMsg.MsgType.LOGIN);
				channelHandlerContext.channel().writeAndFlush(baseMsg);
			}
		}
		switch (baseMsg.getType()) {
		case ASK: {
			NettyChannelMap.get(baseMsg.getClientId()).writeAndFlush(new HashMap<>());
			break;
		}
		case PING: {
			// 收到客户端回复
			channelHandlerContext.channel().writeAndFlush(baseMsg);
		}
			break;
		default:
			break;
		}
		ReferenceCountUtil.release(baseMsg);
	}
}
