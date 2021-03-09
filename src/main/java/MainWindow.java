import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.Animator;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() throws HeadlessException {
        super();

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLJPanel gljpanel = new GLJPanel(capabilities);
        MainGLEventsListener evensListener = new MainGLEventsListener();
        gljpanel.addGLEventListener(evensListener);
        gljpanel.setSize(1000, 1000);

        this.getContentPane().add(gljpanel);
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Animator anim = new Animator(gljpanel);
        anim.start();

//        Thread thread = new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    System.out.println(e.toString());
//                }
//                gljpanel.display();
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
    }
}
