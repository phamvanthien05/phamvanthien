
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private textView tv;

    public Controller(textView tv) {
        this.tv = tv;
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        Thread thread = new Thread(()->{
            String src = e.getActionCommand();
            if (src.equals("Luu")) {
                tv.save();
                return;
            }
            if (src.equals("Xoa")) {
                tv.clean();
                return;
            }
            if (src.equals("Doc file")) {
                tv.read();
                return;
            }
            if (src.equals("Dem slg tu")) {
                tv.demsolgtu();
            }
        });
        thread.start();
    }
}
