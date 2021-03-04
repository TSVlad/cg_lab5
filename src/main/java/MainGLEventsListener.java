import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import java.io.*;
import java.util.Date;

public class MainGLEventsListener implements GLEventListener {
    private double timeStart;
    private double time;
    private int shaderProgram;

    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        int v = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
        String code = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/vertex_shader.glsl")))){
            String line = "";
            while ((line = reader.readLine()) != null) {
                code += line;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        gl.glShaderSource(v, 1, new String[]{code}, null);
        gl.glCompileShader(v);
        shaderProgram = gl.glCreateProgram();
        gl.glAttachShader(shaderProgram, v);
        gl.glLinkProgram(shaderProgram);
        gl.glValidateProgram(shaderProgram);
        gl.glUseProgram(shaderProgram);
        timeStart = new Date().getTime();
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glColor3d(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3d(0.8, 0.5, 0.5);
        gl.glVertex3d(0.8, 0, 0);
        gl.glVertex3d(-0.3, -0.5, 0);
        gl.glVertex3d(-0.3, 0, 0.5);
        gl.glEnd();

        System.out.println(time);
        time = (new Date().getTime() - timeStart) / 1000;
        gl.glUseProgram(shaderProgram);
        int t = gl.glGetUniformLocation(shaderProgram, "t");
        gl.glUniform4f(t, 0.0f, (float) time, 0.0f, 1.0f);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    public void animate() {

    }
}
