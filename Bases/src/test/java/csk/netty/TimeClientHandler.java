package csk.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    ByteBuf buf;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf m = (ByteBuf) msg;
//        buf.writeBytes(m);
//        m.release();
//        if (buf.readableBytes() >= 4) {
//            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
//            ctx.close();
//        }
//        //设置流可以重新读取
//        buf.resetReaderIndex();
        //不使用TimeDecoder时 将 buf=(ByteBuf)msg;注释 放开其他的注释
        buf=(ByteBuf)msg;
        long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
        Date currentTime = new Date(currentTimeMillis);
        System.out.println("Default Date Format:" + currentTime.toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        System.out.println("Date Format:" + dateString);
        ctx.close();
    }

//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) {
//        buf = ctx.alloc().buffer(4); // (1)
//    }

//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) {
//        buf.release(); // (1)
//        buf = null;
//    }

    @Test
    public void timeClientRun() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });
            // Start the client.
            ChannelFuture f = b.connect("localhost", 8099).sync(); // (5)
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
