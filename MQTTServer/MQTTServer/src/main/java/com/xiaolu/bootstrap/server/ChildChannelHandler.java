package com.xiaolu.bootstrap.server;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
	@Resource(name="distributionServerHandler")
	private DistributionServerHandler distributionServerHandler;
	@Override
	protected void initChannel(SocketChannel sch) throws Exception {
		ChannelPipeline pipeline = sch.pipeline();
		pipeline.addLast("idleStateHandler", new IdleStateHandler(40, 10, 0));
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(distributionServerHandler);
	}

}
