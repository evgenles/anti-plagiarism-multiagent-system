import com.diit.antiplagitarism.socket.master.ProxyWebCommunicator;

public class Test {
    public static void main(String[] args) {
        ProxyWebCommunicator pc  = new ProxyWebCommunicator();

        while (true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
