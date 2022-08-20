/**
 * 
 */
package com.channelsoft.slee.callagent.ucce;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import com.channelsoft.ucce.protocol.UCCEService;
import com.channelsoft.ucce.protocol.UCCEService.UCCEMessage;
import com.google.protobuf.ExtensionRegistry;

/**
 * @author wuxz
 */
public class UCCEClientPipelineFactory implements ChannelPipelineFactory
{
	static ExtensionRegistry registry = ExtensionRegistry.newInstance();

	static
	{
		UCCEService.registerAllExtensions(registry);
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	public ChannelPipeline getPipeline() throws Exception
	{
		ChannelPipeline p = Channels.pipeline();
		p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
		p.addLast("protobufDecoder", new ProtobufDecoder(UCCEMessage
				.getDefaultInstance(), registry));

		p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
		p.addLast("protobufEncoder", new ProtobufEncoder());

		p.addLast("handler", new UCCEClientHandler());
		return p;
	}
}