package Server.Thread;


import Server.Services.Controller.Resolver;
import utils.NetworkProvider.ServerInterface;
import utils.NetworkProvider.Model.ClientMessage;

/**
 * Class MessageHandlerThread
 */
public class MessageHandlerThread implements Runnable {
    private MessageListenerThread messageListenerThread;
    private Resolver resolver;

    /**
     * @param messageListenerThread
     * @param resolver
     */
    public MessageHandlerThread(MessageListenerThread messageListenerThread, Resolver resolver) {
        this.messageListenerThread = messageListenerThread;
        this.resolver = resolver;
    }

    @Override
    public void run() {
        while (true) {
            ClientMessage clientMessage = this.messageListenerThread.getMessage();

            if (null == clientMessage) {
                try {
                    Thread.sleep(3000);

                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    return;
                }
            }

            if (!this.resolver.supports(clientMessage)) {
                System.out.println("Unexpected message");
            } else {
                this.resolver.resolve(clientMessage);
            }
        }
    }
}
