package com.xiaolu.bootstrap.server;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Component
public class DistributionServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		
	}

}
