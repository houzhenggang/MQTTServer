package com.xiaolu.bootstrap.server;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.junit.validator.ValidateWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 *<p>Title:MQTTServer</p>
 *<p>Description:netty的server端 /TCP连接</p>
 * @author xiaolu
 * @date 2016年12月14日 上午9:42:53
 */
@Service
public class MQTTServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(MQTTServer.class);
	
	private EventLoopGroup bossGroup;
	
	private EventLoopGroup workerGroup;
	
	@Resource(name="childChannelHandler")
	private ChildChannelHandler childChannelHandler;
	
	@Value("${netty.port:8085}")
	private int port;
	//初始化
	@PostConstruct
	private void Run(){
		LOGGER.info("启动netty服务器");
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			 .channel(NioServerSocketChannel.class)
			 .option(ChannelOption.SO_BACKLOG, 1024)
			 .childHandler(childChannelHandler);
			//绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
