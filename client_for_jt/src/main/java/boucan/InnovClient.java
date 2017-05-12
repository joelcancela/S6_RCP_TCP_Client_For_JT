package boucan;

/**
 * Created by Nassim B on 05/05/17.
 */
public abstract class InnovClient {

    protected String ip;
    protected int port;
    protected String toSend;

    protected InnovClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    protected abstract void connect();
    protected abstract String getInput();
    protected abstract void send();
    public abstract void start();
    protected abstract String parseInput();
    protected abstract String inputProject();

    public abstract void processResponse();
}
